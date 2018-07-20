package com.bwie.jd_demo.mvp.shopping.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.mvp.shopping.model.bean.OrdersBean;

import java.util.List;

public class MyOrdersRecycle extends RecyclerView.Adapter<MyOrdersRecycle.MyViewHolder> {
    private List<OrdersBean.DataBean> list;

    public MyOrdersRecycle(List<OrdersBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_orders_recycle, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.price.setText("￥:" + list.get(position).getPrice());
        holder.time.setText(list.get(position).getCreatetime());
        int status = list.get(position).getStatus();
        if (status == 0) {
            holder.status.setText("待支付");
        } else if (status == 1) {
            holder.status.setText("已支付");
        } else if (status == 2) {
            holder.status.setText("已取消");
        }
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
        private final TextView price;
        private final TextView time;
        private final TextView status;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.SelectOrder_recycle_title);
            price = itemView.findViewById(R.id.SelectOrder_recycle_price);
            time = itemView.findViewById(R.id.SelectOrder_recycle_time);
            status = itemView.findViewById(R.id.SelectOrder_recycle_status);

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
