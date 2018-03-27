package com.bridgelabz.register.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.register.dao.UserDAO;
import com.bridgelabz.register.model.User;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private S3Repository S3Repo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User save(User user) throws IOException 
	{
		logger.debug("Request inside service -"+user);
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userDAO.save(user);
		
		S3Repo.saveImageToS3(user.getId(), user.getImage());
		
		logger.debug("Saved sucessfully into database");
		
		return user;
	}
}
