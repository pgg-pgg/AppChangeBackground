package com.example.skinlibrary.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;

/**
 * Created by pgg on 18-7-26.
 * 剪贴板工具
 */

public class ClipboardUtils {

    private static android.text.ClipboardManager mClipboardManager;
    private static ClipboardManager mNewCliboardManager;

    private static boolean isNew(){
        return Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB;
    }

    private static void  instance(Context context){
        if (isNew()){
            if (mNewCliboardManager==null){
                mNewCliboardManager=(ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
            }
        }else {
            if (mClipboardManager==null){
                mClipboardManager=(android.text.ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
            }
        }
    }

    public static void setText(Context context,CharSequence charSequence){
        if (isNew()){
            instance(context);
            ClipData clipData = ClipData.newPlainText("simple text", charSequence);
            mNewCliboardManager.setPrimaryClip(clipData);
        }else {
            instance(context);
            mClipboardManager.setText(charSequence);
        }
    }

    public static CharSequence getText(Context context){
        StringBuilder sb=new StringBuilder();
        if (isNew()){
            instance(context);
            if (!mNewCliboardManager.hasPrimaryClip()){
                return sb.toString();
            }else {
                ClipData clipData=(mNewCliboardManager).getPrimaryClip();
                int count=clipData.getItemCount();
                for (int i=0;i<count;++i){
                    ClipData.Item item=clipData.getItemAt(i);
                    CharSequence charSequence = item.coerceToText(context);
                    sb.append(charSequence);
                }
            }
        }else {
            instance(context);
            sb.append(mClipboardManager.getText());
        }
        return sb.toString();
    }
}
