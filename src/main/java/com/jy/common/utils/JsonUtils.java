package com.jy.common.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class JsonUtils {



	public static <T> T toObject(String json, Class<T> classOfT){
		Gson gson = new Gson();
		T t = gson.fromJson(json, classOfT);
		return t;
	}

	public static String toJson(Object o){
		Gson gson = new Gson();
		Type  type = new TypeToken<Object>(){}.getType();
		String json = gson.toJson(o, type);
		return json;
	}
	
}
