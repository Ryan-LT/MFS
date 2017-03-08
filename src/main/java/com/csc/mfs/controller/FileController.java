package com.csc.mfs.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable int id){
		fileService.delete(id);
	}
	
	@RequestMapping("/fSearch/{infoFile}")
	public List<Files> searchFile(@PathVariable String infoFile){
		return fileService.searchFile(infoFile);
	}
	
	@RequestMapping("/sumSizeUpload/{id}")
	public double sumSizeUpload(@PathVariable int id){
		System.out.println(new Date());
		return fileService.sumSizeUploadInDay(id, new Date());
	}
	

}










