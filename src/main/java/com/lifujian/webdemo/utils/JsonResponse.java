package com.lifujian.webdemo.utils;

public class JsonResponse {

    public static final int CODE_SERVER_ERROR = -1;
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_CLIENT_ERROR = 1;
    public static final int CODE_NO_PERMISSION = 99;
    public static final int CODE_TOKEN_INVALID = 98;
    public static final int CODE_CHANNEL_ERROR = 400;

    public static final String MSG_SUCCESS = "操作成功";
    public static final String MSG_ERROR_SERVER = "服务器错误";
    public static final String MSG_ERROR_CHANNEL = "渠道名称不存在";

    private int code;

    private String message;

    private Object data;

    public JsonResponse(int code) {
        this.code = code;
    }

    public JsonResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public JsonResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static String toJsonResponse(Object data) {
        if(null == data){

        }
        return Constant.gson.toJson(new JsonResponse(JsonResponse.CODE_SUCCESS, JsonResponse.MSG_SUCCESS, data));
    }

    public static String toJsonResponse(int code, String message, Object data) {
        return Constant.gson.toJson(new JsonResponse(code, message, data));
    }

    public static String toJsonResponse(int code, String message) {
        return Constant.gson.toJson(new JsonResponse(code, message, null));
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
