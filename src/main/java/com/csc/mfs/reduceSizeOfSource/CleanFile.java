package com.csc.mfs.reduceSizeOfSource;

import java.nio.file.*;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.csc.mfs.repository.FilesRepository;

public class CleanFile {
	@Autowired
	private FilesRepository filesRepository;
	//@SuppressWarnings("deprecation")
	//@Scheduled(initialDelay=5000 ,fixedDelay=1000)
	@Scheduled(cron="0 0 0 * * *")//second minutes hour date year OPTION(MON_FRI)
	public void cleanFile(){
		System.out.println(new Date());
		List<com.csc.mfs.model.Files> files = filesRepository.findAll();
		 //Files.createFile(Paths.get("upload\\DragandDrop.png1490087110396"));
		for (com.csc.mfs.model.Files file : files) {
			if(file.getActive()==0){
				try{
					Path fileToDeletePath = Paths.get("upload\\"+file.getPath());
					Files.delete(fileToDeletePath);	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	@Scheduled(cron="0 0 10 * * *")//second minutes hour date year OPTION(MON_FRI)
	public void showData(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(new Date());
	}
}
