package com.example.pgg.appchangebackground.fragment;


import android.os.Bundle;

import com.example.skinlibrary.fragment.WebViewFragment;

/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:17:46
 */
public class BlogFragment extends WebViewFragment {


    public BlogFragment(){
        Bundle arguments = new Bundle();
        arguments.putString("url","https://blog.csdn.net/pgg_cold");
        setArguments(arguments);
    }



}
