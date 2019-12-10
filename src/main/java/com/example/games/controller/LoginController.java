package com.example.games.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.games.entities.User;
import com.example.games.service.UserService;

@Controller
public class LoginController {
	
	   @Autowired
	   private UserService userService;
	   

	    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	    public ModelAndView login(){
	        ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("login");
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
	    
		@PostMapping("/registration")
		public ResponseEntity<Integer> insertUser(@RequestBody User user){
			System.out.println("posting");
			User option = userService.findUserByEmail(user.getEmail());
			if(option != null) {
				return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
			}
			else {
				userService.saveUser(user);
				return new ResponseEntity<Integer>(HttpStatus.OK);
			}
			
		}

//	    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
//	    public ModelAndView home(){
//	        ModelAndView modelAndView = new ModelAndView();
//	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//	        User user = userService.findUserByEmail(auth.getName());
//	        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
//	        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
//	        modelAndView.setViewName("admin/home");
//	        return modelAndView;
//	    }
	    
	    @PostMapping("/admin/promote/{id}")
	    public ResponseEntity<Integer> promoteUser(@PathVariable Integer id){
	    	Optional<User> option = userService.findUserById(id);
	    	if(!option.isPresent()) {
	    		return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
	    	}
	    	else {
	    		userService.promoteUser(id);
	    		return new ResponseEntity<Integer>(HttpStatus.OK);
	    	}
	    }
	    
	    @PostMapping("/admin/demote/{id}")
	    public ResponseEntity<Integer> demoteUser(@PathVariable Integer id){
	    	Optional<User> option = userService.findUserById(id);
	    	if(!option.isPresent()) {
	    		return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
	    	}
	    	else {
	    		userService.demoteUser(id);
	    		return new ResponseEntity<Integer>(HttpStatus.OK);
	    	}
	    }
	    
	    @GetMapping("/admin/users")
	    public ResponseEntity<List<User>> getUsers(){
	    	return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
	    }



}