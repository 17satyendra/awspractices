package com.bridgelabz.register.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.register.json.RegErrorsResponse;
import com.bridgelabz.register.json.Response;
import com.bridgelabz.register.model.User;
import com.bridgelabz.register.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController 
{
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	Response response = null;
	
	@RequestMapping(value= "/register", method=RequestMethod.POST )
	public ResponseEntity<?> register(@Valid User user, BindingResult result) throws IOException
	{
		logger.debug("Request - "+user);
		if(result.hasErrors())
		{
			logger.debug("Validation Failed Error", result.getFieldError());
			
			RegErrorsResponse errorsResponse = new RegErrorsResponse();
			errorsResponse.setMsg("Validation Error");
			errorsResponse.setStatus(false);
			errorsResponse.setErrors(result.getFieldErrors());
			
			return new ResponseEntity<RegErrorsResponse>(errorsResponse, HttpStatus.BAD_REQUEST);
		}
		userService.save(user);
			
		response = new Response();
		response.setStatus(true);
		response.setMsg("Sucess");
		
		logger.debug("Sucessfull", response);
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	@RequestMapping(value= "/any", method=RequestMethod.POST )
	public ResponseEntity<?> any(@RequestBody Response response)
	{
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
}
