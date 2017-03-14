package com.csc.mfs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csc.mfs.service.CommentService;
import com.csc.mfs.model.Comment;

@RestController
@RequestMapping("comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("/getByFile/{idFile}")
	public List<Comment> getByFile(@PathVariable int idFile){
		return commentService.getByFile(idFile);
	}
	
	@PostMapping("/saveComment")
	public void saveComment(@RequestBody Comment comment){
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
