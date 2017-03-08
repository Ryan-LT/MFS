package com.csc.mfs.repository;



import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.csc.mfs.model.Files;

public interface FilesRepository extends JpaRepository<Files, Integer> {
	@Query(value="SELECT * FROM files WHERE name like %:fileInfo%"
			+ " OR size like %:fileInfo% OR"
			+ " user_id IN (SELECT id FROM user WHERE name=:fileInfo)" , nativeQuery=true)
	List<Files> findByInfo(@Param("fileInfo") String fileInfo);
	
	@Query(value="SELECT SUM(size) FROM files WHERE user_id=:idUser and dateupload=:dateUpload" , nativeQuery=true)
	double sumSizeUploadInDay(@Param("idUser") int idUser, @Param("dateUpload") Date dateUpload);
	
}
/*
 * like %:fileInfo % OR"
			+ " size like %:fileInfo% OR "
			+ "user_id IN (SELECT id FROM user WHERE name=:fileInfo')"
 */
