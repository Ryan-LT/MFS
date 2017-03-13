package com.csc.mfs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.csc.mfs.model.Download;
import com.csc.mfs.model.User;

/**
 * This interface extend JpaRepository interface, support method interactive db(direct)
 * @author VuMin
 *
 */
public interface DownloadRepository extends JpaRepository<Download, Integer>{
	/**
	 * Get total size download of user
	 * @param idOfUser
	 * @return Object 
	 */
	@Query(value="SELECT SUM(size) FROM files f, download d WHERE f.id=d.id_file"
			+ " AND d.id_user=:idOfUser", nativeQuery=true)
	Object sumSizeDownload(@Param("idOfUser") int idOfUser);
	
	/**
	 * Get total size download of user in day
	 * @param idOfUser
	 * @param dateDownload
	 * @return Object(it could be NULL)
	 */
	@Query(value="SELECT SUM(size) FROM files f, download d WHERE f.id=d.id_file"
			+ " AND d.id_user=:idOfUser AND (UNIX_TIMESTAMP(datedownload)*1000)=:dateDownload", nativeQuery=true)
	Object sumSizeDownloadInDay(@Param("idOfUser") int idOfUser, @Param("dateDownload") long dateDownload);
	
	/**
	 * Get total download of user
	 * @param idOfUser
	 * @return long
	 */
	@Query(value="SELECT count(*) FROM download d WHERE d.id_user=:idOfUser", nativeQuery=true)
	long countDownloadByUser(@Param("idOfUser") int idOfUser);
	
	/**
	 * get total downloads of file
	 * @param idFile
	 * @return long
	 */
	@Query(value="SELECT count(*) FROM download WHERE id_file=:idFile", nativeQuery=true)
	long countDownloadFiles(@Param("idFile") int idFile);
	
	/**
	 * Remove record by Id user
	 * @param user
	 */
	@Transactional
	void removeByIdUser(User user);
	
	/**
	 * Get record download of user
	 * @param findOne
	 * @return List<Download>
	 */
	List<Download> findByIdUser(User findOne);
	
	@Query(value="SELECT f.*, d.datedownload FROM files f, download d "
			+ " WHERE d.id_file=f.id AND id_user=:idUser"
			+ " LIMIT :number, :pageSize", nativeQuery=true) 
	List<Object> getHistoryDownload(@Param("idUser") int idUser, @Param("number") int number, @Param("pageSize") int pageSize);
	
}
