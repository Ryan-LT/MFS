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
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import com.csc.mfs.model.*;
import com.csc.mfs.repository.FilesRepository;
import com.csc.mfs.service.DownloadService;
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
    private FilesRepository fileRepo;
    @Autowired
    private FileService fileService;
    
    @Autowired
    private StorageProperties storageProp;
    @Autowired
    private DownloadService downloadService;
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
    	
    	
//      model.addAttribute("files", storageService
//      .loadAll()
//      .map(path -> path.getFileName().toString()) .collect(Collectors.toList()));
              
    	
    	
//      Path x =Paths.get("s");
//     
//      model.addAttribute("files", storageService
//      .loadAll()
//      .map(path ->
//              x)
//      .collect(Collectors.toList()));
//    	List<String> x = new Vector();
//    	x.add("124");
//    	x.add("12344");
//    	model.addAttribute("files",x);
        
        return "mainPage";
    }
    
    /**
     * @param This function is for responding to a download request
     * @return
     */
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
    	//get current log-in user
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
 		User user = userService.findUserByEmail(auth.getName());
 		//get download information
 		Download downLoad = new Download();
 		
 		List<Files> listFile = fileRepo.findByName(filename);
 		Files fileDownLoad = listFile.get(0);
 		
 		// check if the user have not reach the maximum download per day.
 		if(downloadService.beforeDownload(user.getId(), fileDownLoad.getSize()))
 		{
 			downLoad.setIdFile(fileDownLoad);
 	 		downLoad.setIdUser(user);
 	 		downLoad.setDatedownload(new Date());
 	 		downloadService.insert(downLoad);
 	        Resource file = storageService.loadAsResource(filename);
 	        return ResponseEntity
 	                .ok()
 	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
 	                .body(file);
 		}
 		
 		Resource file = null;
 		return ResponseEntity
	                .ok()
	                .body(file);
        
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
    	
        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        Files fileDB = new Files();
        Path fileDBPath = Paths.get(storageProp.getLocation());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		CategoriesType category = new CategoriesType(1);
		if(fileService.beforeUpload(user.getId(), file.getSize()/1024.0)!=-1){
			 fileDB.setName(file.getOriginalFilename());
		        fileDB.setSize((double)file.getSize()/1024.0);
		        fileDB.setDateupload(new Date());
		        fileDB.setPath(fileDBPath.resolve(file.getOriginalFilename()).toString());
		        fileDB.setUserId(user.getId());
		        fileDB.setIdType(category);
		        fileService.insertFile(fileDB);
		        
		        return ResponseEntity.ok().body("You have successfully uploaded "+file.getOriginalFilename());
		} else {
			 return ResponseEntity.ok().body("Fail to upload "+file.getOriginalFilename());
		}
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}