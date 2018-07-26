package com.example.pgg.appchangebackground.fragment;

import android.content.Intent;
import android.view.View;

import com.example.pgg.appchangebackground.R;
import com.example.pgg.appchangebackground.activity.SettingActivity;
import com.example.skinlibrary.fragment.base.BaseFragment;


/**
 * Created by _SOLID
 * Date:2016/3/30
 * Time:11:54
 */
public class MovieFragment extends BaseFragment {

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_movie;
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        $(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getMContext(), SettingActivity.class));
            }
        });
    }
}
