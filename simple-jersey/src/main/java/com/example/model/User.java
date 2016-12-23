package com.example.model;

import javax.ws.rs.FormParam;

public class User {

	private int id;
	
	@FormParam("name")
	private String name;
	
	@FormParam("password")
	private String password;
	
	@FormParam("profession")
	private String profession;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the profession
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * @param profession the profession to set
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	public String makeJSON() {
		StringBuilder json = new StringBuilder();
		
		json.append("\"User" + this.id + "\": {");
		json.append("\"name\":" + this.getName() + ", ");
		json.append("\"password\":" + this.getPassword() + ",");
		json.append("\"profession\":" + this.getProfession() + ",");
		json.append("\"id\":" + this.getId() + "}");
		
		return json.toString();
	}
	
}
