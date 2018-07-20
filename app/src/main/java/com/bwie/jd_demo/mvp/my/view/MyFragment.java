package com.bwie.jd_demo.mvp.my.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.base.BaseFragment;
import com.bwie.jd_demo.mvp.home.model.bean.AdBean;
import com.bwie.jd_demo.mvp.home.view.GoodsdetailsActivity;
import com.bwie.jd_demo.mvp.home.view.adapter.MyRecycleViewTJ;
import com.bwie.jd_demo.mvp.my.model.bean.UploadBean;
import com.bwie.jd_demo.mvp.my.model.bean.UserInfoBean;
import com.bwie.jd_demo.mvp.my.presenter.MyPresenter;
import com.bwie.jd_demo.mvp.my.view.activity.LoginActivity;
import com.bwie.jd_demo.mvp.my.view.activity.ModifyActivity;
import com.bwie.jd_demo.mvp.my.view.adapter.MyRecycle_dd;
import com.bwie.jd_demo.mvp.my.view.adapter.MyRecycle_gd;
import com.bwie.jd_demo.mvp.my.view.iview.MyView;
import com.bwie.jd_demo.mvp.shopping.view.SelectOrdersActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.scwang.smartrefresh.header.FlyRefreshHeader;
import com.scwang.smartrefresh.header.TaurusHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class MyFragment extends BaseFragment<MyPresenter> implements MyView {

    private RecyclerView recyclerView_dd;
    private List<Integer> listPic_dd;
    private List<String> listTitle_dd;
    private List<Integer> listPic_gd;
    private List<String> listTitle_gd;
    private RecyclerView recyclerView_gd;
    private SimpleDraweeView simpleDraweeView;
    private TextView user;
    private RecyclerView recyclerView;
    private SmartRefreshLayout smartRefreshLayout;
    private SharedPreferences sharedPreferences;


    @Override
    protected MyPresenter providePresenter() {
        return presenter = new MyPresenter(this);

    }

    @Override
    protected void initData() {
        int uid = sharedPreferences.getInt("uid", 0);
        presenter.UserInfoData(uid);
        presenter.TuijainNet();

    }

    @Override
    protected void initListener() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);//传入false表示刷新失败
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000);//传入false表示加载失败
            }
        });

        //设置 Header 为 贝塞尔雷达 样式
        smartRefreshLayout.setRefreshHeader(new TaurusHeader(getActivity()));
        //设置 Footer 为 球脉冲 样式
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));
    }

    @Override
    protected void initViews(View view) {

        recyclerView_dd = view.findViewById(R.id.my_recycle_dd);
        recyclerView_gd = view.findViewById(R.id.my_recycle_gd);
        simpleDraweeView = view.findViewById(R.id.my_pic);
        recyclerView = view.findViewById(R.id.my_recycle_Tuijian);
        user = view.findViewById(R.id.my_mobile);
        smartRefreshLayout = view.findViewById(R.id._my_refreshLayout);

        getDataFromSharedPreferences();

        sharedPreferences = getActivity().getSharedPreferences("jd", Context.MODE_PRIVATE);
        simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean login = sharedPreferences.getBoolean("login", false);
                if (login) {
                    Intent intent = new Intent(getActivity(), ModifyActivity.class);

                    startActivityForResult(intent, 2);

                } else {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, 1);
                }

            }
        });

        listPic_dd = new ArrayList<>();
        listPic_dd.add(R.drawable.dianpu);
        listPic_dd.add(R.drawable.dsh);
        listPic_dd.add(R.drawable.dpj);
        listPic_dd.add(R.drawable.th_sh);
        listPic_dd.add(R.drawable.mydd);

        listTitle_dd = new ArrayList<>();
        listTitle_dd.add("待付款");
        listTitle_dd.add("待收货");
        listTitle_dd.add("待评价");
        listTitle_dd.add("退货/售后");
        listTitle_dd.add("我的订单");

        listPic_gd = new ArrayList<>();
        listPic_gd.add(R.drawable.dp);
        listPic_gd.add(R.drawable.kh);
        listPic_gd.add(R.drawable.hd);
        listPic_gd.add(R.drawable.spgz);
        listPic_gd.add(R.drawable.gz);

        listTitle_gd = new ArrayList<>();
        listTitle_gd.add("店铺");
        listTitle_gd.add("客服");
        listTitle_gd.add("活动");
        listTitle_gd.add("超市");
        listTitle_gd.add("收藏");

        MyRecycle_dd myRecycle_dd = new MyRecycle_dd(listPic_dd, listTitle_dd);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_dd.setLayoutManager(linearLayoutManager);
        recyclerView_dd.setAdapter(myRecycle_dd);


        MyRecycle_gd myRecycle_gd = new MyRecycle_gd(listPic_gd, listTitle_gd);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_gd.setLayoutManager(linearLayoutManager1);
        recyclerView_gd.setAdapter(myRecycle_gd);
        myRecycle_dd.setOnItemClickListener(new MyRecycle_dd.setOnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                if (position == 4) {
                    Intent intent = new Intent(getActivity(), SelectOrdersActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    public void getDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("jd", Context.MODE_PRIVATE);
        boolean login = sharedPreferences.getBoolean("login", false);
        String mobile = sharedPreferences.getString("user", null);
        String icon = sharedPreferences.getString("icon", null);

        Log.e("tag", "initViews: " + login + mobile + icon);
        if (login) {
            if (icon != null) {
                Uri parse = Uri.parse(icon);
                simpleDraweeView.setImageURI(parse);
            }

            user.setText(mobile);
        }
    }

    @Override
    protected int provideLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1 && requestCode == 1) {
            getDataFromSharedPreferences();
            String icon = data.getStringExtra("pic");
            String mobile = data.getStringExtra("mobile");
            if (icon != null) {
                Uri parse = Uri.parse(icon);
                simpleDraweeView.setImageURI(parse);
            }

            user.setText(mobile);
        }
        if (resultCode == 2 && requestCode == 2) {
            getDataFromSharedPreferences();
            String icon = data.getStringExtra("pic");
            String mobile = data.getStringExtra("mobile");
            if (icon != null) {
                Uri parse = Uri.parse(icon);
                simpleDraweeView.setImageURI(parse);
            }

            user.setText(mobile);
        }
    }

    @Override
    public void Success(final AdBean adBean) {
        List<AdBean.TuijianBean.ListBean> list = adBean.getTuijian().getList();
        MyRecycleViewTJ myRecycleViewTJ = new MyRecycleViewTJ(list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(myRecycleViewTJ);

        myRecycleViewTJ.setSetOnClickItemTJ(new MyRecycleViewTJ.setOnClickItemTJ() {
            @Override
            public void onClickItemTJ(int position) {
                int pid = adBean.getTuijian().getList().get(position).getPid();
                Intent intent = new Intent(getActivity(), GoodsdetailsActivity.class);
                intent.putExtra("pid", pid);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    public void Error(String error) {

    }

    @Override
    public void UpLoadSuccess(UploadBean uploadBean) {

    }

    @Override
    public void UserInfoSuccess(UserInfoBean userInfoBean) {
        String mobile = userInfoBean.getData().getMobile();
        String icon = userInfoBean.getData().getIcon();
        simpleDraweeView.setImageURI(icon);
        user.setText(mobile);
    }


}
