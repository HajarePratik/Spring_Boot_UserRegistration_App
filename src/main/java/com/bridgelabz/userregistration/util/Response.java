package com.bridgelabz.userregistration.util;

import lombok.Data;

@Data
public class Response {

	private Integer StatusCode;
	private String Statusmessage;
	private Object token;
	private String statusMsg;
	public Response(Integer statusCode, String statusmessage, Object token,String statusMsg) {
		super();
		StatusCode = statusCode;
		Statusmessage = statusmessage;
		this.token = token;
		this.statusMsg = statusMsg;
	}
}

