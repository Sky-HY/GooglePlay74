package com.example.googleplay74.http.protocol;

import com.example.googleplay74.domain.AppInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * home页面请求网络加载数据
 */
public class HomeProtocol extends BaseProtocol<ArrayList<AppInfo>> {
    @Override
    public String getParamter() {
        return "";
    }

    @Override
    public String getKey() {
        return "home";
    }

    @Override
    public ArrayList<AppInfo> paserJson(String result) {
        ArrayList<AppInfo> appInfos = null;
        try {
            JSONObject jo = new JSONObject(result);
            JSONArray ja = jo.getJSONArray("list");
            appInfos = new ArrayList<>();
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo1 = ja.getJSONObject(i);
                AppInfo info = new AppInfo();
                info.des = jo1.getString("des");
                info.downloadUrl = jo1.getString("downloadUrl");
                info.iconUrl = jo1.getString("iconUrl");
                info.id = jo1.getString("id");
                info.name = jo1.getString("name");
                info.packageName = jo1.getString("packageName");
                info.size = jo1.getLong("size");
                info.stars = (float) jo1.getDouble("stars");
                appInfos.add(info);
            }



            return appInfos;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
