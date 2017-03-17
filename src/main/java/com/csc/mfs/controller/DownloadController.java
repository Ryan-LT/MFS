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
	
	/**
	 * Get all record download
	 * @return List<Download>
	 */
	@RequestMapping("/all")
	public List<Download> getAll(){
		return downloadService.getAll();
	}
	
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
	public List<Download> getByUser(@PathVariable int idUser){
		return downloadService.findByUser(idUser);
	}
	
	/**
	 * delete one record by id
	 * @param id
	 */
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable int id){
		downloadService.delete(id);;
	}
	
	/**
	 * Delete all download record of user by user id
	 * @param id
	 */
	@RequestMapping("/deleteByUser/{id}")
	public void deleteByUser(@PathVariable int id){
		downloadService.deleteBUser(id);
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
		return downloadService.downloadInDay(idUser);
	}
	
	/**
	 * get total downloads
	 * @return double
	 */
	@RequestMapping(value="/countAllDownload")
	public double countDownload(){
		return downloadService.countDownload();
	}
	
	/**
	 * Get total download of user
	 * @param idUser
	 * @return double
	 */
	@RequestMapping(value="/countDownloadByUser/{idUser}")
	public double countDownloadByUser(@PathVariable int idUser){
		return downloadService.countDownloadByUser(idUser);
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
	
	@RequestMapping("/history/{idUser}/{page}/{pageSize}")
	public Object getHistoryDownload(@PathVariable int idUser, @PathVariable int page, @PathVariable int pageSize){
		return downloadService.getDownloadOfuser(idUser, page, pageSize);
	}
	
	@RequestMapping("/countHistory/{idUser}")
	public long countHistoryDownload(@PathVariable int idUser){
		return downloadService.countDownloadByUser(idUser);
	}
	
	@RequestMapping("/getAll")
	public List<Download> getAll1(){
		return downloadService.getAll();
	}
}
