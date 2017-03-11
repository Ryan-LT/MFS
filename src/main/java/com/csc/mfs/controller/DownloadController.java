package com.csc.mfs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.csc.mfs.model.Download;
import com.csc.mfs.service.DownloadService;

@RestController
@RequestMapping("/download")
public class DownloadController {
	@Autowired
	private DownloadService downloadService;
	
	@RequestMapping("/all")
	public List<Download> getAll(){
		return downloadService.getAll();
	}
	
	@RequestMapping(value="/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Download getOne(@PathVariable int id){
		return downloadService.getOne(id);
	}
	
	@RequestMapping(value="/getByUser/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Download> getByUser(@PathVariable int idUser){
		return downloadService.findByUser(idUser);
	}
	
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable int id){
		downloadService.delete(id);;
	}
	
	@RequestMapping("/deleteByUser/{id}")
	public void deleteByUser(@PathVariable int id){
		downloadService.deleteBUser(id);
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public void insert(@RequestBody Download download){
		downloadService.insert(download);
	}
	
	@RequestMapping(value="/total/{id}")
	public double total(@PathVariable int id){
		return downloadService.downloadInDay(id);
	}
	
	@RequestMapping(value="/countAllDownload")
	public double countDownload(){
		return downloadService.countDownload();
	}
	
	@RequestMapping(value="/countDownloadByUser/{idUser}")
	public double countDownloadByUser(@PathVariable int idUser){
		return downloadService.countDownloadByUser(idUser);
	}
	
	@RequestMapping(value="/countDownloadFile/{idFile}")
	public double countDownloadFile(@PathVariable int idFile){
		return downloadService.countDownFile(idFile);
	}
}
