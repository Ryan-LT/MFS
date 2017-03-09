package com.csc.mfs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.csc.mfs.model.Rank;
import com.csc.mfs.repository.RankRepository;
import com.csc.mfs.service.RankService;

@RestController
@RequestMapping("rank")
public class RankController {
	
	@Autowired
	private RankRepository rankRepository;
	
	@Autowired
	private RankService rankService;
	
	
	@RequestMapping("/all")
	public List<Rank> getAll(){
		return (List<Rank>) rankService.getAll();
	}
	
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable int id){
		rankService.delete(id);
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public void insert(@RequestBody Rank rank){
		rankService.insert(rank);
	}
	
	@RequestMapping(value="/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Rank getOne(@PathVariable int id){
		return rankService.getOne(id);
	}
	
	@RequestMapping("/updateSizeUpload/{id}/{size}")
	public void sizeUpload(@PathVariable int id, @PathVariable double size){
		rankService.sizeUpload(id, size);
	}
	
	@RequestMapping("/updateSizeDownload/{id}/{size}")
	public void sizeDownload(@PathVariable int id, @PathVariable double size){
		rankService.sizeDownload(id, size);
	}
	
	@RequestMapping("/sizerank/{id}/{size}")
	public void sizeRank(@PathVariable int id, @PathVariable double size){
		rankService.sizeRank(id, size);
	}
	
	
	
}
