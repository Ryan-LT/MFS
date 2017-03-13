package com.csc.mfs.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.mfs.repository.DownloadRepository;
import com.csc.mfs.repository.RankRepository;
import com.csc.mfs.repository.UserRepository;
import com.csc.mfs.model.Download;
import com.csc.mfs.model.Rank;
import com.csc.mfs.model.User;

/**
 * This class is service, provide methods interactive db(table download)
 * @author VuMin
 *
 */
@Service
public class DownloadService {
	
	@Autowired
	private DownloadRepository downloadRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RankRepository rankRepository;
	
	/**
	 * This method is used to get all record in download table
	 * @return List<Download>
	 */
	public List<Download> getAll(){
		return downloadRepository.findAll();
	}
	
	/**
	 * This method is used to get total record in download table 
	 * @return long
	 */
	public long countDownload(){
		return downloadRepository.count();
	}
	
	/**
	 * This method is used to get total downloads of User
	 * @param idOfUser
	 * @return long
	 */
	public long countDownloadByUser(int idOfUser){
		return downloadRepository.countDownloadByUser(idOfUser);
	}
	
	/**
	 * This method is used to get total downloads file
	 * @param idFile
	 * @return long
	 */
	public long countDownFile(int idFile){
		return downloadRepository.countDownloadFiles(idFile);
	}
	
	/**
	 * This method is used to get one record in table download
	 * @param id
	 * @return Download
	 */
	public Download getOne(int id){
		return downloadRepository.findOne(id);
	}
	
	/**
	 * This method is used to get all record download of user
	 * @param idUser
	 * @return List<Download>
	 */
	public List<Download> findByUser(int idUser){
		if(null!=userRepository.findOne(idUser)){
			return (List<Download>)downloadRepository.findByIdUser(userRepository.findOne(idUser));
		}
		return null;
	}
	
	/**
	 * This method is used to delete one record by id
	 * @param id
	 */
	public void delete(int id){
		downloadRepository.delete(id);
	}
	
	/**
	 * This method if get total size files which user download in day
	 * @param idUser
	 * @return double
	 */
	public double totalDownloadInDay(int idUser){
		if(null!=downloadRepository.sumSizeDownload(idUser)){
			return (double)downloadRepository.sumSizeDownload(idUser);
		} else {
			return 0d;
		}
	}
	
	/**
	 * This method is used to insert or update(id must really) record download
	 * @param download
	 */
	public void insert(Download download){
		downloadRepository.save(download);
	}
	
	/**
	 * This method is used to delete all download record of user
	 * @param id
	 */
	public void deleteBUser(int id){
		User user = userRepository.findOne(id);
		downloadRepository.removeByIdUser(user);
	}
	
	/**
	 * This method is used to get total downloads in day of user
	 * @param idUser
	 * @return double 
	 */
	public double downloadInDay(int idUser){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = sdf.parse(sdf.format(date));//sdf.format(date)
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(date);
		System.out.println(date.getTime());
		if(null!=downloadRepository.sumSizeDownloadInDay(idUser, date.getTime())){
			return (double) downloadRepository.sumSizeDownloadInDay(idUser, date.getTime());
		} else {
			return 0;
		}
	}
	
	
	/**
	 * This method is used to check condition to download file of user
	 * @param idUser
	 * @param sizeFile
	 * @return boolean
	 */
	public boolean beforeDownload(int idUser, double sizeFile){
		User user = userRepository.findOne(idUser);
		if(null!=user){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
			Date date = new Date();
			try {
				date = sdf.parse(sdf.format(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			double size = 0;
			if(null!=downloadRepository.sumSizeDownloadInDay(idUser, date.getTime())){
				size = (double) downloadRepository.sumSizeDownloadInDay(idUser, date.getTime());
			}
			Rank rank = rankRepository.findOne(user.getRank_Id());
			if(size+sizeFile>=rank.getSizedownload()){
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
}
