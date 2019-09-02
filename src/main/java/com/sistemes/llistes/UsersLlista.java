package com.sistemes.llistes;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sistemes.models.UsersModel;

public class UsersLlista extends ArrayList<UsersModel> {

	private static final long serialVersionUID = 1L;

	public String toJson() {
		String result = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			result = mapper.writeValueAsString(this);
		}catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		 return result;
	}

}
