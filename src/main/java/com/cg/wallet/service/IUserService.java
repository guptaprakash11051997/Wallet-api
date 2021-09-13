package com.cg.wallet.service;

import java.util.List;

import com.cg.wallet.exception.UserAlreadyExistsException;
import com.cg.wallet.exception.UserNotFoundException;
import com.cg.wallet.model.User;

public interface IUserService {
	
	List<User> getAllUser();
	User getUserById(long userId);
	User addUser(User user) throws UserAlreadyExistsException;
	User updateUser(User user);
	List<User> getUserByEmailId(String emailId) throws UserNotFoundException;
}
