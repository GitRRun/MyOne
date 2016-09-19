package com.example.administrator.myone.http;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/9/19.
 */
public class HttpUtils {
    public static String getData(String path){
        String str ="";
        try {
            URL url=new URL(path);
           HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int code =connection.getResponseCode();
            Log.e("==","code==="+code);
            if (code==200){
                InputStream in = connection.getInputStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                StringBuffer sb=new StringBuffer();
                while((str=reader.readLine())!=null){
                    sb.append(str);
                }
                return sb.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
