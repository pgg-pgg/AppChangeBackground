package com.example.skinlibrary.fragment;

import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.example.skinlibrary.R;
import com.example.skinlibrary.activity.ViewPicActivity;
import com.example.skinlibrary.fragment.base.BaseFragment;
import com.example.skinlibrary.http.HttpHelper;
import com.example.skinlibrary.http.ImageLoader;
import com.example.skinlibrary.http.callback.adapter.FileHttpCallBack;
import com.example.skinlibrary.http.request.ImageRequest;
import com.example.skinlibrary.utils.FileUtils;
import com.example.skinlibrary.utils.SnackBarUtils;
import com.example.skinlibrary.utils.SystemShareUtils;

import java.io.File;
import java.util.ArrayList;


import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;
import uk.co.senab.photoview.log.Logger;

/**
 * Created by _SOLID
 * Date:2016/4/22
 * Time:14:30
 */
public class ViewPicFragment extends BaseFragment {

    private ViewPager mViewPager;

    private ArrayList<String> mUrlList;

    private String mSavePath;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_view_pic;
    }

    @Override
    protected void init() {
        mUrlList = getArguments().getStringArrayList(ViewPicActivity.IMG_URLS);
    }

    @Override
    protected void setUpView() {
        mViewPager = $(R.id.viewpager);
        mViewPager.setAdapter(new MyViewPager());

    }

    private class MyViewPager extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            PhotoView photoView = new PhotoView(getMContext());

            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            photoView.setLayoutParams(layoutParams);

            //setUpPhotoViewAttacher(photoView);

            ImageRequest imageRequest = new ImageRequest.Builder().imgView(photoView).url(mUrlList.get(position)).create();
            ImageLoader.getProvider().loadImage(imageRequest);

            container.addView(photoView);

            return photoView;
        }

        private void setUpPhotoViewAttacher(PhotoView photoView) {
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(photoView);
            photoViewAttacher.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                @Override
                public void onViewTap(View view, float v, float v1) {
                    ViewPicActivity activity = (ViewPicActivity) getMContext();
                    activity.hideOrShowToolbar();
                }
            });
        }

        @Override
        public int getCount() {
            return mUrlList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    /**
     * download image
     *
     * @param action 0:save 1:share
     */
    public void downloadPicture(final int action) {
        mSavePath = FileUtils.getSaveImagePath(getMContext()) + File.separator + FileUtils.getFileName(mUrlList.get(0));

        HttpHelper.getProvider().download(mUrlList.get(0), mSavePath, new FileHttpCallBack() {
            @Override
            public void onSuccess(String filePath) {
                if (action == 0) {
                    SnackBarUtils.makeLong(mViewPager, "已保存至:" + filePath).warning();
                } else {
                    SystemShareUtils.shareImage(getMContext(), Uri.parse(filePath));
                }
            }

            @Override
            public void onProgress(long totalBytes, long downloadedBytes, int progress) {
            }

            @Override
            public void onError(Exception e) {
                if (action == 0)
                    SnackBarUtils.makeLong(mViewPager, "保存失败:" + e.getMessage()).danger();
            }
        });
    }
}
