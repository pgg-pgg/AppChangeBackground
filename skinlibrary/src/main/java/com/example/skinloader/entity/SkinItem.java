package com.example.skinloader.entity;

import android.view.View;

import com.example.skinloader.attr.SkinAttr;
import com.example.skinloader.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pgg on 18-7-24.
 * 用来存储那些有皮肤更改需求的View及其对应的属性
 */

public class SkinItem {

    public View view;
    public List<SkinAttr> attrs;

    public SkinItem(){
        attrs=new ArrayList<>();
    }

    public void apply(){
        if (ListUtils.isEmpty(attrs)){
            return;
        }
        for (SkinAttr at:attrs){
            at.apply(view);
        }
    }

    public void clean(){
        if (ListUtils.isEmpty(attrs)){
            return;
        }
        for (SkinAttr at:attrs){
            at=null;
        }
    }

    @Override
    public String toString() {
        return "SkinItem [view=" + view.getClass().getSimpleName() + ", attrs=" + attrs + "]";
    }
}
