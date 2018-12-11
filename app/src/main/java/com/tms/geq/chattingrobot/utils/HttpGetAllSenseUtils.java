package com.tms.geq.chattingrobot.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;

public class HttpGetAllSenseUtils {
    private static final int TIMEOUT_IN_MILLIONS = 5000;

    public static String doPost(String url, String param) {

        JSONObject jsonObject = new JSONObject();


        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            conn.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            conn.setRequestProperty("charset", "utf-8");
           // conn.setHeader("Content-Type", "application/json");
            conn.setUseCaches(false);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
            conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);
            int responseCode = conn.getResponseCode();
            if (responseCode!=200){
                return "";
            }
            if (param != null && !param.trim().equals("")) {
                // 获取URLConnection对象对应的输出流
                out = new PrintWriter(conn.getOutputStream());
                // 发送请求参数
                out.print(param);
                // flush输出流的缓冲
                out.flush();
            }
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }



    public static String JsonPost(String pars) {
        final String path="http://openapi.tuling123.com/openapi/api/v2";
        pars = "{\n" +
                "  \"reqType\": 0,\n" +
                "  \"perception\": {\n" +
                "    \"inputText\": {\n" +
                "      \"text\": \""+pars+"\"\n" +
                "    },\n" +
                "    \"inputImage\": {\n" +
                "      \"url\": \"imageUrl\"\n" +
                "    },\n" +
                "    \"selfInfo\": {\n" +
                "      \"location\": {\n" +
                "        \"city\": \"北京\",\n" +
                "        \"province\": \"北京\",\n" +
                "        \"street\": \"信息路\"\n" +
                "      }\n" +
                "    }\n" +
                "  },\n" +
                "  \"userInfo\": {\n" +
                "    \"apiKey\": \"f3ab09a3fc794fc9acfacacd28de6de7\",\n" +
                "    \"userId\": \"142\"\n" +
                "  }\n" +
                "}";
        JSONObject json = null;
        try {
            json = new JSONObject(pars);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        BufferedReader in = null;
        String result = "";
        OutputStream os = null;
        try {
            URL url = new URL(path);
// 然后我们使用httpPost的方式把lientKey封装成Json数据的形式传递给服务器
// 在这里呢我们要封装的时这样的数据
// 我们把JSON数据转换成String类型使用输出流向服务器写
            String content = String.valueOf(json);
// 现在呢我们已经封装好了数据,接着呢我们要把封装好的数据传递过去
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(8000);
// 设置允许输出
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
// 设置User-Agent: Fiddler
            conn.setRequestProperty("ser-Agent", "Fiddler");
// 设置contentType
            conn.setRequestProperty("Content-Type", "application/json");
            os = conn.getOutputStream();
            os.write(content.getBytes());
            os.flush();
// 定义BufferedReader输入流来读取URL的响应
// Log.i("-----send", "end");

            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            if (conn.getResponseCode() == 200) {
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            }
        } catch (SocketTimeoutException e) {
// Log.i("错误", "连接时间超时");
            e.printStackTrace();
            return "连接时间超时错误";
        } catch (MalformedURLException e) {
// Log.i("错误", "jdkfa");
            e.printStackTrace();
            return "连接错误1";
        } catch (ProtocolException e) {
// Log.i("错误", "jdkfa");
            e.printStackTrace();
            return "连接错误2";
        } catch (IOException e) {
// Log.i("错误", "jdkfa");
            e.printStackTrace();
            return "连接错误3";
        }// 使用finally块来关闭输出流、输入流
        catch (Exception e) {
            e.printStackTrace();
            return "连接错误4";
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;

    }

}
