package com.sistemes.filtres;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.core.MultivaluedMap;
import java.sql.Timestamp;

public class UsersFiltre {

		private int id;
		private String name;
		private String username;
		private String password;

	public UsersFiltre(){
		clear();
	}

	public UsersFiltre(MultivaluedMap<String, String> param){
		if (param.getFirst("id") != null )  {this.id = Integer.parseInt(param.getFirst("id")); }
		if (param.getFirst("name") != null )  {this.name = param.getFirst("name"); }
		if (param.getFirst("username") != null )  {this.username = param.getFirst("username"); }
		if (param.getFirst("password") != null )  {this.password = param.getFirst("password"); }
	}

	public void clear(){
		this.id = 0;
		this.name = null;
		this.username = null;
		this.password = null;
	}

	public int getId(){ return  this.id; }
	public String getName(){ return  this.name; }
	public String getUsername(){ return  this.username; }
	public String getPassword(){ return  this.password; }

	public UsersFiltre setId(int value ){ this.id = value; return this; }
	public UsersFiltre setName(String value ){ this.name = value; return this; }
	public UsersFiltre setUsername(String value ){ this.username = value; return this; }
	public UsersFiltre setPassword(String value ){ this.password = value; return this; }
}
