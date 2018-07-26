package com.example.skinloader.load;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.skinloader.attr.AttrFactory;
import com.example.skinloader.attr.DynamicAttr;
import com.example.skinloader.attr.SkinAttr;
import com.example.skinloader.config.SkinConfig;
import com.example.skinloader.entity.SkinItem;
import com.example.skinloader.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pgg on 18-7-24.
 * 自定义的InflaterFactory，用来代替默认的InflaterFactory
 * 参考链接：http://willowtreeapps.com/blog/app-development-how-to-get-the-right-layoutinflater/
 */

public class SkinInflaterFactory implements LayoutInflaterFactory{
    private static String TAG = "SkinInflaterFactory";

    /**
     * 存储那些有皮肤更改需求的View及其对应的属性的集合
     */
    private List<SkinItem> mSkinItems = new ArrayList<SkinItem>();
    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        // 检测当前View是否有更换皮肤的需求
        boolean isSkinEnable=attrs.getAttributeBooleanValue(SkinConfig.NAMESPACE, SkinConfig.ATTR_SKIN_ENABLE, false);
        if (!isSkinEnable){
            return null;
            //返回null就使用默认的InflaterFactory
        }
        View view=createView(context,name,attrs);
        if (view==null){
            return null;
        }
        parseSkinAttr(context,attrs,view);
        return view;
    }

    /**
     * 搜集可更换皮肤的属性
     * @param context
     * @param attrs
     * @param view
     */
    private void parseSkinAttr(Context context, AttributeSet attrs, View view) {
        //存储View可更换皮肤属性的集合
        List<SkinAttr> viewAttrs=new ArrayList<>();
        //遍历当前View的属性
        for (int i=0;i<attrs.getAttributeCount();i++){
            String attrName=attrs.getAttributeName(i);//属性名
            String attrValue=attrs.getAttributeValue(i);//属性值

            if (!AttrFactory.isSupportedAttr(attrName)){
                continue;
            }
            if (attrValue.startsWith("@")){
                //引用类型 @color/red
                try {
                    int id=Integer.parseInt(attrValue.substring(1));
                    String entryName = context.getResources().getResourceEntryName(id);//入口名，如 text_id
                    String typeName = context.getResources().getResourceTypeName(id);//获取资源类型 color drawable...
                    SkinAttr skinAttr = AttrFactory.get(attrName, id, entryName, typeName);
                    if (skinAttr!=null){
                        viewAttrs.add(skinAttr);
                    }
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }catch (Resources.NotFoundException e){
                    e.printStackTrace();
                }
            }
        }
        if (!ListUtils.isEmpty(viewAttrs)){
            SkinItem skinItem=new SkinItem();
            skinItem.view=view;
            skinItem.attrs=viewAttrs;
            mSkinItems.add(skinItem);
            if (SkinManager.getInstance().isExternalSkin()){
                skinItem.apply();
            }
        }
    }

    /**
     * 通过name去实例化一个view
     * @param context
     * @param name  要被实例化View的全名
     * @param attrs View在布局文件中的XML的属性
     * @return
     */
    private View createView(Context context, String name, AttributeSet attrs) {
        Log.i(TAG, "createView:" + name);
        View view=null;
        try {
            if (-1==name.indexOf('.')){
                if ("View".equals(name)){
                    view= LayoutInflater.from(context).createView(name,"android.view.",attrs);
                }
                if (view==null){
                    view= LayoutInflater.from(context).createView(name,"android.widget.",attrs);
                }
                if (view==null){
                    view=LayoutInflater.from(context).createView(name,"android.webkit.",attrs);
                }
            }else {
                view=LayoutInflater.from(context).createView(name,null,attrs);
            }
        }catch (Exception e){
            view=null;
        }
        return view;
    }


    /**
     * 应用皮肤
     */
    public void applySkin() {
        if (ListUtils.isEmpty(mSkinItems)) {
            return;
        }

        for (SkinItem si : mSkinItems) {
            if (si.view == null) {
                continue;
            }
            si.apply();
        }
    }

    /**
     * 清除有皮肤更改需求的View及其对应的属性的集合
     */
    public void clean() {
        if (ListUtils.isEmpty(mSkinItems)) {
            return;
        }
        for (SkinItem si : mSkinItems) {
            if (si.view == null) {
                continue;
            }
            si.clean();
        }
    }

    public void addSkinView(SkinItem item) {
        mSkinItems.add(item);
    }

    /**
     * 动态添加那些有皮肤更改需求的View，及其对应的属性
     *
     * @param context
     * @param view
     * @param attrName       属性名
     * @param attrValueResId 属性资源id
     */
    public void dynamicAddSkinEnableView(Context context, View view, String attrName, int attrValueResId) {
        int id = attrValueResId;
        String entryName = context.getResources().getResourceEntryName(id);
        String typeName = context.getResources().getResourceTypeName(id);
        SkinAttr mSkinAttr = AttrFactory.get(attrName, id, entryName, typeName);
        SkinItem skinItem = new SkinItem();
        skinItem.view = view;
        List<SkinAttr> viewAttrs = new ArrayList<SkinAttr>();
        viewAttrs.add(mSkinAttr);
        skinItem.attrs = viewAttrs;
        skinItem.apply();
        addSkinView(skinItem);
    }

    /**
     * 动态添加那些有皮肤更改需求的View，及其对应的属性集合
     *
     * @param context
     * @param view
     * @param pDAttrs
     */
    public void dynamicAddSkinEnableView(Context context, View view, List<DynamicAttr> pDAttrs) {
        List<SkinAttr> viewAttrs = new ArrayList<SkinAttr>();
        SkinItem skinItem = new SkinItem();
        skinItem.view = view;

        for (DynamicAttr dAttr : pDAttrs) {
            int id = dAttr.refResId;
            String entryName = context.getResources().getResourceEntryName(id);
            String typeName = context.getResources().getResourceTypeName(id);
            SkinAttr mSkinAttr = AttrFactory.get(dAttr.attrName, id, entryName, typeName);
            viewAttrs.add(mSkinAttr);
        }

        skinItem.attrs = viewAttrs;
        skinItem.apply();
        addSkinView(skinItem);
    }

}
