package com.example.skinloader.attr;

/**
 * Created by pgg on 18-7-24.
 * 动态属性
 */

public class DynamicAttr {

    /**
     * 属性名称
     */
    public String attrName;

    /**
     * 属性ID
     */
    public int refResId;

    public DynamicAttr(String attrName,int refResId){
        this.attrName=attrName;
        this.refResId=refResId;
    }
}
