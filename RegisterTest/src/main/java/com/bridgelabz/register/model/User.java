package com.bridgelabz.register.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//@JsonAutoDetect(isGetterVisibility = Visibility.ANY, setterVisibility = Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable 
{
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name = "userId", strategy = "increment")
	@GeneratedValue(generator = "userId")
	private long id;
	
	@NotBlank(message = "*Please provide a fullname")
	@Size(min=3, message = "*Your fullName must have at least 3 characters")
	private String fullName;
	
	@Email(message = "*Please provide a valid Email")
	@NotBlank(message = "*Please provide an email")
	private String email;
	
	@Size(min = 5, message = "*Your password must have at least 5 characters")
	@NotBlank(message = "*Please provide your password")
	private String password;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@NotNull @Past
	private Date DOB;
	
	@Transient
	
	private MultipartFile image;
	
	private String imageUrl;
	
	public User() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fullName=" + fullName + ", email=" + email + ", password=" + password + ", DOB="
				+ DOB + ", image=" + image + ", imageUrl=" + imageUrl + "]";
	}

}