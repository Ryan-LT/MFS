package com.csc.mfs.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import javax.management.loading.PrivateClassLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csc.mfs.mail.ComponentResetPassword;
import com.csc.mfs.mail.SendMail;
import com.csc.mfs.model.User;
import com.csc.mfs.service.UserService;

@Controller
@RequestMapping("/resetpassword")
public class ResetPassword {
	@Autowired
	private UserService userService;
	private static final Logger logger = LoggerFactory.getLogger(ResetPassword.class);
	
	private ComponentResetPassword componentResetPassword;
	
	private String codeConfrim;
	
	public ResetPassword(ComponentResetPassword crp) {
		this.componentResetPassword = crp;
		codeConfrim = "";
	}
	
	@PostMapping("/checkEmail")
	public ResponseEntity<?> checkEmailExist(@RequestBody String email){
		User user = userService.findUserByEmail(email);
		new ResponseEntity<Boolean>(HttpStatus.OK);
		if(null!=user){
			codeConfrim = componentResetPassword.getConfirm(user.getEmail());
			logger.info(codeConfrim);
			/*ApplicationContext context = new ClassPathXmlApplicationContext("Mail-Bean.xml");
			SendMail sendMail = (SendMail) context.getBean("mail__");
			String content="<h2>You required reset your password at mfs.com</h2>";
			content+="<b>Please click link below to reset your password</b><br>";
			content+="<a href='http://localhost:8080/resetpassword/user/"+user.getPassword()+"'>http://localhost:8080/resetpassword/user/"+user.getPassword()+"</a>";
			sendMail.sendmailToClient("MFS", user.getEmail(), "RESET PASSWORD", content);*/
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
	}
	
	@GetMapping("/user/{mail}/{code}/")
	public String resetPassword(@PathVariable("mail") String mail,@PathVariable("code") String code) {
		User user = userService.findUserByEmail(mail);
		if(null!=user){
			logger.info(mail);
			logger.info(componentResetPassword.getConfirm(user.getEmail()));
			logger.info(code);
			if(componentResetPassword.getConfirm(user.getEmail()).equals(code)){
				logger.info(mail);
				logger.info(componentResetPassword.getConfirm(user.getEmail()));
				logger.info(code);
				return "formResetPassword";	
			}
		}
		return "access-denied";
		
	}
	
}
