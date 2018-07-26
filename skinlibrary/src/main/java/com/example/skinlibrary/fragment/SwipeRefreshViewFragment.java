package com.example.skinlibrary.fragment;

import android.support.v4.widget.SwipeRefreshLayout;

import com.example.skinlibrary.R;
import com.example.skinlibrary.fragment.base.BaseListFragment;
import com.example.skinlibrary.utils.StringUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by pgg on 18-7-26.
 */

public abstract class SwipeRefreshViewFragment<T> extends BaseListFragment{

    XRecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_swiprefresh_recyclerview;
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        mRecyclerView = $(R.id.recyclerview);
        mSwipeRefreshLayout = $(R.id.swipe_refresh_layout);

        mRecyclerView.setLayoutManager(setLayoutManager());
        mRecyclerView.setPullRefreshEnabled(false);
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                switchActionAndLoadData(ACTION_REFRESH);
            }
        });
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

            }
        });
    }

    @Override
    protected void setUpData() {
        super.setUpData();
        switchActionAndLoadData(ACTION_PRE_LOAD);
    }

    @Override
    protected void onDataErrorReceived() {
        loadComplete();
    }

    @Override
    protected void onDataSuccessReceived(String result) {
        if (!StringUtils.isNullOrEmpty(result)) {
            List<T> list = parseData(result);

            mAdapter.addAll(list, mCurrentAction == ACTION_REFRESH);
            if (mCurrentAction != ACTION_PRE_LOAD) loadComplete();
            //mLLReloadWarp.setVisibility(View.GONE);
        } else {
            onDataErrorReceived();
        }
    }

    @Override
    protected void loadComplete() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
