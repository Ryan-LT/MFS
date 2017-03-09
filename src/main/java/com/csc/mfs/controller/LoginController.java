package com.csc.mfs.controller;


import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

import javax.validation.Valid;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.csc.mfs.model.User;
import com.csc.mfs.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/index") // , method = RequestMethod.GET
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/account") // , method = RequestMethod.GET
	public String admin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getAuthorities().toString());
		if(auth.getAuthorities().toString().equals("[ADMIN]")){
			return "admin";	
		}
		return "member";
	}
	
	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");	
		return modelAndView;
	}


	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
			.rejectValue("email", "error.user",
					"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");

		}
		return modelAndView;
	}

	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		//System.out.println(auth.getAuthorities());
		if(null!=user){
			modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
			modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
			modelAndView.setViewName("admin");
		} else {
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/member", method = RequestMethod.GET)
	public ModelAndView memberHome(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		//System.out.println(auth.getAuthorities());
		if(null!=user){
			modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
			modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
			modelAndView.setViewName("member");
		} else {
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/access-denied", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("access-denied");
		return modelAndView;
	}
	
//	@RequestMapping(value = "/error", method = RequestMethod.GET)
//	public ModelAndView error() {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println(auth.getAuthorities());
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("error");
//		return modelAndView;
//	}
}



