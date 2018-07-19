package com.bwie.jd_demo.mvp.classify.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.base.BaseFragment;
import com.bwie.jd_demo.mvp.classify.model.bean.ClassifyBean;
import com.bwie.jd_demo.mvp.classify.model.bean.ClassifyLeftBean;
import com.bwie.jd_demo.mvp.classify.model.bean.ClassifyRightBean;
import com.bwie.jd_demo.mvp.classify.presenter.ClassifyPresenter;
import com.bwie.jd_demo.mvp.classify.view.adapter.MyRecycleRightChild;
import com.bwie.jd_demo.mvp.classify.view.adapter.MyRecycleRightGroup;
import com.bwie.jd_demo.mvp.classify.view.iview.ClassifyView;

import java.util.List;

public class ClassifyFragmentRight extends BaseFragment<ClassifyPresenter> implements ClassifyView {
    private int cid;
    private RecyclerView recyclerView;

    @Override
    protected ClassifyPresenter providePresenter() {
        return presenter = new ClassifyPresenter(this);
    }

    @Override
    protected void initData() {
        presenter.ClassifyNetFromRight(cid);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initViews(View view) {
        Bundle bundle = getArguments();
        cid = bundle.getInt("cid");

        recyclerView = view.findViewById(R.id.classify_recycle_right_group);
    }

    @Override
    protected int provideLayout() {
        return R.layout.fragment_classify_right;
    }

    @Override
    public void getSuccess(ClassifyLeftBean classifyLeftBean) {

    }

    @Override
    public void getError(String error) {

    }

    @Override
    public void Success(ClassifyRightBean classifyRightBean) {
        Log.e("log", "ClassifyFragmentRight---: "+ classifyRightBean.getData().size());

        final List<ClassifyRightBean.DataBean> data = classifyRightBean.getData();
        MyRecycleRightGroup myRecycleRightGroup = new MyRecycleRightGroup(getContext(), data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myRecycleRightGroup);
    }

    @Override
    public void Error(String error) {

    }

    @Override
    public void SUCCESS(ClassifyBean classifyBean) {

    }
}
