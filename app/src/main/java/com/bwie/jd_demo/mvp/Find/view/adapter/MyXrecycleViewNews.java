package com.bwie.jd_demo.mvp.Find.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.mvp.Find.model.bean.NewsBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class MyXrecycleViewNews extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {
    private List<NewsBean.ResultBean.DataBean> list;

    public MyXrecycleViewNews(List<NewsBean.ResultBean.DataBean> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        NewsBean.ResultBean.DataBean dataBean = list.get(position);
        String thumbnail_pic_s03 = dataBean.getThumbnail_pic_s03();
        String thumbnail_pic_s02 = dataBean.getThumbnail_pic_s02();
        String thumbnail_pic_s01 = dataBean.getThumbnail_pic_s();
        if (!TextUtils.isEmpty(thumbnail_pic_s03)) {
            return 1;
        }
        if (!TextUtils.isEmpty(thumbnail_pic_s02)) {
            return 0;
        }

        return 0;
    }

    @NonNull
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.xrecycle_find_news02, parent, false);
            MyViewHolder02 myViewHolder02 = new MyViewHolder02(view);
            return myViewHolder02;
        } else if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.xrecycle_find_news, parent, false);
            MyViewHolder01 myViewHolder01 = new MyViewHolder01(view);
            return myViewHolder01;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull XRecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder01) {
            MyViewHolder01 holder01 = (MyViewHolder01) holder;
            holder01.titleOne.setText(list.get(position).getTitle());
            holder01.picOne.setImageURI(list.get(position).getThumbnail_pic_s());
        } else if (holder instanceof MyViewHolder02) {
            MyViewHolder02 holder02 = (MyViewHolder02) holder;
            holder02.titleTwo.setText(list.get(position).getTitle());
            holder02.pic01Two.setImageURI(list.get(position).getThumbnail_pic_s());
            holder02.pic02Two.setImageURI(list.get(position).getThumbnail_pic_s02());
            holder02.pic03Two.setImageURI(list.get(position).getThumbnail_pic_s03());

        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder01 extends RecyclerView.ViewHolder {

        private final TextView titleOne;
        private final SimpleDraweeView picOne;

        public MyViewHolder01(View itemView) {
            super(itemView);
            titleOne = itemView.findViewById(R.id.find_news_title_one);
            picOne = itemView.findViewById(R.id.find_news_pic_one);
        }
    }

    public class MyViewHolder02 extends RecyclerView.ViewHolder {

        private final SimpleDraweeView pic01Two;
        private final SimpleDraweeView pic02Two;
        private final SimpleDraweeView pic03Two;
        private final TextView titleTwo;

        public MyViewHolder02(View itemView) {
            super(itemView);
            titleTwo = itemView.findViewById(R.id.find_news_title_two);
            pic01Two = itemView.findViewById(R.id.find_news_pic01_two);
            pic02Two = itemView.findViewById(R.id.find_news_pic02_two);
            pic03Two = itemView.findViewById(R.id.find_news_pic03_two);

        }
    }
}
