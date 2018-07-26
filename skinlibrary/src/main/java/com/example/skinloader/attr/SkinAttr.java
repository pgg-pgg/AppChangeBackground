package com.example.skinloader.attr;

import android.view.View;

/**
 * Created by pgg on 18-7-24.
 * 皮肤属性类
 */

public abstract class SkinAttr {

    protected static final String RES_TYPE_NAME_COLOR="color";
    protected static final String RES_TYPE_NAME_DRAWABLE="drawable";

    /**
     * 属性名
     */
    public String attrName;

    /**
     * 属性值引用的ID
     */
    public int attrValueRefId;

    /**
     * 资源的名字
     */
    public String attrValueRefName;

    /**
     * 值的类型，color或者drawable
     */
    public String attrValueTypeName;

    /**
     * 将新的类型值赋值给view
     * @param view
     */
    public abstract void apply(View view);

    @Override
    public String toString() {
        return "SkinAttr{" +
                "attrName='" + attrName + '\'' +
                ", attrValueRefId=" + attrValueRefId +
                ", attrValueRefName='" + attrValueRefName + '\'' +
                ", attrValueTypeName='" + attrValueTypeName + '\'' +
                '}';
    }
}
