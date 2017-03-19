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

import com.csc.mfs.model.Download;
import com.csc.mfs.service.DownloadService;

@RestController
@RequestMapping("/download")
public class DownloadController {
	@Autowired
	private DownloadService downloadService;
	
	/**
	 * Get one record download by id record
	 * @param id
	 * @return Download
	 */
	@RequestMapping(value="/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Download getOne(@PathVariable int id){
		return downloadService.getOne(id);
	}
	
	/**
	 * Get list download record of user(all)
	 * @param idUser
	 * @return List<Download>
	 */
	@RequestMapping(value="/getByUser/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Download> getByUser(@PathVariable int idUser, Pageable pageable){
		return downloadService.findByUser(idUser, pageable);
	}
	
	/**
	 * delete one record by id
	 * @param id
	 */
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable int id){
		downloadService.delete(id);
	}
	
	/**
	 * Insert or update one record into download table
	 * @param download
	 */
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public void insert(@RequestBody Download download){
		downloadService.insert(download);
	}
	
	/**
	 * Get total size file download in day of user
	 * @param id
	 * @return double
	 */
	@RequestMapping(value="/total/{idUser}")
	public double total(@PathVariable int idUser){
		return downloadService.countDownFile(idUser);
	}
	
	
	/**
	 * Get total download of user
	 * @param idUser
	 * @return double
	 */
	@RequestMapping(value="/countDownloadByUser/{idUser}")
	public long countDownloadByUser(@PathVariable int idUser, Pageable pageable){
		return downloadService.countDownloadByUser(idUser, pageable);
	}
	
	/**
	 * Get total downloads of file
	 * @param idFile
	 * @return double
	 */
	@RequestMapping(value="/countDownloadFile/{idFile}")
	public double countDownloadFile(@PathVariable int idFile){
		return downloadService.countDownFile(idFile);
	}
}
