package com.bridgelabz.test;

import static org.junit.Assert.assertNotNull;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bridgelabz.register.RegisterTestApplication;
import com.bridgelabz.register.json.Response;
import com.bridgelabz.register.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@WebAppConfiguration
@SpringBootTest(classes={RegisterTestApplication.class})
@RunWith(SpringRunner.class)
public class RegisterTestApplicationTests 
{
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	static ObjectMapper mapper = new ObjectMapper();
	
	private final Logger logger = LoggerFactory.getLogger(RegisterTestApplicationTests.class);
	static Gson gson = new Gson();
	@Before
	public void setUp() 
	{
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	@Ignore
	public void register_shouldReturn_Status200() throws Exception 
	{
		logger.debug("Test forming...");
		MockHttpServletResponse result = mockMvc.perform(MockMvcRequestBuilders
				  								.post("/user/register")
				  								.contentType(MediaType.APPLICATION_JSON_VALUE)
				  								.content(getUSERJSON()))
				  								.andExpect(MockMvcResultMatchers.status().is(200))
				  								.andReturn()
				  								.getResponse();
		//Response response = mapper.convertValue(result.getContentAsString(), Response.class);
		
		Response response = gson.fromJson(result.getContentAsString(), Response.class);
		System.out.println(response);
		assertNotNull("Sucess", response.getMsg());
	}
	
	private static String getUSERJSON() throws IOException
	{
		File file = new File("/home/bridgeit/Downloads/abc.jpeg");
		
		BufferedImage bufferedImage = ImageIO.read(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		String fileName = file.getName();
		ImageIO.write(bufferedImage, fileName.substring(fileName.lastIndexOf(".")+1), baos);
		baos.flush();
		MockMultipartFile multipartFile = new MockMultipartFile(fileName, baos.toByteArray());
		User user = new User();
		user.setFullName("Satyendra");
		user.setEmail("satyendra@gmail.com");
		user.setPassword("123456");
		user.setDOB(getDOB());
		user.setImage(multipartFile);
		
		
		return gson.toJson(user);
		//return mapper.writeValueAsString(user);
	} 
	
	private static Date getDOB()
	{
		/*Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 1991);
		calendar.set(Calendar.MONTH, 12);
		calendar.set(Calendar.DATE, 17);*/
		 LocalDate date = LocalDate.of(1991, 12, 17);
		 Date date2 = java.sql.Date.valueOf(date);
		return date2;
	}
	
	//random test
	@Test
	@Ignore
	public void any_shouldReturn_Status200() throws Exception 
	{
		MockHttpServletResponse result = mockMvc.perform(MockMvcRequestBuilders
					.post("/user/any")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(getANYJSON()))
					.andExpect(MockMvcResultMatchers.status().is(200))
					.andReturn()
					.getResponse();
		Response response = gson.fromJson(result.getContentAsString(), Response.class);
		assertNotNull(response);
	}

	private String getANYJSON() 
	{
		Response resp = new Response();
		resp.setMsg("any");
		resp.setStatus(true);
		
		return gson.toJson(resp);
	}

}
