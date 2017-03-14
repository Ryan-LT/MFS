package com.csc.mfs.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.csc.mfs.model.Comment;

@Transactional
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	@Query(value="SELECT c.id, c.content, c.likeComment, c.dateComment, u.last_name FROM finalfresherfilesharing.comment c, finalfresherfilesharing.user u "
			+ " WHERE u.id=c.id_user AND c.id_file=:idFile", nativeQuery=true)
	List<Object> getCommentOfFle(@Param("idFile") int idFile);
	
	List<Comment> findByIdFile(Integer idFile);
	
	void removeByIdUser(int idUser);

	void removeByIdFile(int idFile);

}
