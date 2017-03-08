package com.csc.mfs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.csc.mfs.model.Download;


public interface DownloadRepository extends JpaRepository<Download, Integer>{
	@Query(value="SELECT SUM(size) FROM files f, download d WHERE f.id=d.id_file"
			+ " AND d.id_user=:idOfUser", nativeQuery=true)
	double sumSizeDownload(@Param("idOfUser") int idOfUser);
}
