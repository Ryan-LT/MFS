package com.csc.mfs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.csc.mfs.model.Files;

public interface FilesRepository extends JpaRepository<Files, Integer> {
	@Query(value="SELECT * FROM files WHERE name like %:fileInfo%"
			+ " OR size like %:fileInfo% OR"
			+ " user_id IN (SELECT id FROM user WHERE name=:fileInfo) LIMIT :number, :pageSize" , nativeQuery=true)
	List<Files> findByInfo(@Param("fileInfo") String fileInfo, @Param("number") int number, @Param("pageSize") int pageSize);
	
	@Query(value="SELECT SUM(size) FROM files WHERE user_id=:idUser and "
			+ "(UNIX_TIMESTAMP(dateupload)*1000)=:dateUpload" , nativeQuery=true)
	Object sumSizeUploadInDay(@Param("idUser") int idUser, @Param("dateUpload") Long dateUpload);
	
	@Query(value="SELECT SUM(size) FROM files WHERE user_id=:idUser", nativeQuery=true)
	Object sumSizeUpload(@Param("idUser") int idUser);
	
}
/*
 * like %:fileInfo % OR"
			+ " size like %:fileInfo% OR "
			+ "user_id IN (SELECT id FROM user WHERE name=:fileInfo')"
 */
