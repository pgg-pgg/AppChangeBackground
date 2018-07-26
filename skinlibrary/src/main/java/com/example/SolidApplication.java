package com.example;


import com.example.skinlibrary.utils.ToastUtils;
import com.example.skinloader.base.SkinBaseApplication;

/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:20:59
 */
public class SolidApplication extends SkinBaseApplication {
    private static SolidApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ToastUtils.init(this);
    }

    public static SolidApplication getInstance() {
        return mInstance;
    }
}
