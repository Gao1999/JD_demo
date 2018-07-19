package com.bwie.jd_demo.mvp.classify.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.base.BaseFragment;
import com.bwie.jd_demo.mvp.classify.model.bean.ClassifyBean;
import com.bwie.jd_demo.mvp.classify.model.bean.ClassifyLeftBean;
import com.bwie.jd_demo.mvp.classify.model.bean.ClassifyRightBean;
import com.bwie.jd_demo.mvp.classify.presenter.ClassifyPresenter;
import com.bwie.jd_demo.mvp.classify.view.adapter.MyRecycleLeftView;
import com.bwie.jd_demo.mvp.classify.view.iview.ClassifyView;

import java.util.List;

public class ClassifyFragment extends BaseFragment<ClassifyPresenter> implements ClassifyView {

    private RecyclerView recyclerView;

    @Override
    protected ClassifyPresenter providePresenter() {
        return presenter = new ClassifyPresenter(this);
    }

    @Override
    protected void initData() {
        presenter.classifyNetLeft();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initViews(View view) {
        recyclerView = view.findViewById(R.id.classify_left_recycle);
    }

    @Override
    protected int provideLayout() {
        return R.layout.fragment_classify;
    }

    @Override
    public void getSuccess(ClassifyLeftBean classifyLeftBean) {

        Log.e("log", "ClassifyFragment---: "+ classifyLeftBean.getData().size());
        final List<ClassifyLeftBean.DataBean> data = classifyLeftBean.getData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        final MyRecycleLeftView myRecycleLeftView = new MyRecycleLeftView(data);
        recyclerView.setAdapter(myRecycleLeftView);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        ClassifyLeftBean.DataBean bean = data.get(0);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        ClassifyFragmentRight fragmentRight = new ClassifyFragmentRight();
        transaction.replace(R.id.classify_frame, fragmentRight);
        Bundle bundle = new Bundle();
        int cid = bean.getCid();
        bundle.putInt("cid", cid);
        fragmentRight.setArguments(bundle);
        transaction.commit();

        myRecycleLeftView.setOnClickItemListener(new MyRecycleLeftView.setOnClickItemListener() {
            @Override
            public void onClickItemListener(int position) {
                ClassifyLeftBean.DataBean bean = data.get(position);
                myRecycleLeftView.setThisPosition(position);
                myRecycleLeftView.notifyDataSetChanged();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                ClassifyFragmentRight fragmentRight = new ClassifyFragmentRight();
                transaction.replace(R.id.classify_frame, fragmentRight);
                Bundle bundle = new Bundle();
                int cid = bean.getCid();
                bundle.putInt("cid", cid);
                fragmentRight.setArguments(bundle);
                transaction.commit();
            }
        });
    }

    @Override
    public void getError(String error) {
        Log.d("tag", "getError: " + error);
    }

    @Override
    public void Success(ClassifyRightBean classifyRightBean) {

    }

    @Override
    public void Error(String error) {

    }

    @Override
    public void SUCCESS(ClassifyBean classifyBean) {

    }
}
