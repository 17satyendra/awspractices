package com.bridgelabz.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan("com.bridgelabz.register")
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@EnableTransactionManagement(proxyTargetClass=true)
public class RegisterTestApplication
{

	public static void main(String[] args) 
	{
		SpringApplication.run(RegisterTestApplication.class, args);
	}
}
