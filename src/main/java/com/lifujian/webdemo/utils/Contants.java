package com.lifujian.webdemo.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author lifujian
 * @date 2018/09/10 16:18
 * @description 一些公用常量
 */
public class Contants {
	
    public static final Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
    
}
