package com.example.pgg.appchangebackground.activity;

import android.support.v4.app.Fragment;

import com.example.pgg.appchangebackground.fragment.AboutFragment;
import com.example.skinlibrary.activity.ToolbarActivity;


public class SettingActivity extends ToolbarActivity {


    @Override
    protected String getToolbarTitle() {
        return "设置";
    }

    @Override
    protected Fragment setFragment() {
        return new AboutFragment();
    }
}
