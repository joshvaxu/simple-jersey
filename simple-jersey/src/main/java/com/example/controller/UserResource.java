package com.example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.model.User;
import com.example.util.DBUtil;

/**
 * Root resource (exposed at "users/{username:[a-zA-Z][a-zA-Z_0-9]*}
 * 
 * @author xuhz
 *
 */
@Path("users")
public class UserResource {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent
	 * to the client as "text/plain" media type.
	 * 
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getUser(@PathParam("username") String username) {
		return username;
	}
	
	/**
	 * Method handling HTTP POST requests. The returned object will be sent
	 * to the client as "application/json" media type.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String ceateUser(@BeanParam User newUser) {
		
		StringBuilder sb = new StringBuilder();
		
		final String sql = "insert into user(name, password, profession) values (?, ?, ?);";
		
		final String querySql = "select * from user;";
		
		PreparedStatement ps = null;
		PreparedStatement psQuery = null;
		
		try {
			ps = DBUtil.getConnection().prepareStatement(sql);
			
			ps.setString(1, newUser.getName());
			ps.setString(2, newUser.getPassword());
			ps.setString(3, newUser.getProfession());
			
			ps.execute();
			
			psQuery = DBUtil.getConnection().prepareStatement(querySql);
			ResultSet rs = psQuery.executeQuery();
			
			while (rs.next()) {
				User usr = new User();
				usr.setId(rs.getInt("id"));
				usr.setName(rs.getString("name"));
				usr.setPassword(rs.getString("password"));
				usr.setProfession(rs.getString("profession"));
				
				sb.append(usr.makeJSON() + ",");
			}
			
			if (sb.length() > 0) {
				sb.delete(sb.length() - 1, sb.length());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
				ps = null;
			}
		}
		return sb.toString();
	}
	
}
