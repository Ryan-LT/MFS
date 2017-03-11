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

@Service
public class DownloadService {
	@Autowired
	private DownloadRepository downloadRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RankRepository rankRepository;
	
	public List<Download> getAll(){
		return downloadRepository.findAll();
	}
	
	public long countDownload(){
		return downloadRepository.count();
	}
	
	public long countDownloadByUser(int idOfUser){
		return downloadRepository.countDownloadByUser(idOfUser);
	}
	
	public long countDownFile(int idFile){
		return downloadRepository.countDownloadFiles(idFile);
	}
	
	public Download getOne(int id){
		return downloadRepository.findOne(id);
	}
	
	public List<Download> findByUser(int idUser){
		if(null!=userRepository.findOne(idUser)){
			return (List<Download>)downloadRepository.findByIdUser(userRepository.findOne(idUser));
		}
		return null;
	}
	
	public void delete(int id){
		downloadRepository.delete(id);
	}
	
	public double totalDownloadInDay(int id){
		return downloadRepository.sumSizeDownload(id);
	}
	
	public void insert(Download download){
		downloadRepository.save(download);
	}
	
	public void deleteBUser(int id){
		User user = userRepository.findOne(id);
		downloadRepository.removeByIdUser(user);
	}
	
	
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
