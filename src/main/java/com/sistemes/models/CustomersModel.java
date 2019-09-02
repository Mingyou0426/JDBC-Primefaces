package com.sistemes.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.core.MultivaluedMap;
import java.sql.Timestamp;

public class CustomersModel {

    private int id;
    private String name;
    private int vatnumber;
    private String location;
    private String city;

    public CustomersModel(){
        clear();
    }

    public CustomersModel(MultivaluedMap<String, String> param){
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

    /* public CustomersModel setId(int value ){ this.id = value; return this; }
    public CustomersModel setName(String value ){ this.name = value; return this; }
    public CustomersModel setVatnumber(int value ){ this.vatnumber = value; return this; }
    public CustomersModel setLocation(String value ){ this.location = value; return this; }
    public CustomersModel setCity(String value ){ this.city = value; return this; } */

    public void setId(int value ){ this.id = value; }
    public void setName(String value ){ this.name = value; }
    public void setVatnumber(int value ){ this.vatnumber = value; }
    public void setLocation(String value ){ this.location = value; }
    public void setCity(String value ){ this.city = value; }

    public CustomersModel copy(CustomersModel valor){
        this.setId(valor.getId());
        this.setName(valor.getName());
        this.setVatnumber(valor.getVatnumber());
        this.setLocation(valor.getLocation());
        this.setCity(valor.getCity());
        return this;
    }

    public CustomersModel clone(CustomersModel valor){
        CustomersModel element = new CustomersModel();
        element.setId(valor.getId());
        element.setName(valor.getName());
        element.setVatnumber(valor.getVatnumber());
        element.setLocation(valor.getLocation());
        element.setCity(valor.getCity());
        return element;
    }
    public String toJson(){
        String result = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static CustomersModel fromJson(String json){
/*				Gson gson = new Gson();
				CustomersModel element;
				element = gson.fromJson(json);
				return element;
*/
        return null;
    }
}
