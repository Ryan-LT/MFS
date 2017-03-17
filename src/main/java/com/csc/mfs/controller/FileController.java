package com.csc.mfs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		// Files file_ = new Files("vuong.txt", "", 1234, new User(2), new
		// Date());
		fileReponsitory.save(file);
	}

	/**
	 * get all file
	 * 
	 * @return List<Files>
	 */
	@RequestMapping("/all")
	public List<Files> getAll() {
		return fileService.getAll();
	}

	/**
	 * get all file(pagination)
	 * 
	 * @return List<Files>
	 */
	@RequestMapping(value = "/allPagination/{page}/{pageSize}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Object> getAllFilePagination(@PathVariable int page, @PathVariable int pageSize) {
		return fileService.getAllFilePagination(page, pageSize);
	}

	/**
	 * Get one file by id file
	 * 
	 * @param id
	 * @return File
	 */
	@RequestMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Files getFile(@PathVariable int id) {
		System.out.println(fileReponsitory.findOne(id).getUserId());
		return fileService.getFile(id);
	}

	/**
	 * get all file of user by id user(pagination)
	 * 
	 * @param id
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/getByUser/{id}/{page}/{pageSize}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Files> getFileByUser(@PathVariable int id, @PathVariable int page, @PathVariable int pageSize) {
		return fileService.getFileByUser(id, page, pageSize);
	}

	/**
	 * Get total file upload of user
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/countFileOfUser/{id}")
	public long countFileByUser(@PathVariable int id) {
		return fileService.countFileOfUser(id);
	}
	
	/**
	 * Get total file
	 * 
	 * @return
	 */
	@RequestMapping(value = "/countFile")
	public long countFile() {
		return fileService.countFile();
	}

	/**
	 * Delete one file(not recommend because foreign key)
	 * 
	 * @param id
	 */
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable int id) {
		fileService.delete(id);
	}

	/**
	 * Delete file of user(not recommend)
	 * 
	 * @param id
	 */
	@RequestMapping("/deleteFUser/{id}")
	public void deleteFilesOfUser(@PathVariable int id) {
		fileService.deleteFilesOfUser(id);
	}

	/**
	 * update user_id =0 when user is deleted
	 * 
	 * @param id
	 */
	@RequestMapping("/updateUFile/{id}")
	public void UpdateFileOfUser(@PathVariable int id) {
		fileService.updateUser(id);
	}

	/**
	 * Search file
	 * 
	 * @param infoFile
	 * @param page
	 * @param pageSize
	 * @return List<Files>
	 */
	@RequestMapping("/fSearch/{infoFile}")
	public Page<Object> searchFile(@PathVariable("infoFile") String infoFile, Pageable pageable) {
		return fileService.searchFile(infoFile, pageable);
	}
	
	/**
	 * search by category
	 * @param infoFile
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/searchOption/{type}/{file}")
	public Page<Object> fSearchByCategory(@PathVariable("file") String file, @PathVariable("type") int type, Pageable pageable) {
		if(type==0){
			return fileService.searchFile(file, pageable);
		} else if(type==1){
			return fileService.findByInfoCategory(file, pageable);
		} else if(type==2){
			return fileService.findByInfoName(file, pageable);
		} else if(type==3){
			return fileService.findByInfoUploader(file, pageable);
		} else {
			return fileService.findByInfoSize(Integer.parseInt(file), pageable);
		}
	}

	/**
	 * get total size file upload in day
	 * 
	 * @param id
	 * @return double
	 */
	@RequestMapping("/sumSizeUpload/{id}")
	public double sumSizeUpload(@PathVariable int id) {
		return fileService.sumSizeUploadInDay(id);
	}

	/**
	 * Get files has most downloaded
	 * 
	 * @return
	 */
	@RequestMapping("/getBestDownload")
	public List<Files> getBesDownload() {
		return fileService.getBestDownload();
	}
	
	@RequestMapping("/updateSharing/{idFile}")
	public void updateSharing(@PathVariable("idFile") int idFile) {
		fileService.updateSharing(idFile);;
	}
	
	@RequestMapping("/getFileByCategory/{nameCategory}")
	public Page<Object> getFileByCategory(@PathVariable String nameCategory, Pageable pageable) {
		return fileService.getFileByCategory(nameCategory, pageable);
	}
	
}