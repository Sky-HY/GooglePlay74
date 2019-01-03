package com.example.googleplay74.http.protocol;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * 推荐模块，网络数据请求
 */
public class RecommendProtocol extends BaseProtocol<List<String>> {
    @Override
    public String getParamter() {
        return "";
    }

    @Override
    public String getKey() {
        return "recommend";
    }

    @Override
    public List<String> paserJson(String result) {
        try {
            ArrayList<String> list = new ArrayList<>();
            JSONArray ja = new JSONArray(result);
            for (int i = 0;i<ja.length();i++){
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
