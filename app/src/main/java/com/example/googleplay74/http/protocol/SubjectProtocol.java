package com.example.googleplay74.http.protocol;

import com.example.googleplay74.domain.SubjectInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SubjectProtocol extends BaseProtocol<List<SubjectInfo>> {
    @Override
    public String getParamter() {
        return "";
    }

    @Override
    public String getKey() {
        return "subject";
    }

    @Override
    public List<SubjectInfo> paserJson(String result) {
        try {
            JSONArray ja = new JSONArray(result);

            ArrayList<SubjectInfo> list = new ArrayList<SubjectInfo>();
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = ja.getJSONObject(i);

                SubjectInfo info = new SubjectInfo();
                info.des = jo.getString("des");
                info.url = jo.getString("url");

                list.add(info);
            }

            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
