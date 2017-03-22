package com.csc.mfs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.csc.mfs.reduceSizeOfSource.CleanFile;

@Configuration
@EnableScheduling
public class AppConfigScheduled {
	
	@Bean
	public CleanFile cleanFile(){
		return new CleanFile();
	}
}
