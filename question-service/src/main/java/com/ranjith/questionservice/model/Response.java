package com.ranjith.questionservice.model;

import lombok.Data;

@Data
public class Response {

	private Integer id;
	private String response;
	
	
	public Response(Integer id, String response) {
		super();
		this.id = id;
		this.response = response;
	}
	
	
}
