package com.example.controller;

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
	@GET @Path("/names/{username}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUser(@PathParam("username") String username) {
		
		System.out.println(username);
		
		StringBuilder sb = new StringBuilder();
		
		final String sql = "select * from user where name like '%" + username + "%'";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = DBUtil.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				User user = new User();
				
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setProfession(rs.getString("profession"));
				
				sb.append(user.makeJSON());
			} else {
				sb.append("HEAD: { CODE : 404 }, BODY: { MESSAGE : NO DATA FOUND} ");
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (null != rs) {
					rs.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			} finally {
				rs = null;
			}
			
			try {
				if (null != ps) {
					ps.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			} finally {
				ps = null;
			}
		}
		
		return sb.toString();
	}

	/**
	 * Method handling HTTP GET requests. The returned object will be sent
	 * to the client as "text/plain" media type.
	 * 
	 * @return String that will be returned as a text/plain response.
	 */
	@GET @Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public String listUsers() {

		StringBuilder sb = new StringBuilder();
		
		final String querySql = "select * from user;";
		PreparedStatement psQuery = null;
		ResultSet rs = null;
		
		try {
			psQuery = DBUtil.getConnection().prepareStatement(querySql);
			rs = psQuery.executeQuery();
			
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
			if (null != rs) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				} finally {
					rs = null;
				}
			}
			if (null != psQuery) {
				try {
					psQuery.close();
				} catch (SQLException se) {
					se.printStackTrace();
				} finally {
					psQuery = null;
				}
			}
		}
		return sb.toString();
	}
	
	
	/**
	 * Method handling HTTP POST requests. The returned object will be sent
	 * to the client as "application/json" media type.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String ceateUser(@BeanParam User newUser) {
		
		final String sql = "insert into user(name, password, profession) values (?, ?, ?);";
		
		PreparedStatement ps = null;
		
		try {
			ps = DBUtil.getConnection().prepareStatement(sql);
			
			ps.setString(1, newUser.getName());
			ps.setString(2, newUser.getPassword());
			ps.setString(3, newUser.getProfession());
			
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != ps) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace();
				} finally {
					ps = null;
				}
			}
		}
		return listUsers();
	}
	
}
