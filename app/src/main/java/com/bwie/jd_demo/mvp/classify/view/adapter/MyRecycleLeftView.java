package com.bwie.jd_demo.mvp.classify.view.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.mvp.classify.model.bean.ClassifyLeftBean;

import java.util.List;

public class MyRecycleLeftView extends RecyclerView.Adapter<MyRecycleLeftView.MyViewHolder> {
    private List<ClassifyLeftBean.DataBean> list;

    public MyRecycleLeftView(List<ClassifyLeftBean.DataBean> list) {
        this.list = list;
    }

    public int thisPosition;

    public int getThisPosition() {
        return thisPosition;
    }

    public void setThisPosition(int thisPosition) {
        this.thisPosition = thisPosition;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.classify_recycle_left, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.title.setText(list.get(position).getName());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemListener.onClickItemListener(position);
            }
        });
        if (position == thisPosition) {
            holder.title.setBackgroundColor(Color.RED);
            holder.title.setTextColor(Color.WHITE);
        } else {
            holder.title.setBackgroundColor(Color.WHITE);
            holder.title.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.classify_left_recycle_title);
        }
    }

    private setOnClickItemListener onClickItemListener;

    public void setOnClickItemListener(setOnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    public interface setOnClickItemListener {
        void onClickItemListener(int position);
    }
}
