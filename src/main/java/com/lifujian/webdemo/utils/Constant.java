package com.lifujian.webdemo.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author itlfj
 * @time 2019/06/22 23:28
 * @description 一些公用常量
 */
public class Constant {
	
    public static final Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
    
}
