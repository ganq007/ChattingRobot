package com.tms.geq.chattingrobot.utils;

import java.net.URLEncoder;

public class HttpUtils {
    /**
     *
     * @param apikey   机器人机器号
     * @param msg  请求信息
     * @return   json数据
     */
    public static  String getData(String apikey ,String msg){
        String INFO = null;
        try{
            //转换编码f3ab09a3fc794fc9acfacacd28de6de7
            INFO = URLEncoder.encode(msg,"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        final String getURL="http://openapi.tuling123.com/openapi/api/v2?";
        return  null;
    }
}



