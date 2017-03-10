package com.csc.mfs.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.mfs.model.Files;
import com.csc.mfs.model.Rank;
import com.csc.mfs.model.User;
import com.csc.mfs.repository.FilesRepository;
import com.csc.mfs.repository.RankRepository;
import com.csc.mfs.repository.UserRepository;

@Service
public class FileService {
	@Autowired
	private FilesRepository fileRepository;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RankRepository rankRepository;
	
	public List<Files> getAll(){
		return fileRepository.findAll();
	}

	
	public void insertFile(Files file){
		//Files file_ = new Files("vuong.txt", "", 1234, new User(2), new Date());
		fileRepository.save(file);
	}

	public Files getFile(int id){
		return fileRepository.findOne(id);
	}
	
	public List<Files> getFileByUser(int idUser){
		User user = userRepository.findOne(idUser);
		return fileRepository.findByUserId(user);
	}
	
	
	public void delete(int id){
		fileRepository.delete(id);
	}
	
	public void updateUser(int idUser){
		fileRepository.UpdateUser(idUser);
	}
	
	
	public List<Files> searchFile(String infoFile, int page, int pageSize){
		int number = page*pageSize;
		return fileRepository.findByInfo(infoFile, number, pageSize);
	}
	
	public double sumSizeUploadInDay(int idUser){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
		Date date = new Date();
		try {
			date = sdf.parse(sdf.format(date));
			System.out.println(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(null!=fileRepository.sumSizeUploadInDay(idUser, date.getTime())){
			return (double)fileRepository.sumSizeUploadInDay(idUser, date.getTime());	
		} else {
			return 0d;
		}
	}
	
	public double totalSizeUpload(int idUser){
		if(null!=fileRepository.sumSizeUpload(idUser)){
			return (double)fileRepository.sumSizeUpload(idUser);	
		} else {
			return 0d;
		}
	}
	
	public double beforeUpload(int id, double sizeFile){
		User user = userRepository.findOne(id);
		Rank rank = rankRepository.findOne(user.getRank_Id());
		double inDay = sumSizeUploadInDay(id);
		if((inDay+sizeFile)>=rank.getSizeupload()){
			return -1;
		} else {
			return (rank.getSizeupload()-(inDay+sizeFile));
		}
	}
	
	public void afterUpload(int id, double sizeFile){
		User user = userRepository.findOne(id);
		Rank rank = rankRepository.findOne(user.getRank_Id());
		double inDay = sumSizeUploadInDay(id);
		if(user.getRank_Id()==3){
			return;
		} else {
			if((totalSizeUpload(id)+sizeFile)>rank.getSizeupload()){
				user.setRank_Id(user.getRank_Id()+1);
			}
		}
	}
	
	public void deleteFilesOfUser(int idUser){
		User user = userRepository.findOne(idUser);
		fileRepository.removeByUserId(user);
	}
	
	
}



















