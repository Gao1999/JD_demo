package com.bwie.jd_demo.mvp.my.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.jd_demo.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyRecycle_dd extends RecyclerView.Adapter<MyRecycle_dd.MyViewHolder> {
    private List<Integer> list;
    private List<String> listTitle_dd;

    public MyRecycle_dd(List<Integer> list, List<String> listTitle_dd) {
        this.list = list;
        this.listTitle_dd = listTitle_dd;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_recycleview_dd, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.imageView.setImageResource(list.get(position));
        holder.title.setText(listTitle_dd.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.OnItemClickListener(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.my_recycleview_dd_pic);
            title = itemView.findViewById(R.id.my_recycleview_dd_title);
        }
    }

    public setOnItemClickListener onItemClickListener;

    public void setOnItemClickListener(setOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface setOnItemClickListener {
        void OnItemClickListener(int position);
    }
}
