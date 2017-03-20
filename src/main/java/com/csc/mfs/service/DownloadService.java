package com.csc.mfs.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.csc.mfs.repository.DownloadRepository;
import com.csc.mfs.repository.FilesRepository;
import com.csc.mfs.repository.RankRepository;
import com.csc.mfs.repository.UserRepository;

import com.csc.mfs.model.Download;
import com.csc.mfs.model.Files;
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
	@Autowired
	private FilesRepository fileRepository;
	
	/**
	 * This method is used to get total downloads of User
	 * @param idOfUser
	 * @return long
	 */
	public long countDownloadByUser(int idOfUser, Pageable pageable){
		User user = userRepository.findOne(idOfUser);
		if(null!=user){
			return downloadRepository.findByIdUser(user, pageable).getTotalElements();	
		}
		return 0;
		
	}
	
	/**
	 * This method is used to get total downloads file
	 * @param idFile
	 * @return long
	 */
	public long countDownFile(int idFile){
		Files file = fileRepository.findOne(idFile);
		if(null!=file){
			return downloadRepository.findByIdFile(file).size();
		}
		return 0;
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
	public Page<Download> findByUser(int idUser, Pageable pageable){
		User user = userRepository.findOne(idUser);
		PageRequest page = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Direction.DESC, "datedownload");
		if(null!=user){
			return downloadRepository.findByIdUser(user, page);
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
	 * This method is used to insert or update(id must really) record download
	 * @param download
	 */
	public void insert(Download download){
		downloadRepository.save(download);
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
		Object sum = downloadRepository.sumSizeDownloadInDay(idUser, date);
		if(null!=sum){
			return (double) sum;
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
	public double beforeDownload(int idUser, double sizeFile){
		User user = userRepository.findOne(idUser);
		if(null!=user){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			try {
				date = sdf.parse(sdf.format(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			double size = downloadInDay(idUser);
			Rank rank = rankRepository.findOne(user.getRankId().getId());
			if((size+sizeFile)>rank.getSizedownload()){
				return rank.getSizedownload()-size;
			} else if((size+sizeFile)==rank.getSizedownload()){
				return 0d;
			}
		}
		return -1.0d;
	}
}
