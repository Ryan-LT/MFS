package com.csc.mfs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csc.mfs.model.Files;
import com.csc.mfs.repository.FilesRepository;
import com.csc.mfs.service.FileService;

@RestController
@RequestMapping("/file")
public class FileController {
	@Autowired
	private FilesRepository fileReponsitory;

	@Autowired
	private FileService fileService;

	/**
	 * Insert file
	 * 
	 * @param file
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insertFile(@RequestBody Files file) {
		fileService.insertFile(file);
	}

	/**
	 * get all file
	 * 
	 * @return List<Files>
	 */
	@RequestMapping("/all")
	public Page<Files> getAll(Pageable pageable) {
		return fileService.getAll(pageable);
	}
	
	/**
	 * get all file of user by id user(pagination)
	 * 
	 * @param id
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/getByUser/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Files> getFileByUser(@PathVariable int idUser, Pageable pageable) {
		return fileService.getFileByUser(idUser, pageable);
	}
	
	/**
	 * Delete one file(not recommend because foreign key)
	 * 
	 * @param id
	 */
	@RequestMapping("/delete/{idFile}")
	public void delete(@PathVariable int idFile) {
		fileService.delete(idFile);
	}

	/**
	 * Get one file by id file
	 * 
	 * @param id
	 * @return File
	 */
	@RequestMapping(value = "/get/{idFile}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Files getFile(@PathVariable int idFile) {
		return fileService.getFile(idFile);
	}
	
	@RequestMapping(value = "/find/Size/{size}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Files> findBySize(@PathVariable double size, Pageable pageable) {
		return fileService.findBySize(size, pageable);
	}
	
	@RequestMapping(value = "/find/Name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Files> findByName(@PathVariable String name, Pageable pageable) {
		return fileService.findByNameLike(name, pageable);
	}
	
	@RequestMapping(value = "/find/Uploader/{lastName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Files> findByUploader(@PathVariable String lastName, Pageable pageable) {
		return fileService.findByUploader(lastName, pageable);
	}
	
	@RequestMapping(value = "/find/FileType/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Files> findByTypeFile(@PathVariable String type, Pageable pageable) {
		return fileService.findByFileType(type, pageable);
	}
	
	@RequestMapping(value = "/find/Category/{category}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Files> findByCategory(@PathVariable String category, Pageable pageable) {
		return fileService.findByCategory(category, pageable);
	}
	
	@RequestMapping(value = "/find/All/{info}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Files> findByAll(@PathVariable String info, Pageable pageable) {
		return fileService.findByAll(info, pageable);
	}
	
	@RequestMapping("/updateSharing/{idFile}")
	public void updateSharing(@PathVariable("idFile") int idFile) {
		fileService.updateSharing(idFile);;
	}
	
	@PutMapping("/updateDescription")
	public void updateDescription(@RequestParam("idFile") int idFile, @RequestParam("description") String description) {
		fileService.updateDescription(idFile, description);
	}
	
}