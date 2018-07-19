package com.bwie.jd_demo.mvp.Find.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.base.BaseFragment;
import com.bwie.jd_demo.mvp.Find.model.bean.NewsBean;
import com.bwie.jd_demo.mvp.Find.model.bean.WelfareBean;
import com.bwie.jd_demo.mvp.Find.presenter.FindPresenter;
import com.bwie.jd_demo.mvp.Find.view.iview.FindView;

public class FoodFragment extends BaseFragment<FindPresenter> implements FindView {


    @Override
    protected FindPresenter providePresenter() {
        return presenter=new FindPresenter(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected int provideLayout() {
        return R.layout.fragment_food;
    }

    //定义一个方法进行接收
    public static FoodFragment create(String title) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        FoodFragment foodFragment = new FoodFragment();
        foodFragment.setArguments(bundle);
        return foodFragment;
    }

    @Override
    public void NewSuccess(NewsBean newsBean) {

    }

    @Override
    public void Error(String error) {

    }

    @Override
    public void WelfareSuccess(WelfareBean welfareBean) {

    }
}
