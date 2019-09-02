package com.sistemes.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.core.MultivaluedMap;
import java.sql.Timestamp;

public class UsersModel {

    private int id;
    private String name;
    private String username;
    private String password;

    public UsersModel(){
        clear();
    }

    public UsersModel(MultivaluedMap<String, String> param){
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

    /* public UsersModel setId(int value ){ this.id = value; return this; }
    public UsersModel setName(String value ){ this.name = value; return this; }
    public UsersModel setUsername(String value ){ this.username = value; return this; }
    public UsersModel setPassword(String value ){ this.password = value; return this; } */

    public void setId(int value ){ this.id = value; }
    public void setName(String value ){ this.name = value; }
    public void setUsername(String value ){ this.username = value; }
    public void setPassword(String value ){ this.password = value; }

    public UsersModel copy(UsersModel valor){
        this.setId(valor.getId());
        this.setName(valor.getName());
        this.setUsername(valor.getUsername());
        this.setPassword(valor.getPassword());
        return this;
    }

    public UsersModel clone(UsersModel valor){
        UsersModel element = new UsersModel();
        element.setId(valor.getId());
        element.setName(valor.getName());
        element.setUsername(valor.getUsername());
        element.setPassword(valor.getPassword());
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
    public static UsersModel fromJson(String json){
/*				Gson gson = new Gson();
				UsersModel element;
				element = gson.fromJson(json);
				return element;
*/
        return null;
    }
}
