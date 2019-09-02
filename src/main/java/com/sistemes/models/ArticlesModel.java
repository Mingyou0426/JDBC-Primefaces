package com.sistemes.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.core.MultivaluedMap;
import java.sql.Timestamp;

public class ArticlesModel {

    private int id;
    private String name;
    private int price;

    public ArticlesModel(){
        clear();
    }

    public ArticlesModel(MultivaluedMap<String, String> param){
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

    /* public ArticlesModel setId(int value ){ this.id = value; return this; }
    public ArticlesModel setName(String value ){ this.name = value; return this; }
    public ArticlesModel setPrice(int value ){ this.price = value; return this; } */

    public void setId(int value ){ this.id = value; }
    public void setName(String value ){ this.name = value; }
    public void setPrice(int value ){ this.price = value; }

    public ArticlesModel copy(ArticlesModel valor){
        this.setId(valor.getId());
        this.setName(valor.getName());
        this.setPrice(valor.getPrice());
        return this;
    }

    public ArticlesModel clone(ArticlesModel valor){
        ArticlesModel element = new ArticlesModel();
        element.setId(valor.getId());
        element.setName(valor.getName());
        element.setPrice(valor.getPrice());
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
    public static ArticlesModel fromJson(String json){
/*				Gson gson = new Gson();
        ArticlesModel element;
        element = gson.fromJson(json);
        return element;
*/
        return null;
    }
}