package com.csc.mfs.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.csc.mfs.model.Comment;
import com.csc.mfs.model.Files;

@Transactional
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	Page<Comment> findByIdFile(Files file, Pageable pageable);
	void removeByIdUser(int idUser);
	void removeByIdFile(int idFile);

}
