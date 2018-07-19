package com.bwie.jd_demo.mvp.classify.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.mvp.classify.model.bean.ClassifyRightBean;
import com.bwie.jd_demo.mvp.classify.view.activity.ClassifyActivity;

import java.util.List;

public class MyRecycleRightGroup extends RecyclerView.Adapter<MyRecycleRightGroup.MyViewHolderGroup> {
    private Context context;
    private List<ClassifyRightBean.DataBean> list;
    public MyRecycleRightChild myRecycleRightChild;

    public MyRecycleRightGroup(Context context, List<ClassifyRightBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolderGroup onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.classify_recycle_right_group, parent, false);
        MyViewHolderGroup myViewHolderGroup = new MyViewHolderGroup(inflate);
        return myViewHolderGroup;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderGroup holder, final int position) {
        holder.textView.setText(list.get(position).getName());
        final List<ClassifyRightBean.DataBean.ListBean> list = this.list.get(position).getList();

        myRecycleRightChild = new MyRecycleRightChild(list, context);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.recyclerView.setLayoutManager(gridLayoutManager);
        holder.recyclerView.setAdapter(myRecycleRightChild);

        myRecycleRightChild.setOnClictItemListenerChild(new MyRecycleRightChild.setOnClictItemListenerChild() {
            @Override
            public void onClickListenerChild(int position) {
                int pscid1 = list.get(position).getPscid();
                Log.e("tag", "onClickListenerChild: " + pscid1);
                Intent intent = new Intent(context, ClassifyActivity.class);
                intent.putExtra("pscid", pscid1);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolderGroup extends RecyclerView.ViewHolder {

        private final TextView textView;
        private final RecyclerView recyclerView;

        public MyViewHolderGroup(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.classify_recycle_right_group_title);
            recyclerView = itemView.findViewById(R.id.classify_recycle_right_child);
        }
    }

    private setOnClictItemListener onClictItemListener;

    public void setOnClictItemListener(setOnClictItemListener onClictItemListener) {
        this.onClictItemListener = onClictItemListener;
    }

    public interface setOnClictItemListener {
        void onClickListener(int position, int pscid);
    }
}
