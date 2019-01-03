package com.example.googleplay74.http.protocol;

import com.example.googleplay74.domain.CateGoryInfo;
import com.example.googleplay74.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类获取并解析网络数据
 */
public class CateGoryProtocol extends BaseProtocol<List<CateGoryInfo>> {
    private static final String TAG = "CateGoryProtocol";

    @Override
    public String getParamter() {
        return "";
    }

    @Override
    public String getKey() {
        return "category";
    }

    @Override
    public List<CateGoryInfo> paserJson(String result) {
        try {
            JSONArray ja = new JSONArray(result);
            List<CateGoryInfo> list = new ArrayList<>();
            CateGoryInfo info;
            for (int i = 0; i < ja.length(); i++) {

                JSONObject jo = ja.getJSONObject(i);
                // 解析标题
                info = new CateGoryInfo();
                String title = jo.getString("title");
                info.isTitle = true;
                info.title = title;
                list.add(info);
                LogUtil.i(TAG, "标题" + info);
                // 解析内容
                JSONArray ja2 = jo.getJSONArray("infos");
                for (int j = 0; j < ja2.length(); j++) {
                    info = new CateGoryInfo();
                    JSONObject jo2 = ja2.getJSONObject(j);
                    info.name1 = jo2.getString("name1");
                    info.name2 = jo2.getString("name2");
                    info.name3 = jo2.getString("name3");
                    info.url1 = jo2.getString("url1");
                    info.url2 = jo2.getString("url2");
                    info.url3 = jo2.getString("url3");
                    info.isTitle = false;
                    list.add(info);
                    LogUtil.i(TAG, "内容" + info);
                }

            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
