package com.example.skinlibrary.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.example.skinlibrary.R;
import com.example.skinlibrary.fragment.ViewPicFragment;

import java.util.ArrayList;

/**
 * Created by pgg on 18-7-25.
 */

public class ViewPicActivity extends ToolbarActivity {
    private static String TAG = "ViewPicActivity";
    public final static String IMG_URLS = "ImageUrls";
    private ArrayList<String> mUrlList;

    private ViewPicFragment mFragment;

    @Override
    public void init() {
        super.init();
        mUrlList = getIntent().getExtras().getStringArrayList(IMG_URLS);

    }

    @Override
    public void setUpView() {

        super.setUpView();

    }

    @Override
    protected String getToolbarTitle() {
        return "图片";
    }

    @Override
    protected Fragment setFragment() {
        mFragment = new ViewPicFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(IMG_URLS, mUrlList);
        mFragment.setArguments(bundle);
        return mFragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_viewpic_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save) {
            mFragment.downloadPicture(0);
            return true;
        } else if (id == R.id.action_share) {
            mFragment.downloadPicture(1);
        }

        return super.onOptionsItemSelected(item);
    }

}
