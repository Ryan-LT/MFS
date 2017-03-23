package com.csc.mfs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
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
	
	public Page<Files> getAll(Pageable pageable){
		PageRequest page = new PageRequest( pageable.getPageNumber(), pageable.getPageSize(), Direction.DESC, "dateupload");
		return fileRepository.findBySharingAndActive(1, 1, page);
	}
	
	/**
	 * Get one file by id
	 * @param id
	 * @return Files
	 */
	public Files getFile(int idFile){
		return fileRepository.findOne(idFile);
	}
	
	/**
	 * Get file by user id(pagination)
	 * @param idUser
	 * @param page
	 * @param pageSize
	 * @return List<Files>
	 */
	public Page<Files> getFileByUser(int idUser, Pageable pageable){
		User user = userRepository.findOne(idUser); 
		PageRequest page = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Direction.DESC, "dateupload");
		if(user != null){
			return fileRepository.findByUserIdAndActive(user, 1, page);	
		} else {
			return null;
		}
	}
	
	/**
	 * Delete file(set active =0... foreign key)
	 * @param id
	 */
	public void delete(int idFile){
		Files file = fileRepository.findOne(idFile);
		if(file!=null){
			file.setActive(0);
			fileRepository.flush();
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
	public double beforeUpload(int idUser, double sizeFile){
		User user = userRepository.findOne(idUser);
		Rank rank = null;
		if(user!=null){
			rank = rankRepository.findOne(user.getRankId().getId());
			if(sizeFile>=rank.getSizeupload()){
				return -1;
			}
		}
		return 1;
	}
	
	/**
	 * Set rank(condition) after upload
	 * @param id
	 * @param sizeFile
	 */
	public void afterUpload(int idUser, double sizeFile){
		User user = userRepository.findOne(idUser);
		Rank rank = rankRepository.findOne(user.getRankId().getId());
		if("gold".equals(user.getRankId().getName().toLowerCase())){
		} else {
			if(totalSizeUpload(idUser)>rank.getSizerank()){
				user.setRankId(rankRepository.findOne(user.getRankId().getId()+1));
				userRepository.flush();
			}
		}
	}
	
	/**
	 * Insert or update file
	 * @param file
	 */
	public void insertFile(Files file){
		//file.setActive(1);
		fileRepository.save(file);
	}
	
	
	public Page<Files> findBySize(double size, Pageable pageable){
		PageRequest page = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Direction.DESC, "dateupload");
		return fileRepository.findBySizeIsLessThanEqualAndSharing(size,1 , page);
	}
	
	public Page<Files> findByNameLike(String name, Pageable pageable){
		PageRequest page = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Direction.DESC, "dateupload");
		name = checkData(name);
		return fileRepository.findByNameContainingAndActiveAndSharing(name, 1, 1, page);
	}
	
	public Page<Files> findByUploader(String lastName, Pageable pageable){
		PageRequest page = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Direction.DESC, "dateupload");
		lastName = checkData(lastName);
		return fileRepository.findByUserIdLastNameContainingAndActiveAndSharing(lastName, 1, 1, page);
	}
	
	public Page<Files> findByFileType(String typeFile, Pageable pageable){
		PageRequest page = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Direction.DESC, "dateupload");
		typeFile = checkData(typeFile);
		return fileRepository.findByIdTypeFileTypeContainingAndActiveAndSharing(typeFile, 1, 1, page);
	}
	
	public Page<Files> findByCategory(String category, Pageable pageable){
		PageRequest page = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Direction.DESC, "dateupload");
		category = checkData(category);
		return fileRepository.findByIdTypeCategoryIdNameAndActiveAndSharing(category, 1, 1, page);
	}
	
	public Page<Files> findByAll(String info, Pageable pageable){
		PageRequest page = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Direction.DESC, "dateupload");
		info = checkData(info);
		double size =0;
		try {
			info+="";
			size = Integer.parseInt(info);
		} catch (Exception e) {
		}
		//return fileRepository.findBySharingAndActiveOrNameContainingOrUserIdLastNameContainingOrSizeLessThanEqualOrIdTypeFileTypeContaining(1, 1, info, info, size, info, pageable);
		return fileRepository.findBySharingAndActiveAndNameContainingOrSharingAndActiveAndUserIdLastNameContainingOrSharingAndActiveAndSizeLessThanEqualOrSharingAndActiveAndIdTypeFileTypeContainingOrSharingAndActiveAndIdTypeCategoryIdName(1, 1, info, 1, 1, info, 1, 1, size, 1, 1, info, 1, 1, info,page);
	}
	
	public String checkData(String info){
		if(info==null || info.equals("undefined")){
			return "";
		}
		return info;
	}
	
	
	public void updateSharing(Integer idFile){
		Files file = fileRepository.findOne(idFile);
		if(null!=file){
			if(file.getSharing()!=1){
				file.setSharing(1);
			} else {
				file.setSharing(0);
			}
		}
		fileRepository.flush();
	}
	
	public void updateDescription(Integer idFile, String content){
		Files file = fileRepository.getOne(idFile);
		if(null!=file){
			file.setDescription(content);
		}
		fileRepository.flush();
	}
	
	/**
	 * Get total file user uploaded which still active
	 * @param idUser
	 * @return long
	 */
	public long countFileOfUser(int idUser, Pageable pageable){
		User user = userRepository.findOne(idUser);
		return fileRepository.findByUserIdAndActive(user, 1, pageable).getTotalElements();
	}
	
	/**
	 * Get total file
	 * @return long
	 */
	public long countFile(){
		return fileRepository.count();
	}
	
	
}