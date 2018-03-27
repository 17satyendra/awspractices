package com.bridgelabz.register.service;

import java.io.IOException;

import com.bridgelabz.register.model.User;

public interface UserService 
{
	User save(User user) throws IOException;
}
