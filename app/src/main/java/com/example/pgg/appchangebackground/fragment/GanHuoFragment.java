package com.example.pgg.appchangebackground.fragment;

import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.pgg.appchangebackground.R;
import com.example.pgg.appchangebackground.adapter.GanHuoPagerAdapter;
import com.example.skinlibrary.fragment.base.BaseFragment;


/**
 * Created by _SOLID
 * Date:2016/4/18
 * Time:15:30
 */
public class GanHuoFragment extends BaseFragment {

    private static String TAG="GanHuoFragment";

    private ViewPager mViewPager;
    private GanHuoPagerAdapter mAdapter;
    private TabLayout mTabLayout;
    private String[] mTitles;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_gan_huo;
    }

    @Override
    protected void setUpView() {

        mTitles = new String[]{"all", "休息视频", "福利", "Android", "iOS", "拓展资源", "前端", "瞎推荐"};
        mTabLayout = $(R.id.sliding_tabs);
        mViewPager = $(R.id.viewpager);
        mAdapter = new GanHuoPagerAdapter(getChildFragmentManager(), mTitles);

        for (int i = 0; i < mTitles.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitles[i]));
        }

        new SetAdapterTask().execute();
        dynamicAddSkinView(mTabLayout, "tabIndicatorColor", R.color.colorAccent);
    }


    private class SetAdapterTask extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            if (mAdapter != null) {
                mViewPager.setAdapter(mAdapter);
                mTabLayout.setupWithViewPager(mViewPager);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter = new GanHuoPagerAdapter(getChildFragmentManager(), mTitles);
        Log.i(TAG,"onResume");
    }
}
