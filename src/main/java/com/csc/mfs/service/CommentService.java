package com.csc.mfs.service;

import com.csc.mfs.model.Comment;
import com.csc.mfs.model.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.csc.mfs.repository.CommentRepository;
import com.csc.mfs.repository.FilesRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private FilesRepository fileRepository;
	
	public Page<Comment> getByFile(int idFile, Pageable pageable){
		Files file = fileRepository.findOne(idFile);
		PageRequest page = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Direction.DESC, "dateComment");
		if(null!=file){
			return commentRepository.findByIdFile(file, page);	
		} else {
			return null;
		}
	}

	public void saveComment(Comment comment){
		commentRepository.saveAndFlush(comment);
	}
	
	public void increaseLike(int idComment){
		Comment comment = commentRepository.findOne(idComment);
		if(null!=comment){
			comment.setLikeComment(comment.getLikeComment()+1);
			commentRepository.flush();
		}
	}
	
	public void decreaseLike(int idComment){
		Comment comment = commentRepository.findOne(idComment);
		if(null!=comment){
			comment.setLikeComment(comment.getLikeComment()-1);
			commentRepository.flush();
		}
	}
	
	public void deleteByIdComment(int idComment){
		if(null!=commentRepository.findOne(idComment)){
			commentRepository.delete(idComment);
		}
	}
	
	public void deleteByIdUser(int idUser){
		commentRepository.removeByIdUser(idUser);
	}
	
	public void deleteByIdFile(int idFile){
		commentRepository.removeByIdFile(idFile);
	}
	
}
