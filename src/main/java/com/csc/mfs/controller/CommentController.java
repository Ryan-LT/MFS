package com.csc.mfs.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csc.mfs.service.CommentService;
import com.csc.mfs.model.Comment;
import com.csc.mfs.model.User;
import com.csc.mfs.repository.UserRepository;

@RestController
@RequestMapping("comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserRepository userRepository;
	@RequestMapping("/getByFile/{idFile}")
	public List<Comment> getByFile(@PathVariable int idFile){
		return commentService.getByFile(idFile);
	}
	
	@RequestMapping("/getCommnetOfFile/{idFile}")
	public List<Object> getCommentOfFile(@PathVariable int idFile){
		return commentService.getCommentOfFile(idFile);
	}
	
	@PostMapping("/saveComment")
	public void saveComment(@RequestBody Comment comment){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByEmail(auth.getName());
		System.out.println(comment.getContent());
		System.out.println(comment.getIdFile());
		comment.setIdUser(user.getId());
		comment.setLikeComment(0);
		comment.setDatecomment(new Date());
		commentService.saveComment(comment);
	}
	
	@RequestMapping("/like/{idComment}")
	public void increaceLike(@PathVariable int idComment){
		commentService.increaseLike(idComment);
	}
	
	@RequestMapping("/unLike/{idComment}")
	public void decreaceLike(@PathVariable int idComment){
		commentService.decreaseLike(idComment);
	}
	
	@RequestMapping("/deleteByUser/{idUser}")
	public void deleteByUser(@PathVariable int idUser){
		commentService.deleteByIdUser(idUser);
	}
	
	@RequestMapping("/deleteByFile/{idFile}")
	public void deleteByFile(@PathVariable int idFile){
		commentService.deleteByIdFile(idFile);
	}
	
	@RequestMapping("/delete/{idComment}")
	public void delete(@PathVariable int idComment){
		commentService.deleteByIdComment(idComment);
	}
	
	
}
