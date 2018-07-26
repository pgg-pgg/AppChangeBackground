package com.example.skinlibrary.http;

import android.widget.ImageView;

import com.example.skinlibrary.http.callback.HttpCallBack;
import com.example.skinlibrary.http.request.HttpRequest;
import com.example.skinlibrary.http.request.ImageRequest;

import java.util.Map;

/**
 * Created by _SOLID
 * Date:2016/5/14
 * Time:11:03
 */
public class HttpClientManager {

    public static void displayImage(ImageView iv, String url) {
        ImageRequest request = new ImageRequest.Builder().url(url).imgView(iv).create();
        ImageLoader.getProvider().loadImage(request);
    }

    public static void getData(String url, HttpCallBack callBack) {
        HttpRequest request = new HttpRequest.Builder().method(HttpRequest.Method.GET).url(url).create();
        HttpHelper.getProvider().loadString(request, callBack);
    }

    public static void getData(String url, Map<String, String> params, HttpCallBack callBack) {
        HttpRequest request = new HttpRequest.Builder().method(HttpRequest.Method.GET).url(url).params(params).create();
        HttpHelper.getProvider().loadString(request, callBack);
    }
}
