package com.example.skinlibrary.http.provider.base;


import com.example.skinlibrary.http.callback.HttpCallBack;
import com.example.skinlibrary.http.callback.adapter.FileHttpCallBack;
import com.example.skinlibrary.http.request.HttpRequest;

/**
 * Created by _SOLID
 * Date:2016/5/13
 * Time:9:49
 */
public interface IHttpProvider {

    void loadString(HttpRequest request, HttpCallBack callBack);

    void download(String downloadUrl, String savePath, FileHttpCallBack callBack);
}
