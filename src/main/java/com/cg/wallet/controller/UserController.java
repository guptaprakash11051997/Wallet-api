package com.cg.wallet.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.wallet.exception.UserAlreadyExistsException;
import com.cg.wallet.exception.UserNotFoundException;
import com.cg.wallet.model.User;
import com.cg.wallet.service.IUserService;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

	@Autowired
	IUserService serviceRef;

	private Logger logger = Logger.getLogger(getClass());
	
	@RequestMapping("/users")
	public List<User> getAllUser() {
		return serviceRef.getAllUser();
	}

	@RequestMapping("/users/{userId}")
	public User getUserById(@PathVariable long userId) {
		return serviceRef.getUserById(userId);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/user/add")
	public void addUser(@RequestBody User user) throws UserAlreadyExistsException {		
		serviceRef.addUser(user);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/user/update")
	public void updateUser(@RequestBody User user) {
		serviceRef.updateUser(user);
		logger.info("User updated Successfully");
	}

	@RequestMapping("/user/email/{emailId}")
	public List<User> getUserByEmailId(@PathVariable String emailId) throws UserNotFoundException  {
		return serviceRef.getUserByEmailId(emailId);
	}
	
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<Object> exceptionForUserNotFound(Exception ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = UserAlreadyExistsException.class)
	public ResponseEntity<Object> exceptionForUserAlreadyExists(Exception ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.ALREADY_REPORTED);
	}
}
