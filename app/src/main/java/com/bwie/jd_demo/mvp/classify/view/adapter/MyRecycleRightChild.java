package com.bwie.jd_demo.mvp.classify.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.mvp.classify.model.bean.ClassifyRightBean;
import com.bwie.jd_demo.mvp.classify.view.activity.ClassifyActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyRecycleRightChild extends RecyclerView.Adapter<MyRecycleRightChild.MyViewHolderChild> {
    private List<ClassifyRightBean.DataBean.ListBean> list;
    private Context context;

    public MyRecycleRightChild(List<ClassifyRightBean.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolderChild onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.classify_recycle_right_child, parent, false);
        MyViewHolderChild myViewHolderChild = new MyViewHolderChild(inflate);
        return myViewHolderChild;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderChild holder, final int position) {
        holder.name.setText(list.get(position).getName());
        holder.simpleDraweeView.setImageURI(list.get(position).getIcon());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClictItemListenerChild != null) {
                    onClictItemListenerChild.onClickListenerChild(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolderChild extends RecyclerView.ViewHolder {

        private final SimpleDraweeView simpleDraweeView;
        private final TextView name;

        public MyViewHolderChild(View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.classify_recycle_right_child_pic);
            name = itemView.findViewById(R.id.classify_recycle_right_child_name);

        }
    }

    public setOnClictItemListenerChild onClictItemListenerChild;

    public void setOnClictItemListenerChild(setOnClictItemListenerChild onClictItemListenerChild) {
        this.onClictItemListenerChild = onClictItemListenerChild;
    }

    public interface setOnClictItemListenerChild {
        void onClickListenerChild(int position);
    }
}
