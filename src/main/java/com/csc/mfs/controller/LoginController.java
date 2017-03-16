package com.csc.mfs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

	@RequestMapping(value = "/") // , method = RequestMethod.GET public
	public ModelAndView landing() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("landing");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getAuthorities().toString().equals("[MEMBER]")) {
			User user = userService.findUserByEmail(auth.getName());
			modelAndView.addObject("userName", user.getName());
			System.out.println(user.getName());
		}
		return modelAndView;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getAuthorities().toString().equals("[MEMBER]") || auth.getAuthorities().toString().equals("[ADMIN]")) {
			modelAndView.setViewName("landing");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		ModelAndView modelAndViewAdmin = new ModelAndView();
		ModelAndView modelAndViewMember = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getAuthorities().toString());
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		if (auth.getAuthorities().toString().equals("[ADMIN]")) {
			modelAndViewAdmin.setViewName("admin");
			return modelAndViewAdmin;
		} else if (auth.getAuthorities().toString().equals("[MEMBER]")) {
			modelAndViewMember.setViewName("member");
			return modelAndViewMember;
		}
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user",
					"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
			modelAndView.addObject("message", "User has been registered fail.");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("message", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");

		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		// System.out.println(auth.getAuthorities());
		if (null != user) {
			modelAndView.setViewName("admin");
		} else {
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/member", method = RequestMethod.GET)
	public ModelAndView memberHome() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		// System.out.println(auth.getAuthorities());
		if (null != user) {
			modelAndView.addObject("userName",
					"Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
			modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
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
}
