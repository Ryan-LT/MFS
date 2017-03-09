package com.csc.mfs.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.mfs.model.Files;
import com.csc.mfs.repository.FilesRepository;

@Service
public class FileService {
	@Autowired
	private FilesRepository fileRepository;
	
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
	
	public void delete(int id){
		fileRepository.delete(id);
	}
	
	public List<Files> searchFile(String infoFile){
		return fileRepository.findByInfo(infoFile);
	}
	
	public double sumSizeUploadInDay(int idUser, Date dateUpload){
		return fileRepository.sumSizeUploadInDay(idUser, dateUpload);
	}
}
