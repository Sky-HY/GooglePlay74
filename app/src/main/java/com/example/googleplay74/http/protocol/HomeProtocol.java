package com.example.googleplay74.http.protocol;

import com.example.googleplay74.domain.AppInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * home页面请求网络加载数据
 */
public class HomeProtocol extends BaseProtocol<ArrayList<AppInfo>> {
    private ArrayList<String> pics;

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
        ArrayList<AppInfo> appInfos;
        try {
            JSONObject jo = new JSONObject(result);
            JSONArray ja = jo.getJSONArray("list");
            appInfos = new ArrayList<>();
            pics = new ArrayList<>();
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

            // 初始化轮播条的数据
            JSONArray ja1 = jo.getJSONArray("picture");
            pics = new ArrayList<>();
            for (int i = 0; i < ja1.length(); i++) {
                String pic = ja1.getString(i);
                pics.add(pic);
            }

            return appInfos;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 返回头条轮播的图片
    public List<String> getPicLists() {
        return pics;
    }
}
