package com.csc.mfs.controller;

import com.csc.mfs.storage.StorageFileNotFoundException;
import com.csc.mfs.storage.StorageProperties;
import com.csc.mfs.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.stream.Collectors;
import com.csc.mfs.model.*;
import com.csc.mfs.repository.FilesRepository;
import com.csc.mfs.service.FileService;
import com.csc.mfs.service.UserService;

@Controller
public class FileUploadController {

    private final StorageService storageService;  
 
    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }
    @Autowired
    private FileService fileService;
    
    @Autowired
    private StorageProperties storageProp;
    
    @Autowired
	private UserService userService;

    @GetMapping("/upload/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));

        return "mainPage";
    }
    
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        Files fileDB = new Files();
        Path fileDBPath = Paths.get(storageProp.getLocation());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
        fileDB.setName(file.getOriginalFilename());
        fileDB.setSize((double)file.getSize());
        fileDB.setDateupload(new Date());
        fileDB.setPath(fileDBPath.resolve(file.getOriginalFilename()).toString());
        fileDB.setUserId(user);
        
        fileService.insertFile(fileDB);
        return "redirect:/upload/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
