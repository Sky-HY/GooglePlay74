package com.example.googleplay74.http.protocol;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * 排行的网络请求数据
 */
public class HotProtocol extends BaseProtocol<List<String>> {
    @Override
    public String getParamter() {
        return "";
    }

    @Override
    public String getKey() {
        return "hot";
    }

    @Override
    public List<String> paserJson(String result) {
        try {
            ArrayList<String> list = new ArrayList<>();
            JSONArray ja = new JSONArray(result);
            for (int i = 0; i < ja.length(); i++) {
                String s = ja.getString(i);
                list.add(s);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
