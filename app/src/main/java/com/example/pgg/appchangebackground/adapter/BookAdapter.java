package com.example.pgg.appchangebackground.adapter;

import android.content.Context;
import android.content.Intent;

import com.example.pgg.appchangebackground.R;
import com.example.pgg.appchangebackground.activity.BookDetailActivity;
import com.example.pgg.appchangebackground.bean.BookBean;
import com.example.skinlibrary.adapter.SolidRVBaseAdapter;

import java.util.List;


/**
 * Created by _SOLID
 * Date:2016/4/5
 * Time:11:34
 */
public class BookAdapter extends SolidRVBaseAdapter<BookBean> {

    public BookAdapter(Context context, List<BookBean> beans) {
        super(context, beans);
    }

    @Override
    public int getItemLayoutID(int vieWType) {
        return R.layout.item_book;
    }

    @Override
    protected void onItemClick(int position) {
        Intent intent = new Intent(mContext, BookDetailActivity.class);
        intent.putExtra("url", mBeans.get(position - 1).getUrl());
        mContext.startActivity(intent);
    }
    @Override
    protected void onBindDataToView(SolidCommonViewHolder holder, BookBean bean,int position) {
        holder.setText(R.id.tv_title, bean.getTitle());
        holder.setText(R.id.tv_price, "￥" + bean.getPrice());
        holder.setText(R.id.tv_author, "作者:" + bean.getAuthor() + "");
        holder.setText(R.id.tv_date, "出版日期:" + bean.getPubdate());
        holder.setText(R.id.tv_publisher, "出版社:" + bean.getPublisher());
        holder.setText(R.id.tv_num_rating, bean.getRating().getNumRaters() + "人评分");
        holder.setImageFromInternet(R.id.iv_image, bean.getImage());
    }
}
