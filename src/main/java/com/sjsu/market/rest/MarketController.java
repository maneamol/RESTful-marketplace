package com.sjsu.market.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sjsu.market.beans.User;
import com.sjsu.marketplace.process.UserProcess;


@Path("/users")
public class MarketController {
	@POST
	@Path("/signup")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void signUp(User user) {
		try {
			if (user.getType() == null) {
				user.setType("cust");
			}
			System.out.println(user.getUserName() + " " + user.getFirstName() + " " + user.getLastName() + " " + user.getPassword() + " " + user.getEmail() + " " + user.getType());
			UserProcess userProcess = new UserProcess();
			userProcess.signUp(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@FormParam("email") String email, @FormParam("password") String password) {
		UserProcess userProcess = new UserProcess();
		User user = userProcess.login(email, password);
		if (null != user) {
			return Response.status(200).entity(user).build();
		}
		return Response.status(400).entity("Error handling request!! Try again!!").build();
	}
	
	@POST
	@Path("/adminlogin")
	@Produces(MediaType.APPLICATION_JSON)
	public Response adminLogin(@FormParam("email") String email, @FormParam("password") String password, @FormParam("type") String type ) {
		UserProcess userProcess = new UserProcess();
		User user = userProcess.adminLogin(email, password, type);
		if (null != user) {
			return Response.status(200).entity(user).build();
		}
		return Response.status(400).entity("Error handling request!! Try again!!").build();
	}
}
