package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.User;
import com.example.service.UserService;

@Controller
public class LoginController {

	public static String url="http://localhost:8090";
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value="status", required=false) String status, Model model) {
		
		if(status != null) {
			System.out.println(status);
			if(status.equals("okey")) {
				model.addAttribute("status", "Kaydiniz basariyla tamamlandı.");
			}else 
				model.addAttribute("status","Hata tekrar deneyiniz!");
			
		}
		return "login";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		
		return "register";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model,HttpServletRequest request ) {
		
		request.getSession().setAttribute("user", null);
		return "redirect:/login";
	}
	
	@RequestMapping(value ="/controlUser", method=RequestMethod.POST)
	public ResponseEntity<String> controlUser(@RequestBody User user, HttpServletRequest request ){
		
		User userm  = userService.getNoteFindByUsernameAndPass(user);
		
		if(userm != null) {
			
			request.getSession().setAttribute("user", userm);
			return new ResponseEntity<String>("OK",HttpStatus.OK);	
		}
		
		return new ResponseEntity<String>("ERROR",HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reg/{key}", method = RequestMethod.GET)
	public String regOk(@PathVariable("key") String key ,Model model) {
		
		if(userService.getNoteFindByKey(key)) {
			return "redirect:/login?status=okey";	
		}
		return "redirect:/login?status=error";
	}
	
	@RequestMapping(value ="/addUser", method=RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestBody User user, HttpServletRequest request ){
		
		int status = controlUser(user);
		
		if(status  != 0) {
			
			return new ResponseEntity<String>(status+"",HttpStatus.OK);
		}
		
		if(userService.insert(user).equals(1L)) {
			
			return new ResponseEntity<String>("OK",HttpStatus.OK);	
		}
		
		return new ResponseEntity<String>("ERROR",HttpStatus.OK);
	}
	
	private int controlUser(User user) {
		
		if (!user.getPassword2().equals(user.getPassword())) {
			
			return 1;
		}
		return 0;
	}
	
}
