package com.csc.mfs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.csc.mfs.model.Rank;
import com.csc.mfs.service.RankService;

@RestController
@RequestMapping("rank")
public class RankController {
	
	@Autowired
	private RankService rankService;
	
	
	@RequestMapping("/all")
	public ResponseEntity<List<Rank>> getAll(){
		 return ResponseEntity
	                .ok()
	                .body((List<Rank>) rankService.getAll());
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
	public ResponseEntity<Rank> getOne(@PathVariable int id){
		return ResponseEntity
                .ok()
                .body(rankService.getOne(id));
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void update(@RequestBody Rank rank){
		rankService.update(rank);
	}
}
