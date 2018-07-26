package com.example.skinloader.listener;

import android.view.View;

import com.example.skinloader.attr.DynamicAttr;

import java.util.List;

/**
 * Created by pgg on 18-7-24.
 * 动态加载View
 */

public interface IDynamicNewView {

    void dynamicAddView(View view, List<DynamicAttr> attrs);

}
