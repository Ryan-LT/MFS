package com.csc.mfs.controller;

import com.csc.mfs.storage.StorageFileNotFoundException;
import com.csc.mfs.storage.StorageProperties;
import com.csc.mfs.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.csc.mfs.service.FileService;
import com.csc.mfs.service.FileUploadService;
import com.csc.mfs.service.UserService;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    private final StorageService storageService;  
 
    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }
    @Autowired
    private FileService fileService;
    
    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private StorageProperties storageProp;
    
    @Autowired
	private UserService userService;
    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
    	fileUploadService.handleFileUpload(file);
        return "You have sucessfully upload" +file.getOriginalFilename();
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
