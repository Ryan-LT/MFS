package com.csc.mfs.controller;

//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.csc.mfs.mail.ComponentResetPassword;
import com.csc.mfs.mail.SendMail;
import com.csc.mfs.messages.Message;
import com.csc.mfs.model.User;
import com.csc.mfs.service.UserService;

import net.sf.ehcache.CacheManager;

@Controller
@RequestMapping("/resetpassword")
public class ResetPassword {
	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(ResetPassword.class);
	
	ApplicationContext context;
	
	private ComponentResetPassword componentResetPassword;
	
	public ResetPassword(ComponentResetPassword crp) {
		this.componentResetPassword = crp;
	}
	
	@PostMapping("/checkEmail")
	public ResponseEntity<?> checkEmailExist(@RequestBody String email){
		User user = userService.findUserByEmail(email);
		new ResponseEntity<Boolean>(HttpStatus.OK);
		if(null!=user){
			componentResetPassword.getConfirm(user.getEmail());
			context = new ClassPathXmlApplicationContext("Mail-Bean.xml");
			SendMail sendMail = (SendMail) context.getBean("mail__");
			String content="<h2 text-align='center'>You required reset your password at mfs.com</h2>";
			content+="<b>Please click link below to reset your password</b><br>";
			content+="<a href='http://localhost:8080/resetpassword/user/"+user.getEmail()+"/"+componentResetPassword.getConfirm(user.getEmail())+"/'>";
			content+="http://localhost:8080/resetpassword/user/"+user.getEmail()+"/"+componentResetPassword.getConfirm(user.getEmail())+"</a>";
			sendMail.sendmailToClient("MFS", user.getEmail(), "RESET PASSWORD", content);
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
	}
	
	@GetMapping("/user/{mail}/{code}/")
	public ModelAndView resetPassword(@PathVariable("mail") String mail,@PathVariable("code") String code) {
		ModelAndView model = new ModelAndView();
		User user = userService.findUserByEmail(mail);
		if(null!=user){
			//CacheManager cache = new CacheManager();
			//logger.info(cache.getCache("passwordConfirm")+"");
			if(componentResetPassword.getConfirm(user.getEmail()).equals(code)){
//				logger.info(mail);
//				logger.info(componentResetPassword.getConfirm(user.getEmail()));
//				logger.info(code);
//				
				model.addObject("email", user.getEmail());
				model.addObject("token", componentResetPassword.getConfirm(user.getEmail()));
				model.setViewName("formResetPassword");
				return model;	
			}
		}
		model.setViewName("access-denied");
		return model;
	}
	
	@PostMapping("/doReset")
	public ModelAndView doReset(@RequestParam String email, @RequestParam String token, @RequestParam String newPassword){
		ModelAndView model = new ModelAndView();
		if(!token.equals(componentResetPassword.getConfirm(email))){
			model.setViewName("access-denied");
		} else {
			model.setViewName("formResetPassword");
			Message msg = userService.resetPassword(email, newPassword);
			if(msg.isState()){
				model.addObject("msg", "Reset password successful");
				componentResetPassword.refreshConfirm();
			} else {
				model.addObject("msg", "Sonething was wrong, please check again!");
			}
		}
		return model;
	}
	
}




