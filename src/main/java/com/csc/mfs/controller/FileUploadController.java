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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

//        model.addAttribute("files", storageService
//                .loadAll()
//                .map(path ->
//                        MvcUriComponentsBuilder
//                                .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
//                                .build().toString())
//                .collect(Collectors.toList()));
//    	List<Files> listDisplayFile = fileRepo.getAllFile();
//    	Map<String,String> files= new HashMap<String,String>();
//    	
//    	
//    	for(Files f: listDisplayFile){
//    		files.put(f.getName(), f.getPath());
//    	}
//    	
//    	files.
//    	
//        model.addAttribute("files", files);
      
        
//        model.addAttribute("files", storageService
//                .loadAll()       
//                .collect(Collectors.toList()));
 //       model.addAttribute("link","http://localhost:8080/files/");
	
        return "mainPage";
    }
    
    /**
     * @param This function is for responding to a download request
     * @return
     */
    @GetMapping("/download/files/{idFile}")
    public ResponseEntity<Resource> serveFile(@PathVariable Integer idFile) {
    	//get current log-in user
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
 		User user = userService.findUserByEmail(auth.getName());
 		//get download information
 		Download downLoad = new Download();
 		//retrieve the file name without time
// 		String filenameDB = filename.substring(0,filename.length()-13);
 		Files fileDownload = fileRepo.findOne(idFile);
 		
 		
 		// check if the user have not reach the maximum download per day.
 		if(downloadService.beforeDownload(user.getId(), fileDownload.getSize()))
 		{
 			downLoad.setIdFile(fileDownload);
 	 		downLoad.setIdUser(user);
 	 		downLoad.setDatedownload(new Date());
 	 		downloadService.insert(downLoad);
 	 		String filename  = fileDownload.getPath();
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
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
    	
        
        
        Files fileDB = new Files();
        Path fileDBPath = Paths.get(storageProp.getLocation());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		CategoriesType category = new CategoriesType(1);
		if(fileService.beforeUpload(user.getId(), file.getSize()/(1024.0*1024.0))!=-1){
			 fileDB.setName(file.getOriginalFilename());
		        fileDB.setSize((double)file.getSize()/(1024.0*1024.0));
		        fileDB.setDateupload(new Date());
		        fileDB.setPath((file.getOriginalFilename()).toString());
		        fileDB.setUserId(user.getId());
		        fileDB.setIdType(category);
		        fileService.insertFile(fileDB);
		        storageService.store(file);
		        redirectAttributes.addFlashAttribute("message",
		                "You successfully uploaded " + file.getOriginalFilename() + "!");
		} else {
			redirectAttributes.addFlashAttribute("message",
	                "Fail to upload " + file.getOriginalFilename() + "!");
		}
       
        return "redirect:/upload/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}