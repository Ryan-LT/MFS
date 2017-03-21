package com.csc.mfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

import com.csc.mfs.storage.StorageProperties;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties(StorageProperties.class)
public class CscMiniFilesSharingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CscMiniFilesSharingApplication.class, args);
	}
}
