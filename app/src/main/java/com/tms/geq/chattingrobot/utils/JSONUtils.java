package com.tms.geq.chattingrobot.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class JSONUtils {

    public static int getCode(String result) {
        int code = -1;
        try {
            JSONObject json = new JSONObject(result);
            code = json.getJSONObject("intent").getInt("code");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return code;
    }

    //根据code解析
    public static String getResult(String json) {
        Log.e("-------", "run:------------------------ "+json +getCode(json));
        String  data ="";
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray results = jsonObject.getJSONArray("results");
            int code = getCode(json);
            switch (code) {
                //纯文本聊天
                //"results":[{"groupType":0,"resultType":"text",
                //    "values":{"text":"我是聪明可爱的小爱，认识你很高兴。"}}]}
                case 10004:
                    JSONObject values = jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("values");
                    Log.e("-------", "run:------------------------ "+values.getString("text"));
                    return values.getString("text");
                //URL地址
                //"results":
                //[{"groupType":1,"resultType":"url",
                //  "values":{"url":"http://touch.qunar.com/"}},
                //  {"groupType":1,"resultType":"text","values":{"text":"亲，已帮你找到列车信息"}}]
                case 10018:
                    String url = results.getJSONObject(0).getJSONObject("values").getString("url");
                    String text = results.getJSONObject(1).getJSONObject("values").getString("text");
                    return "<a href="+url+">"+"点位查看"+"</a>"+text;
                //图片查询
                case 10014:
                    //"results":[{"groupType":1,"resultType":"url",
                    //    "values":{"url":"http://m.image.so.com/i?q=%E8%B5%B5%E4%B8%BD%E9%A2%96"}},
                    //  {"groupType":1,"resultType":"text","values":{"text":"亲，已帮你找到图片"}}
                    String imageUrl = results.getJSONObject(0).getJSONObject("values").getString("url");
                    String text2 = results.getJSONObject(1).getJSONObject("values").getString("text");

                return "<a href="+imageUrl+">"+"点位查看"+"</a>"+text2;
                //菜谱查询
                case 10015:
                    String text3 = results.getJSONObject(0).getJSONObject("values").getString("text");
                    JSONArray jsonArray = results.getJSONObject(1).getJSONObject("values").getJSONArray("news");
                    StringBuffer detail_menu = new StringBuffer();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        detail_menu.append("菜名:"+object.getString("name")+"<br>"+
                                "原料:"+object.getString("info")+"<br>"+
                                "详情:"+"<a href="+object.getString("detailurl")+">点我查看</a>"+"<br>"+
                                "<img src="+object.getString("icon")+"/><br>"
                        );
                       return jsonObject.getString("text")+"<br>"+detail_menu.toString();
                    }
                    break;
                    default:
                        data="现在小青无法回答你"+code;
            }
        } catch (Exception e) {
            Log.e("json解析", "getResult: json解析异常了" );
        }
        return data;
    }

    public static List getResultMenu(String json) {

        return null;
    }

}
