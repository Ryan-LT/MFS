package com.csc.mfs.controller;

import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csc.mfs.service.CommentService;
import com.csc.mfs.model.Comment;
import com.csc.mfs.model.Files;
import com.csc.mfs.model.User;
import com.csc.mfs.repository.FilesRepository;
import com.csc.mfs.repository.UserRepository;

@RestController
@RequestMapping("comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FilesRepository fileRepository;
	
	@RequestMapping("/getByFile/{idFile}")
	public Page<Comment> getByFile(@PathVariable int idFile, Pageable pageable){
		return commentService.getByFile(idFile, pageable);
	}
	
	@PostMapping("/saveComment")
	public void saveComment(@RequestBody String jsonComment){
		int idFile=0;
		String content="";
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(jsonComment);
			JSONObject jsonObject = (JSONObject) obj;
			System.out.println(jsonObject);
			idFile = ((Long) jsonObject.get("idFile")).intValue();
            content = (String) jsonObject.get("content");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByEmail(auth.getName());
		Files file = fileRepository.findOne(idFile);
//		System.out.println(comment.getContent());
//		System.out.println(comment.getIdFile().getId());
		Comment comment= new Comment();
		comment.setIdFile(file);
		comment.setIdUser(user);
		comment.setContent(content);
		comment.setLikeComment(0);
		comment.setDateComment(new Date());
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
