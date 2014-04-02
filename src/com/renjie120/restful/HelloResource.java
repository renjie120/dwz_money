package com.renjie120.restful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

@Path("/hello")
public class HelloResource {
	@Context
	Request request;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		System.out.println(request.getMethod());
		return "Hello Jer1111sey";
	}
	
	public String getContact() {
		System.out.println(request.getMethod());
		return "Hello Jer1111sey";
	}
}
