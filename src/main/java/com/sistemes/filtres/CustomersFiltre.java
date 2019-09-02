package com.sistemes.filtres;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.core.MultivaluedMap;
import java.sql.Timestamp;

public class CustomersFiltre {

	private int id;
	private String name;
	private int vatnumber;
	private String location;
	private String city;

	public CustomersFiltre(){
		clear();
	}

	public CustomersFiltre(MultivaluedMap<String, String> param){
		if (param.getFirst("id") != null )  {this.id = Integer.parseInt(param.getFirst("id")); }
		if (param.getFirst("name") != null )  {this.name = param.getFirst("name"); }
		if (param.getFirst("vatnumber") != null )  {this.vatnumber = Integer.parseInt(param.getFirst("vatnumber")); }
		if (param.getFirst("location") != null )  {this.location = param.getFirst("location"); }
		if (param.getFirst("city") != null )  {this.city = param.getFirst("city"); }
	}

	public void clear(){
		this.id = 0;
		this.name = null;
		this.vatnumber = 0;
		this.location = null;
		this.city = null;
	}

	public int getId(){ return  this.id; }
	public String getName(){ return  this.name; }
	public int getVatnumber(){ return  this.vatnumber; }
	public String getLocation(){ return  this.location; }
	public String getCity(){ return  this.city; }

	public CustomersFiltre setId(int value ){ this.id = value; return this; }
	public CustomersFiltre setName(String value ){ this.name = value; return this; }
	public CustomersFiltre setVatnumber(int value ){ this.vatnumber = value; return this; }
	public CustomersFiltre setLocation(String value ){ this.location = value; return this; }
	public CustomersFiltre setCity(String value ){ this.city = value; return this; }
}
