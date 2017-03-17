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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @RequestMapping(value="/upload", method = RequestMethod.GET)
    public String listUploadedFiles(Model model) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
        model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));
                 
        return "upload";
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
 		
 		Files fileDownload = fileRepo.findOne(idFile);
 			downLoad.setIdFile(fileDownload);
 	 		downLoad.setIdUser(user);
 	 		
 	 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 			Date date = new Date();
 			try {
 				date = sdf.parse(sdf.format(date));//sdf.format(date)
 			} catch (ParseException e) {
 				e.printStackTrace();
 			}
 			
 	 		downLoad.setDatedownload(date);
 	 		downloadService.insert(downLoad);
 	        Resource file = storageService.loadAsResource(fileDownload.getPath());
 	        return ResponseEntity
 	                .ok()
 	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+fileDownload.getName()+"\"")
 	                .body(file);
    }
    
    @GetMapping("/download/check/{idFile}")
    public ResponseEntity<Double> checkBeforeDownload(@PathVariable Integer idFile) {
    	//get current log-in user
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
 		User user = userService.findUserByEmail(auth.getName());
 		Files fileDownLoad = fileRepo.findOne(idFile);
 		
 		// check if the user have not reach the maximum download per day.
 		double checkData = downloadService.beforeDownload(user.getId(), fileDownLoad.getSize()); 
 		if(checkData>0)
 		{
 	        return ResponseEntity
                .ok()
                .body(checkData);
 		}
 		return ResponseEntity
	                .ok()
	                .body(-1.0);
        
    }
    
    
    

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile fileList[] ,
    								@RequestParam("description") String description
                                  , RedirectAttributes redirectAttributes) {
    	/**
    	 * 
    	 * Retrieve user information.
    	 */
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		/**
		 * Verify if user still has enough upload space.
		 */			
    	double sumFileSize=0.0; // the sum of file sizes
    	for(MultipartFile file: fileList){
    		sumFileSize +=file.getSize();
    	}
    	Double spaceAvailable = fileService.beforeUpload(user.getId(),sumFileSize/1024.0 );
    	/**
    	 * Store files and respond message.
    	 */
    	if(spaceAvailable>=0){
    		String notice=""; // File names to print message
    		
    		for(MultipartFile file: fileList){
    			CategoriesType category = new CategoriesType(1);
    			Files fileDB = new Files();
    			fileDB.setName(file.getOriginalFilename());
		        fileDB.setSize((double)file.getSize()/1024.0);
		        fileDB.setDateupload(new Date());
		        fileDB.setPath((file.getOriginalFilename()).toString()+(new Date()).getTime());
		        fileDB.setUserId(user.getId());
		        fileDB.setIdType(category);
		        fileDB.setDescription(description);
		        fileService.insertFile(fileDB);
		        storageService.store(file,Paths.get(fileDB.getPath()));
		        fileService.afterUpload(user.getId(), file.getSize()/1024.0);
		        notice+=file.getOriginalFilename()+" ";
    		}
    		String message="You have successfully uploaded your files";
    		System.out.println(message);
    		redirectAttributes.addFlashAttribute("uploadMessage",message);
    		System.out.println(redirectAttributes.toString());
		} else {
			redirectAttributes.addFlashAttribute("message",
	                "You files have exceeded limitation! Please choose smaller files.");
		}
        return "redirect:/upload";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}