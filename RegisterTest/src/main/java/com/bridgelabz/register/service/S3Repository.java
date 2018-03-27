package com.bridgelabz.register.service;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.Optional;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.bridgelabz.register.dao.UserDAO;
import com.bridgelabz.register.model.User;

/**
 * This is Image/file uploading service/class S3Repository
 * there are two methods for upload image and get the signed url of 
 * Particular image.
 * We have used aws s3 credential for aws Http api call.
 * 
 * @author Satyendra Singh
 */
@Component
public class S3Repository 
{
	@Autowired
	private AmazonS3 s3Client;
	
	@Autowired
	private UserDAO userDAO;
	
	@Value("${aws_S3_bucket_name}")
	private String S3_BUCKET_NAME;
	
	private final Logger logger = LoggerFactory.getLogger(S3Repository.class);
	
	/**
	 * This method is for Upload image file.
	 * @param Id User primary id 
	 * @param file to be upload
	 * @throws IOException file parse Exception.
	 */
	@Async
	public void saveImageToS3(long Id, MultipartFile file) throws IOException
	{
		logger.debug("saving to s3 bucket...");
		//File fileToUpload = convertFromMultiPart(file);//store at disk
		String key = "USER-"+Id+generateKey(file);

		ObjectMetadata ob = new ObjectMetadata();
		// file.
		ob.setContentDisposition(file.getName());
		ob.setContentLength(file.getSize());
		ob.setContentType(file.getContentType());
		ob.setContentDisposition("inline");

		s3Client.putObject(S3_BUCKET_NAME, key, file.getInputStream(), ob);
		
		Optional<User> optional = userDAO.findById(Id);
		if(optional.isPresent())
		{
			User user = optional.get();
			user.setImageUrl(key);
			userDAO.save(user);
		}
		logger.debug("saved file into S3 bucket -", key);
	}
	/**
	 * This method takes image key and validate to exist image or not. useful in
	 * obtaining only the object metadata, and avoids wasting bandwidth on fetching
	 * the object data.
	 * 
	 * @param key Image key
	 * @return signed Image Url
	 * @throws Exception 
	 * @throws UploadImageException
	 */
	public String getSignedImgUrl(String key) throws Exception  
	{
		logger.debug("fetching url for image");
		
		URL signedUrl = null;
		try 
		{
			GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(S3_BUCKET_NAME,key);
			generatePresignedUrlRequest.setMethod(HttpMethod.GET);
			generatePresignedUrlRequest.setExpiration(DateTime.now().plusMinutes(2).toDate());

			signedUrl = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
			
		} catch (Exception e) 
		{
			logger.error("Error occured while fetched signed url", e.getMessage());
			throw new Exception(e);
		}
		logger.debug("fetched the url");
		return signedUrl.toString();
	}
	private String generateKey(MultipartFile file)
	{
		return "_"+Instant.now().getEpochSecond()+"_"+file.getOriginalFilename();
	}
}
