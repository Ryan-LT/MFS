package com.csc.mfs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
	
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public void insertFile(@RequestBody Files file){
		//Files file_ = new Files("vuong.txt", "", 1234, new User(2), new Date());
		fileReponsitory.save(file);
	}
	
	@RequestMapping("/all")
	public List<Files> getAll(){
		return fileService.getAll();
	}
	
	
	@RequestMapping(value="/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Files getFile(@PathVariable int id){
		return fileService.getFile(id);
	}

	@RequestMapping(value="/getByUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Files> getFileByUser(@PathVariable int id){
		return fileService.getFileByUser(id);
	}
	
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable int id){
		fileService.delete(id);
	}
	
	@RequestMapping("/deleteFUser/{id}")
	public void deleteFilesOfUser(@PathVariable int id){
		fileService.deleteFilesOfUser(id);
	}
	
	@RequestMapping("/updateUFile/{id}")
	public void UpdateUserOfUser(@PathVariable int id){
		fileService.updateUser(id);
	}
	
	@RequestMapping("/fSearch/{infoFile}/{page}/{pageSize}")
	public List<Files> searchFile(@PathVariable("infoFile") String infoFile, @PathVariable("page") int page,
			@PathVariable("pageSize") int pageSize){
		return fileService.searchFile(infoFile, page, pageSize);
	}
	
	@RequestMapping("/sumSizeUpload/{id}")
	public double sumSizeUpload(@PathVariable int id){
		return fileService.sumSizeUploadInDay(id);
	}
	

}










