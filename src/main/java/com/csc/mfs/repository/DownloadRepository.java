package com.csc.mfs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.csc.mfs.model.Download;
import com.csc.mfs.model.User;


public interface DownloadRepository extends JpaRepository<Download, Integer>{
	@Query(value="SELECT SUM(size) FROM files f, download d WHERE f.id=d.id_file"
			+ " AND d.id_user=:idOfUser", nativeQuery=true)
	double sumSizeDownload(@Param("idOfUser") int idOfUser);
	
	@Query(value="SELECT SUM(size) FROM files f, download d WHERE f.id=d.id_file"
			+ " AND d.id_user=:idOfUser AND (UNIX_TIMESTAMP(datedownload)*1000)=:dateDownload", nativeQuery=true)
	Object sumSizeDownloadInDay(@Param("idOfUser") int idOfUser, @Param("dateDownload") long dateDownload);
	
	@Query(value="SELECT count(*) FROM files f WHERE f.user_id=:idOfUser", nativeQuery=true)
	long countDownloadByUser(@Param("idOfUser") int idOfUser);
	
	@Query(value="SELECT count(*) FROM download WHERE id_file=:idFile", nativeQuery=true)
	long countDownloadFiles(@Param("idFile") int idFile);
	
	@Transactional
	void removeByIdUser(User user);

	List<Download> findByIdUser(User findOne);
}
