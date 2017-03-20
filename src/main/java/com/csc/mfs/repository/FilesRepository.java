package com.csc.mfs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.csc.mfs.model.Files;
import com.csc.mfs.model.User;
import java.lang.Integer;

/**
 * This interface provide method interactive db(files table)
 * @author VuMin
 *
 */
@Transactional
public interface FilesRepository extends JpaRepository<Files, Integer> {
	Page<Files> findBySharingAndActive(Integer sharing, Integer active, Pageable pageable);
	
	Page<Files> findByUserIdAndActive(User userid, Integer active, Pageable pageable);
	
	Page<Files> findBySizeIsLessThanEqual(double size, Pageable pageable);
	
	Page<Files> findByNameContainingAndActive(String name, Integer active, Pageable pageable);
	
	Page<Files> findByUserIdLastNameContainingAndActive(String lastName, Integer active, Pageable pageable);
	
	Page<Files> findByIdTypeFileTypeContainingAndActive(String typeFile, Integer active, Pageable pageable);
	
	Page<Files> findByIdTypeCategoryIdNameAndActive(String category, Integer active, Pageable pageable);
	
	Page<Files> findBySharingAndActiveOrNameContainingOrUserIdLastNameContainingOrSizeLessThanEqualOrIdTypeFileTypeContaining(Integer sharing, Integer active, String name, String uploader, double size, String type, Pageable pageable);
	
	Page<Files> findBySharingAndActiveAndNameContainingOrSharingAndActiveAndUserIdLastNameContainingOrSharingAndActiveAndSizeLessThanEqualOrSharingAndActiveAndIdTypeFileTypeContainingOrSharingAndActiveAndIdTypeCategoryIdName(Integer sharing, Integer active, String name, Integer sharing1, Integer active1, String uploader, Integer sharing2, Integer active2, double size, Integer sharing3, Integer active3, String type, Integer sharing4, Integer active4, String category, Pageable pageable);
	
	@Query(value="SELECT SUM(size) FROM files WHERE active=1 AND user_id=:idUser", nativeQuery=true)
	Object sumSizeUpload(@Param("idUser") int idUser);
	
	/**
	 * This method is used to search file by info file(name, type, categories, size) 
	 * @param fileInfo
	 * @param number
	 * @param pageSize
	 * @return List<Files>
	 *//*
	@Query(value="SELECT f.*, u.last_name FROM files f INNER JOIN user u ON f.user_id=u.id WHERE f.active=1 "
			+ " AND (f.name like %:fileInfo%"
			+ " OR f.size like %:fileInfo% OR"
			+ " f.user_id IN (SELECT id FROM user WHERE user.last_name=:fileInfo)"
			+ " OR id_type IN (SELECT id FROM categories_type WHERE file_type LIKE %:fileInfo%)"
            + " OR id_type IN (SELECT id FROM categories_type t WHERE t.category_id "
            + "	IN ( SELECT id FROM categories WHERE name LIKE %:fileInfo%)"
            + ")) ORDER BY ?#{#pageable}"
            , countQuery="SELECT count(*) FROM files f WHERE f.active=1 AND (f.name like %:fileInfo%"
			+ " OR f.size like %:fileInfo% OR"
			+ " f.user_id IN (SELECT id FROM user u WHERE u.last_name=:fileInfo)"
			+ " OR id_type IN (SELECT id FROM categories_type WHERE file_type LIKE %:fileInfo%)"
            + " OR id_type IN (SELECT id FROM categories_type t WHERE t.category_id "
            + "	IN ( SELECT id FROM categories WHERE name LIKE %:fileInfo%)"
            + "))"
            ,nativeQuery=true)
	Page<Object> findByInfo(@Param("fileInfo") String fileInfo, Pageable pageable);*/
	
}