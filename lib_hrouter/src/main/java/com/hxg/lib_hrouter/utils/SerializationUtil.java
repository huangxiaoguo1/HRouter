package com.hxg.lib_hrouter.utils;


import com.google.gson.Gson;

public class SerializationUtil {
    public static String object2Json(Object value) {
        return new Gson().toJson(value);
    }
}
