package com.example.skinloader.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.skinloader.attr.DynamicAttr;
import com.example.skinloader.listener.IDynamicNewView;
import com.example.skinloader.listener.ISkinUpdate;
import com.example.skinloader.load.SkinInflaterFactory;
import com.example.skinloader.load.SkinManager;
import com.example.skinloader.statusbar.StatusBarBackground;

import java.util.List;

/**
 * Created by pgg on 18-7-24.
 * 需要实现换肤功能的Activity就要继承这个类
 */

public class SkinBaseActivity extends AppCompatActivity implements ISkinUpdate, IDynamicNewView{

    /**
     * 当前Activity是否需要响应皮肤更改需求
     */
    private boolean isResponseOnSkinChanging=true;
    private SkinInflaterFactory mSkinInflaterFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mSkinInflaterFactory=new SkinInflaterFactory();
        LayoutInflaterCompat.setFactory(getLayoutInflater(),mSkinInflaterFactory);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SkinManager.getInstance().attach(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().detach(this);
        mSkinInflaterFactory.clean();
    }

    @Override
    public void onThemeUpdate() {
        if (!isResponseOnSkinChanging){
            return;
        }
        mSkinInflaterFactory.applySkin();
        changeStatusColor();
    }

    private void changeStatusColor() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            int color=SkinManager.getInstance().getColorPrimaryDark();
            StatusBarBackground statusBarBackground = new StatusBarBackground(
                    this, color);
            if (color != -1)
                statusBarBackground.setStatusBarbackColor();
        }

    }

    @Override
    public void dynamicAddView(View view, List<DynamicAttr> attrs) {
        mSkinInflaterFactory.dynamicAddSkinEnableView(this, view, attrs);
    }

    protected void dynamicAddSkinEnableView(View view, String attrName, int attrValueResId) {
        mSkinInflaterFactory.dynamicAddSkinEnableView(this, view, attrName, attrValueResId);
    }

    protected void dynamicAddSkinEnableView(View view, List<DynamicAttr> pDAttrs) {
        mSkinInflaterFactory.dynamicAddSkinEnableView(this, view, pDAttrs);
    }

    final protected void enableResponseOnSkinChanging(boolean enable) {
        isResponseOnSkinChanging = enable;
    }
}
