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

	public List<Rank> getAll() {
		return (List<Rank>) rankRepository.findAll();
	}

	public void delete(int id) {
		rankRepository.delete(id);
	}

	public void insert(Rank rank) {
		Rank rank_ = rankRepository.findByName(rank.getName());
		if(rank_!=null){
			rankRepository.save(rank);	
		}
	}

	public Rank getOne(int id) {
		return rankRepository.findOne(id);
	}

	public Rank update(Rank rank) {
		Rank rank_ = rankRepository.findOne(rank.getId());
		if(rank_!=null){
			rankRepository.save(rank);
			rankRepository.flush();
			return rank;
		} else {
			return null;
		}
	}

}
