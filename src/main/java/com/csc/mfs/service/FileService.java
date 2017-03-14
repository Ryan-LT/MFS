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
	
	/**
	 * Get all file uploaded
	 * @return List<Files>
	 */
	public List<Files> getAll(){
		return fileRepository.findAll();
	}
	
	/**
	 * Insert or update file
	 * @param file
	 */
	public void insertFile(Files file){
		//Files file_ = new Files("vuong.txt", "", 1234, new User(2), new Date());
		file.setActive(1);
		fileRepository.save(file);
	}

	/**
	 * Get one file by id
	 * @param id
	 * @return Files
	 */
	public Files getFile(int id){
		return fileRepository.findOne(id);
	}
	
	/**
	 * get file have most downloaded
	 * @return List<Files>
	 */
	public List<Files> getBestDownload(){
		return fileRepository.getBestDownload();
	}
	
	/**
	 * Get file by user id(pagination)
	 * @param idUser
	 * @param page
	 * @param pageSize
	 * @return List<Files>
	 */
	public List<Files> getFileByUser(int idUser, int page, int pageSize){
		int count = page*pageSize;
		return fileRepository.findFileActiveByUserId(idUser, count, pageSize);
	}
	
	/**
	 * Get total file user uploaded which still active
	 * @param idUser
	 * @return long
	 */
	public long countFileOfUser(int idUser){
		return fileRepository.countFileActiveByUserId(idUser);
	}
	
	/**
	 * Get total file
	 * @return long
	 */
	public long countFile(){
		return fileRepository.countFile();
	}
	
	/**
	 * Delete file(set active =0... foreign key)
	 * @param id
	 */
	public void delete(int id){
		//fileRepository.delete(id);
		fileRepository.updateActive(id);
	}
	
	/**
	 * Set id_user = 0(foreign key).. delete user
	 * @param idUser
	 */
	public void updateUser(int idUser){
		fileRepository.UpdateUser(idUser);
	}
	
	
	/**
	 * Search file
	 * @param infoFile
	 * @param page
	 * @param pageSize
	 * @return List<Files>
	 */
	public List<Files> searchFile(String infoFile, int page, int pageSize){
		int number = page*pageSize;
		return fileRepository.findByInfo(infoFile, number, pageSize);
	}
	
	/**
	 * Get total size upload in day of user
	 * @param idUser
	 * @return
	 */
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
	
	/**
	 * Get total size upload of user
	 * @param idUser
	 * @return double
	 */
	public double totalSizeUpload(int idUser){
		if(null!=fileRepository.sumSizeUpload(idUser)){
			return (double)fileRepository.sumSizeUpload(idUser);	
		} else {
			return 0d;
		}
	}
	
	/**
	 * Check condition before upload
	 * @param id
	 * @param sizeFile
	 * @return
	 */
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
	
	/**
	 * Set rank(condition) after upload
	 * @param id
	 * @param sizeFile
	 */
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
	
	/**
	 * Delete file of User
	 * @param idUser
	 */
	public void deleteFilesOfUser(int idUser){
		User user = userRepository.findOne(idUser);
		fileRepository.removeByUserId(user);
	}
	
	/**
	 * Get all file(pagination)
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Files> getAllFilePagination(int page, int pageSize){
		return fileRepository.getAllFilePagination(page*pageSize, pageSize);
	}
	
	public long countSearch(String infoFile){
		return fileRepository.countSearch(infoFile);
	}
	
}



















