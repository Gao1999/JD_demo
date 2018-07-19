package com.bwie.jd_demo.mvp.Find.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.mvp.Find.model.bean.WelfareBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class MyRecycleViewWelfare extends XRecyclerView.Adapter<MyRecycleViewWelfare.MyViewHolder> {
    private List<WelfareBean.DataBean> list;

    public MyRecycleViewWelfare(List<WelfareBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.xrexycle_find_welfare, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.e("tag", "onBindViewHolder: "+list.get(position).getUrl() );
        holder.simpleDraweeView.setImageURI(list.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView simpleDraweeView;

        public MyViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.find_welfare_pic);
        }
    }
}
