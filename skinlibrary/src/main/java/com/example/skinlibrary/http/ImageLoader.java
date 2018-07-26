package com.example.skinlibrary.http;


import com.example.skinlibrary.http.provider.PicassoImageLoaderProvider;
import com.example.skinlibrary.http.provider.base.IImageLoaderProvider;

/**
 * Created by _SOLID
 * Date:2016/5/13
 * Time:10:24
 */
public class ImageLoader {

    private static volatile IImageLoaderProvider mProvider;

    public static IImageLoaderProvider getProvider() {
        if (mProvider == null) {
            synchronized (ImageLoader.class) {
                if (mProvider == null) {
                    mProvider = new PicassoImageLoaderProvider();
                }
            }
        }
        return mProvider;
    }

}
