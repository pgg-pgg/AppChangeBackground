package com.example.skinloader.attr;

import android.view.View;
import android.widget.TextView;

import com.example.skinloader.load.SkinManager;

/**
 * Created by pgg on 18-7-25.
 * 文字颜色属性
 */

public class TextColorAttr extends SkinAttr{

    @Override
    public void apply(View view) {
        if (view instanceof TextView){
            TextView tv=(TextView) view;
            if (RES_TYPE_NAME_COLOR.equals(attrValueTypeName)){
                tv.setTextColor(SkinManager.getInstance().convertToColorStateList(attrValueRefId));
            }
        }
    }
}
