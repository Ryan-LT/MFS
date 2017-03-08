package com.csc.mfs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.mfs.model.Rank;
import com.csc.mfs.repository.RankRepository;


@Service
public class RankService {
	@Autowired
	private RankRepository rankRepository;
	
	
	public List<Rank> getAll(){
		return (List<Rank>) rankRepository.findAll();
	}
	
	public void delete(int id){
		rankRepository.delete(id);
	}
	
	public void insert(Rank rank){
		rankRepository.save(rank);
	}
	
	public Rank getOne(int id){
		return rankRepository.findOne(id);
	}
	
	public void sizeUpload(int id, double size){
		Rank rank = rankRepository.findOne(id);
		rank.setSizeupload(size);
		rankRepository.flush();
	}
	
	public void sizeDownload(int id, double size){
		Rank rank = rankRepository.findOne(id);
		rank.setSizedownload(size);
		rankRepository.flush();
	}
	
	public void sizeRank(int id, double size){
		Rank rank = rankRepository.findOne(id);
		rank.setSizerank(size);
		rankRepository.flush();
	}
	
}
