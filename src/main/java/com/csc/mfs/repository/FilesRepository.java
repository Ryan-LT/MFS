package com.csc.mfs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.csc.mfs.model.Files;
import com.csc.mfs.model.User;

public interface FilesRepository extends JpaRepository<Files, Integer> {
	@Query(value="SELECT * FROM files WHERE active=1 AND (name like %:fileInfo%"
			+ " OR size like %:fileInfo% OR"
			+ " user_id IN (SELECT id FROM user WHERE name=:fileInfo)) LIMIT :number, :pageSize" , nativeQuery=true)
	List<Files> findByInfo(@Param("fileInfo") String fileInfo, @Param("number") int number, @Param("pageSize") int pageSize);
	
	@Query(value="SELECT SUM(size) FROM files WHERE active=1 AND user_id=:idUser and "
			+ "(UNIX_TIMESTAMP(dateupload)*1000)=:dateUpload" , nativeQuery=true)
	Object sumSizeUploadInDay(@Param("idUser") int idUser, @Param("dateUpload") Long dateUpload);
	
	@Query(value="SELECT SUM(size) FROM files WHERE active=1 AND user_id=:idUser", nativeQuery=true)
	Object sumSizeUpload(@Param("idUser") int idUser);
	
	@Transactional
	void removeByUserId(User user);
	 
	@Transactional
	@Modifying
	@Query(value="UPDATE files SET user_id= 0 WHERE user_id=:idUser", nativeQuery=true)
	void UpdateUser(@Param("idUser") int idUser);

	List<Files> findByUserId(User user);
	
	@Query(value="SELECT * FROM files WHERE active=1 AND user_id=:idUser"
			+ " LIMIT :number, :pageSize", nativeQuery=true)
	List<Files> findFileActiveByUserId(@Param("idUser") int idUser, 
			@Param("number") int number, @Param("pageSize") int pageSize);
	
	@Query(value="SELECT count(*) FROM files WHERE active=1 AND user_id=:idUser", nativeQuery=true)
	long countFileActiveByUserId(@Param("idUser") int idUser);
	
	
	@Transactional
	@Modifying
	@Query(value="UPDATE files SET active=0 WHERE id=:idFile", nativeQuery=true)
	void updateActive(@Param("idFile") int idFile);
	
}
/*
 * like %:fileInfo % OR"
			+ " size like %:fileInfo% OR "
			+ "user_id IN (SELECT id FROM user WHERE name=:fileInfo')"
 */
