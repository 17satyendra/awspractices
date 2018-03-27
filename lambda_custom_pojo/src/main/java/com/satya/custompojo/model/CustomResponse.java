package com.satya.custompojo.model;

public class CustomResponse 
{
	String msg;

	public CustomResponse(String msg) {
		super();
		this.msg = msg;
	}
	
	public CustomResponse() {}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
