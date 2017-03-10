package com.csc.mfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.csc.mfs.storage.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class CscMiniFilesSharingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CscMiniFilesSharingApplication.class, args);
	}
}
