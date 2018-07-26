package com.example.skinloader.attr;

import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;

import com.example.skinloader.load.SkinManager;

/**
 * Created by pgg on 18-7-25.
 */

public class TabLayoutAttr extends SkinAttr {

    @Override
    public void apply(View view) {
        if (view instanceof TabLayout){
            TabLayout tl = (TabLayout) view;
            if (RES_TYPE_NAME_COLOR.equals(attrValueTypeName)) {
                Log.i("TabLayoutAttr", "apply color");
                int color = SkinManager.getInstance().getColor(attrValueRefId);
                tl.setSelectedTabIndicatorColor(color);
            } else if (RES_TYPE_NAME_DRAWABLE.equals(attrValueTypeName)) {
                Log.i("TabLayoutAttr", "apply drawable");
                //  tv.setDivider(SkinManager.getInstance().getDrawable(attrValueRefId));
            }
        }
    }
}
