package com.example.skinlibrary.http.provider;

import android.content.Context;

import com.example.SolidApplication;
import com.example.skinlibrary.http.provider.base.IImageLoaderProvider;
import com.example.skinlibrary.http.request.ImageRequest;
import com.squareup.picasso.Picasso;


/**
 * Created by _SOLID
 * Date:2016/5/13
 * Time:10:27
 */
public class PicassoImageLoaderProvider implements IImageLoaderProvider {
    @Override
    public void loadImage(ImageRequest request) {
        Picasso.with(SolidApplication.getInstance()).load(request.getUrl()).placeholder(request.getPlaceHolder()).into(request.getImageView());
    }

    @Override
    public void loadImage(Context context, ImageRequest request) {
        Picasso.with(context).load(request.getUrl()).placeholder(request.getPlaceHolder()).into(request.getImageView());
    }
}
