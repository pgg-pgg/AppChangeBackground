package com.example.skinlibrary.http.provider;


import com.example.skinlibrary.http.callback.HttpCallBack;
import com.example.skinlibrary.http.callback.adapter.FileHttpCallBack;
import com.example.skinlibrary.http.provider.base.IHttpProvider;
import com.example.skinlibrary.http.request.HttpRequest;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;

import okhttp3.Call;

/**
 * Created by _SOLID
 * Date:2016/5/13
 * Time:11:20
 */
public class OKHttpProvider implements IHttpProvider {

    @Override
    public void loadString(final HttpRequest request, final HttpCallBack callBack) {
        if (request.getMethod() == HttpRequest.Method.GET) {
            OkHttpUtils
                    .get()
                    .url(request.getUrl())
                    .params(request.getParams())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e) {
                            callBack.onError(e);
                        }

                        @Override
                        public void onResponse(String response) {
                            callBack.onSuccess(callBack.parseData(response));
                        }
                    });
        } else {
            OkHttpUtils
                    .post()
                    .url(request.getUrl())
                    .params(request.getParams())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e) {
                            callBack.onError(e);
                        }

                        @Override
                        public void onResponse(String response) {
                            callBack.onSuccess(callBack.parseData(response));
                        }
                    });
        }
    }

    @Override
    public void download(String downloadUrl, String savePath, final FileHttpCallBack callBack) {

        String saveDir = savePath.substring(0, savePath.lastIndexOf("/"));
        String fileName = savePath.substring(savePath.lastIndexOf("/") + 1);

        OkHttpUtils
                .get()
                .url(downloadUrl)
                .build()
                .execute(new FileCallBack(saveDir, fileName) {

                    @Override
                    public void onError(Call call, Exception e) {
                        callBack.onError(e);
                    }

                    @Override
                    public void onResponse(File response) {
                        callBack.onSuccess(response.getAbsolutePath());
                    }

                    @Override
                    public void inProgress(float progress, long total) {
                        callBack.onProgress(total, (long) (progress * total), (int) (progress * 100));
                    }
                });
    }
}
