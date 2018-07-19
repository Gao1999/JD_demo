package com.bwie.jd_demo.mvp.home.view.adapter;

import com.bwie.jd_demo.R;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.jd_demo.mvp.home.model.bean.GoodsDetailBean;

public class MyRecycleGoods extends RecyclerView.Adapter<MyRecycleGoods.MyViewHolderGoods> {
    private GoodsDetailBean.DataBean list;

    public MyRecycleGoods(GoodsDetailBean.DataBean list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolderGoods onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclegoods_item, parent, false);
        MyViewHolderGoods myViewHolderGoods = new MyViewHolderGoods(inflate);
        return myViewHolderGoods;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderGoods holder, int position) {
        holder.name.setText(list.getTitle());
        holder.price.setText("￥:"+list.getPrice());
        holder.title.setText(list.getSubhead());
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class MyViewHolderGoods extends RecyclerView.ViewHolder {


        private final TextView price;
        private final TextView title;
        private final TextView name;

        public MyViewHolderGoods(View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.goods_price);
            title = itemView.findViewById(R.id.goods_title);
            name = itemView.findViewById(R.id.goods_name);

        }
    }
}
