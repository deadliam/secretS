package com.example.deadliam.parser;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;

public class JSONParser {

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    // constructor
    public JSONParser() {

    }

    // метод получение json объекта по url
    // используя HTTP запрос и методы POST или GET
    public JSONObject makeHttpRequest(String url, String method, List<NameValuePair> params) {

        // Создаем HTTP запрос
        try {
            Log.d("my_log: ", "11");
            // проверяем метод HTTP запроса
            if(Objects.equals(method, "POST")){
                Log.d("my_log: ", "12");
                DefaultHttpClient httpClient = new DefaultHttpClient();
                Log.d("my_log: ", "13");
                HttpPost httpPost = new HttpPost(url);
                Log.d("my_log: ", "14");
                httpPost.setEntity(new UrlEncodedFormEntity(params));
                Log.d("my_log: ", "15");
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            }else if(Objects.equals(method, "GET")){
                Log.d("my_log: ", "21");
                DefaultHttpClient httpClient = new DefaultHttpClient();
                Log.d("my_log: ", "22");
                String paramString = URLEncodedUtils.format(params, "utf-8");
                Log.d("my_log: ", "23");
                url += "?" + paramString;
                Log.d("my_log: ", "24");
                HttpGet httpGet = new HttpGet(url);
                Log.d("my_log: ", "25");

                HttpResponse httpResponse = httpClient.execute(httpGet);
                Log.d("my_log: ", "26");
                HttpEntity httpEntity = httpResponse.getEntity();
                Log.d("my_log: ", "27");
                is = httpEntity.getContent();
                Log.d("my_log: ", "28");
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Log.d("my_log: ", "31");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            Log.d("my_log: ", "32");
            StringBuilder sb = new StringBuilder();
            Log.d("my_log: ", "33");
            String line = null;
            while ((line = reader.readLine()) != null) {
                Log.d("my_log: ", "34");
                sb.append(line).append("\n");
            }
            is.close();
            Log.d("my_log: ", "35");
            json = sb.toString();
            Log.d("my_log: ", "36");
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // пытаемся распарсить строку в JSON объект
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // возвращаем JSON строку
        Log.d("my_log: ", "40");
        return jObj;

    }

}
