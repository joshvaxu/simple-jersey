package com.example;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "smooth")
 * 
 * @author xuhz
 *
 */
@Path("smooth")
@Produces(MediaType.TEXT_PLAIN)
public class SmoothResource {

	/**
	 * Method handling HTTP GET requests.
	 * 
	 *  @return String 
	 */
	@GET
	public String smooth(@DefaultValue("xuhz") @QueryParam("id") String id) {
		return id;
	}
}
