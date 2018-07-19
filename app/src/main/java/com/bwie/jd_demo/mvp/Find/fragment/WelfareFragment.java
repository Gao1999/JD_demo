package com.bwie.jd_demo.mvp.Find.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.base.BaseFragment;
import com.bwie.jd_demo.constant.ConstantApi;
import com.bwie.jd_demo.mvp.Find.model.bean.NewsBean;
import com.bwie.jd_demo.mvp.Find.model.bean.WelfareBean;
import com.bwie.jd_demo.mvp.Find.presenter.FindPresenter;
import com.bwie.jd_demo.mvp.Find.view.adapter.MyRecycleViewWelfare;
import com.bwie.jd_demo.mvp.Find.view.iview.FindView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class WelfareFragment extends BaseFragment<FindPresenter> implements FindView {


    private XRecyclerView xRecyclerView;

    @Override
    protected FindPresenter providePresenter() {
        return presenter = new FindPresenter(this);
    }

    @Override
    protected void initData() {
        presenter.WelfareNet(ConstantApi.WelfareNet);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initViews(View view) {
        xRecyclerView = view.findViewById(R.id.find_welfare_xrecycle);
    }

    @Override
    protected int provideLayout() {
        return R.layout.fragment_welfare;
    }

    //定义一个方法进行接收
    public static WelfareFragment create(String title) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        WelfareFragment welfareFragment = new WelfareFragment();
        welfareFragment.setArguments(bundle);
        return welfareFragment;
    }

    @Override
    public void NewSuccess(NewsBean newsBean) {

    }

    @Override
    public void Error(String error) {
        Log.e("tag", "Error: "+error );
    }

    @Override
    public void WelfareSuccess(WelfareBean welfareBean) {
        Log.e("tag", "WelfareSuccess: "+welfareBean.getMsg() );
        List<WelfareBean.DataBean> data = welfareBean.getData();

        MyRecycleViewWelfare myRecycleViewWelfare = new MyRecycleViewWelfare(data);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(gridLayoutManager);
        Log.e("tag", "WelfareSuccess: "+data.size() );
        xRecyclerView.setAdapter(myRecycleViewWelfare);

    }
}
