package com.example.skinlibrary.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.skinloader.base.SkinBaseActivity;

/**
 * Created by pgg on 18-7-25.
 */

public abstract class BaseActivity extends SkinBaseActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(setLayoutResourceID());
        setUpView();

        setUpData();
    }

    protected void setUpData(){

    }


    protected abstract void setUpView();

    protected abstract int setLayoutResourceID();
    protected <T extends View> T $(int id) {
        return (T) super.findViewById(id);
    }

    protected void init() {

    }

    protected void startActivityWithoutExtras(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    protected void startActivityWithExtras(Class<?> clazz, Bundle extras) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(extras);
        startActivity(intent);

    }
}
