package com.bwie.jd_demo.mvp.home.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.mvp.home.model.bean.AdBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyRecycleViewTJ extends RecyclerView.Adapter<MyRecycleViewTJ.MyViewHolder> {
    private List<AdBean.TuijianBean.ListBean> list;

    public MyRecycleViewTJ(List<AdBean.TuijianBean.ListBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycle_tj, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        holder.simpleDraweeView.setImageURI(split[0]);

        holder.title.setText(list.get(position).getTitle());

        holder.price.setText("￥:" + list.get(position).getPrice());

        holder.simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (setOnClickItemTJ != null) {
                    setOnClickItemTJ.onClickItemTJ(position);
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
            simpleDraweeView = itemView.findViewById(R.id.home_tuijain_pic);
            title = itemView.findViewById(R.id.home_tuijain_title);
            price = itemView.findViewById(R.id.home_tuijain_price);
        }
    }

    private setOnClickItemTJ setOnClickItemTJ;

    public void setSetOnClickItemTJ(MyRecycleViewTJ.setOnClickItemTJ setOnClickItemTJ) {
        this.setOnClickItemTJ = setOnClickItemTJ;
    }

    public interface setOnClickItemTJ {
        void onClickItemTJ(int position);
    }
}
