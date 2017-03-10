package com.csc.mfs.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.csc.mfs.model.Files;
import com.csc.mfs.model.User;
import com.csc.mfs.storage.StorageProperties;
import com.csc.mfs.storage.StorageService;

@Service
public class FileUploadService {
	
	private final StorageService storageService;  
	 
    @Autowired
    public FileUploadService(StorageService storageService) {
        this.storageService = storageService;
    }
    @Autowired
    private FileService fileService;
    
    @Autowired
    private StorageProperties storageProp;
    
    @Autowired
	private UserService userService;
	public void handleFileUpload( MultipartFile file) {
//      redirectAttributes.addFlashAttribute("message",
//      "You successfully uploaded " + file.getOriginalFilename() + "!");
//
//RedirectAttributes redirectAttributes
		storageService.store(file);
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
    }
}
