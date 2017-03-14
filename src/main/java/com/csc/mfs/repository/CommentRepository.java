package com.csc.mfs.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csc.mfs.model.Comment;

@Transactional
public interface CommentRepository extends JpaRepository<Comment, Integer>{

	List<Comment> findByIdFile(Integer idFile);
	
	void removeByIdUser(int idUser);

	void removeByIdFile(int idFile);

}
