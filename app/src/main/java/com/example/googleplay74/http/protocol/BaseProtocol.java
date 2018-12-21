package com.example.googleplay74.http.protocol;

import android.text.TextUtils;

import com.example.googleplay74.utils.IOUtil;
import com.example.googleplay74.utils.LogUtil;
import com.example.googleplay74.utils.UIUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 连接网络访问数据，并生成缓存的基类
 */
public abstract class BaseProtocol<T> {
    private static final String TAG = "BaseProtocol";

    // 获取数据
    public T getData(int index) {
        String result;
        result = getCache(index);
        if (TextUtils.isEmpty(result)) {
            result = getDataFromServer(index);
        }

        if (result != null) {
            LogUtil.i(TAG, "result:" + result);
            return paserJson(result);
        }
        return null;
    }

    // 从服务器获取数据,index为分页角标
    private String getDataFromServer(final int index) {
        // okhttp工具类
        OkHttpClient client = new OkHttpClient();
        String url = "http://127.0.0.1:8090/" + getKey() + "?index=" + index + getParamter();
        LogUtil.i(TAG, "url:" + url);

        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String result = response.body().string();
            if (!TextUtils.isEmpty(result)) {
                // 写缓存
                setCache(index, result);
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtil.close(response);
        }
        return null;
    }


    // 将缓存写到缓存文件夹
    // key为文件名,result为内容
    public void setCache(int index, String result) {
        File cacheDir = UIUtil.getContext().getCacheDir();
        File cacheFile = new File(cacheDir, getKey() + "?index=" + index + getParamter());
        FileWriter fw = null;
        try {
            fw = new FileWriter(cacheFile);
            // 设置第一行为有效时间，半个效时
            long overTime = System.currentTimeMillis() + 30 * 60 * 1000;
            fw.write(overTime + "\n");
            fw.write(result);
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtil.close(fw);
        }
    }

    // 读缓存
    public String getCache(int index) {
        File cacheDir = UIUtil.getContext().getCacheDir();
        File cacheFile = new File(cacheDir, getKey() + "?index=" + index + getParamter());
        BufferedReader br = null;
        try {
            if (cacheFile.exists()) {
                br = new BufferedReader(new FileReader(cacheFile));
                String time = br.readLine();
                long overTime = Long.parseLong(time);
                long currentTimeMillis = System.currentTimeMillis();
                if (overTime > currentTimeMillis) {
                    String line;
                    StringBuffer sb = new StringBuffer();
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    return sb.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtil.close(br);
        }


        return null;
    }

    // 参数
    public abstract String getParamter();

    // 具体路径
    public abstract String getKey();

    // 解析json数据,子类实现
    public abstract T paserJson(String result);

}
