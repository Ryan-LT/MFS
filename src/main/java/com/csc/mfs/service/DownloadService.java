package com.csc.mfs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.mfs.repository.DownloadRepository;

import com.csc.mfs.model.Download;

@Service
public class DownloadService {
	@Autowired
	private DownloadRepository downloadRepository;
	
	public List<Download> getAll(){
		return downloadRepository.findAll();
	}
	
	public Download getOne(int id){
		return downloadRepository.findOne(id);
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
}
