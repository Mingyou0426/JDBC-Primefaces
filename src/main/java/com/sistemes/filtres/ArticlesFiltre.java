package com.sistemes.filtres;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.core.MultivaluedMap;
import java.sql.Timestamp;

public class ArticlesFiltre {

	private int id;
	private String name;
	private int price;

	public ArticlesFiltre(){
		clear();
	}

	public ArticlesFiltre(MultivaluedMap<String, String> param){
		if (param.getFirst("id") != null )  {this.id = Integer.parseInt(param.getFirst("id")); }
		if (param.getFirst("name") != null )  {this.name = param.getFirst("name"); }
		if (param.getFirst("price") != null )  {this.price = Integer.parseInt(param.getFirst("price")); }
	}

	public void clear(){
		this.id = 0;
		this.name = null;
		this.price = 0;
	}

	public int getId(){ return  this.id; }
	public String getName(){ return  this.name; }
	public int getPrice(){ return  this.price; }

	public ArticlesFiltre setId(int value ){ this.id = value; return this; }
	public ArticlesFiltre setName(String value ){ this.name = value; return this; }
	public ArticlesFiltre setPrice(int value ){ this.price = value; return this; }
}
