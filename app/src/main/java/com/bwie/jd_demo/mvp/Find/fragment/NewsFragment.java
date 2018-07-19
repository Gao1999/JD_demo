package com.bwie.jd_demo.mvp.Find.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.base.BaseFragment;
import com.bwie.jd_demo.constant.ConstantApi;
import com.bwie.jd_demo.mvp.Find.model.bean.NewsBean;
import com.bwie.jd_demo.mvp.Find.model.bean.WelfareBean;
import com.bwie.jd_demo.mvp.Find.presenter.FindPresenter;
import com.bwie.jd_demo.mvp.Find.view.adapter.MyXrecycleViewNews;
import com.bwie.jd_demo.mvp.Find.view.iview.FindView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class NewsFragment extends BaseFragment<FindPresenter> implements FindView {


    private XRecyclerView xRecyclerView;

    @Override
    protected FindPresenter providePresenter() {
        return presenter=new FindPresenter(this);
    }

    @Override
    protected void initData() {
        presenter.NewsData(ConstantApi.newsNet);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initViews(View view) {
        xRecyclerView = view.findViewById(R.id.news_xrecycle);
    }

    @Override
    protected int provideLayout() {
        return R.layout.fragment_news;
    }

    //定义一个方法进行接收
    public static NewsFragment create(String title){
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        NewsFragment newsFragment = new NewsFragment();
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    @Override
    public void NewSuccess(NewsBean newsBean) {
        Log.e("tag", "NewSuccess: "+newsBean.getReason());
        List<NewsBean.ResultBean.DataBean> data = newsBean.getResult().getData();
        MyXrecycleViewNews myXrecycleViewNews = new MyXrecycleViewNews(data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        xRecyclerView.setLayoutManager(linearLayoutManager);
        xRecyclerView.setAdapter(myXrecycleViewNews);
    }

    @Override
    public void Error(String error) {
        Log.e("tag", "Error: "+error );
    }

    @Override
    public void WelfareSuccess(WelfareBean welfareBean) {

    }
}
