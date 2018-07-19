package com.bwie.jd_demo.mvp.home.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.mvp.home.model.bean.AdBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyRecycleViewMS extends RecyclerView.Adapter<MyRecycleViewMS.MyViewHolder> {
    private List<AdBean.MiaoshaBean.ListBeanX> list;

    public MyRecycleViewMS(List<AdBean.MiaoshaBean.ListBeanX> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycle_ms, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        holder.simpleDraweeView.setImageURI(split[0]);

        holder.simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickItemMS != null) {
                    onClickItemMS.onClickItemMS(position);
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

        public MyViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.home_miaosha_pic);
        }
    }

    private setOnClickItemMS onClickItemMS;

    public void setOnClickItemMS(setOnClickItemMS onClickItemMS) {
        this.onClickItemMS = onClickItemMS;
    }

    public interface setOnClickItemMS {
        void onClickItemMS(int position);
    }
}
