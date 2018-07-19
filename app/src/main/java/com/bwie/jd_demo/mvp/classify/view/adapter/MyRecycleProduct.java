package com.bwie.jd_demo.mvp.classify.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.mvp.classify.model.bean.ClassifyBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyRecycleProduct extends RecyclerView.Adapter<MyRecycleProduct.MyViewHolder> {
    private List<ClassifyBean.DataBean> list;

    public MyRecycleProduct(List<ClassifyBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.classify_recycleproduct, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        holder.simpleDraweeView.setImageURI(split[0]);
        holder.title.setText(list.get(position).getSubhead());
        holder.price.setText("￥:" + list.get(position).getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickItemListener != null) {
                    onClickItemListener.OnClickItemListener(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView simpleDraweeView;
        private final TextView title;
        private final TextView price;

        public MyViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.classify_product_pic);
            title = itemView.findViewById(R.id.classify_product_title);
            price = itemView.findViewById(R.id.classify_product_price);

        }
    }

    public setOnClickItemListener onClickItemListener;

    public void setOnClickItemListener(setOnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    public interface setOnClickItemListener {
        void OnClickItemListener(int position);
    }
}
