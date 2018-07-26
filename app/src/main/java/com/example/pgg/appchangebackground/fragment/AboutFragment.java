package com.example.pgg.appchangebackground.fragment;

import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

import com.example.pgg.appchangebackground.R;
import com.example.skinlibrary.fragment.base.BaseFragment;

/**
 * Created by pgg on 18-7-26.
 */

public class AboutFragment extends BaseFragment {
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_about;
    }

    @Override
    protected void setUpView() {
        TextView tv_content = $(R.id.tv_content);
        tv_content.setAutoLinkMask(Linkify.ALL);
        tv_content.setMovementMethod(LinkMovementMethod
                .getInstance());
    }

}
