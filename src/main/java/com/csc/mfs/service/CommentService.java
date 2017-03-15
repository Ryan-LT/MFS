package com.csc.mfs.service;

import com.csc.mfs.model.Comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.mfs.repository.CommentRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;
	
	public List<Comment> getByFile(int idFile){
		return commentRepository.findByIdFile(idFile);
	}
	
	public List<Object> getCommentOfFile(int idFile){
		return commentRepository.getCommentOfFle(idFile);
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
