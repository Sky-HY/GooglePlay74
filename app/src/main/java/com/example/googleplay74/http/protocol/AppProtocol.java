package com.example.googleplay74.http.protocol;

import com.example.googleplay74.domain.AppInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * app页面加载网络数据的类
 */
public class AppProtocol extends BaseProtocol<List<AppInfo>> {
    @Override
    public String getParamter() {
        return "";
    }

    @Override
    public String getKey() {
        return "app";
    }

    @Override
    public List<AppInfo> paserJson(String result) {
        try {
            JSONArray ja = new JSONArray(result);
            ArrayList<AppInfo> list = new ArrayList<>();
            AppInfo info;
            for (int i = 0; i < ja.length(); i++) {
                info = new AppInfo();
                JSONObject jo = ja.getJSONObject(i);
                info.des = jo.getString("des");
                info.downloadUrl = jo.getString("downloadUrl");
                info.iconUrl = jo.getString("iconUrl");
                info.id = jo.getString("id");
                info.name = jo.getString("name");
                info.packageName = jo.getString("packageName");
                info.size = jo.getLong("size");
                info.stars = (float) jo.getDouble("stars");
                list.add(info);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
}
