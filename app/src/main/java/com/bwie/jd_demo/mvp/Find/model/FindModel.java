package com.bwie.jd_demo.mvp.Find.model;

import com.bwie.jd_demo.mvp.Find.model.bean.NewsBean;
import com.bwie.jd_demo.mvp.Find.model.bean.WelfareBean;
import com.bwie.jd_demo.utils.RetrofitManager;

import io.reactivex.Observable;

public class FindModel {
    public Observable<NewsBean> NewsData(String url) {
        return RetrofitManager.getDefault().create(IFindApi.class).newsNetFrom(url);
    }

    public Observable<WelfareBean> WelfareNet(String url) {
        return RetrofitManager.getDefault().create(IFindApi.class).WelfareNetFrom(url);

    }
}
