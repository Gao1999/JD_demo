package com.bwie.jd_demo.mvp.my.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.jd_demo.R;

import java.util.List;

public class MyRecycle_gd extends RecyclerView.Adapter<MyRecycle_gd.MyViewHolder> {
    private List<Integer> listPic_gd;
    private List<String> listTitle_gd;

    public MyRecycle_gd(List<Integer> listPic_gd, List<String> listTitle_gd) {
        this.listPic_gd = listPic_gd;
        this.listTitle_gd = listTitle_gd;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_recycleview_gd, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imageView.setImageResource(listPic_gd.get(position));
        holder.title.setText(listTitle_gd.get(position));
    }

    @Override
    public int getItemCount() {
        return listPic_gd == null ? 0 : listPic_gd.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.my_recycleview_gd_pic);
            title = itemView.findViewById(R.id.my_recycleview_gd_title);
        }
    }
}
