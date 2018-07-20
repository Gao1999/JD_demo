package com.bwie.jd_demo.mvp.shopping.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.mvp.shopping.model.bean.SelectAddressBean;
import com.bwie.jd_demo.mvp.shopping.view.AddressActivity;

import java.util.List;

public class MyAddressRecycle extends RecyclerView.Adapter<MyAddressRecycle.MyViewHolder> {
    private List<SelectAddressBean.DataBean> list;
    private Context context;

    public MyAddressRecycle(List<SelectAddressBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_recycle_select, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.name.setText("姓名:" + list.get(position).getName());
        holder.phone.setText("手机号:" + list.get(position).getMobile());
        holder.address.setText("收货地址:" + list.get(position).getAddr());
        int status = list.get(position).getStatus();
        if (status == 0) {
            holder.checkBox.setChecked(false);
        } else {
            holder.checkBox.setChecked(true);
            holder.defaultAddress.setText("默认地址");
        }

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickItemLister != null) {
                    onClickItemLister.OnClickItemLister(position);
                }
            }
        });


        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickItemLister != null) {
                    onClickItemLister.OnClickItemCheckboxListener(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView phone;
        private final TextView address;
        private final CheckBox checkBox;
        private final LinearLayout linearLayout;
        private final TextView defaultAddress;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.address_recycle_name);
            phone = itemView.findViewById(R.id.address_recycle_phone);
            address = itemView.findViewById(R.id.address_recycle_address);
            checkBox = itemView.findViewById(R.id.address_recycle_checkbox);
            linearLayout = itemView.findViewById(R.id.address_recycle_linear);
            defaultAddress = itemView.findViewById(R.id.address_recycle_default);
        }
    }

    private setOnClickItemLister onClickItemLister;

    public void setOnClickItemLister(setOnClickItemLister onClickItemLister) {
        this.onClickItemLister = onClickItemLister;
    }

    public interface setOnClickItemLister {
        void OnClickItemLister(int position);

        void OnClickItemCheckboxListener(int position);
    }
}
