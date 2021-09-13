package com.cg.wallet.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.wallet.dao.IUserDAO;
import com.cg.wallet.exception.UserAlreadyExistsException;
import com.cg.wallet.exception.UserNotFoundException;
import com.cg.wallet.model.User;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDAO daoRef;


	private Logger logger = Logger.getLogger(getClass());
	
	@Override
	public List<User> getAllUser() {
		return daoRef.findAll();
	}

	@Override
	public User getUserById(long userId) {
		return daoRef.getOne(userId);
	}

	@Override
	public User addUser(User user) throws UserAlreadyExistsException {
		if (daoRef.findByUserEmailId(user.getUserEmailId()).isEmpty()) {
			logger.info("User added Successfully");
			return daoRef.save(user);
		} else {
			logger.error("Adding user failed..");
			throw new UserAlreadyExistsException("This email Id is already being used");
		}
	}

	@Override
	public User updateUser(User user) {
		logger.info("User updated Successfully");
		return daoRef.save(user);
	}

	@Override
	public List<User> getUserByEmailId(String emailId) throws UserNotFoundException {
		if (daoRef.findByUserEmailId(emailId).isEmpty()) {
			logger.error("Email Id not found");
			throw new UserNotFoundException("This Email Id is not used by any user");
		} else {
			logger.info("user retrieved successfully");
			return daoRef.findByUserEmailId(emailId);
		}
	}

}
