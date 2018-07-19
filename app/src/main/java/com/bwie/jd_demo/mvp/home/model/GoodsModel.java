package com.bwie.jd_demo.mvp.home.model;

import com.bwie.jd_demo.mvp.home.model.bean.AddCartBean;
import com.bwie.jd_demo.mvp.home.model.bean.GoodsDetailBean;
import com.bwie.jd_demo.mvp.home.model.bean.SearchBean;
import com.bwie.jd_demo.utils.RetrofitManager;

import io.reactivex.Observable;

public class GoodsModel {
    public Observable<GoodsDetailBean> GoodsXbannerNet(int pid) {
        return RetrofitManager.getDefault().create(IHomeApi.class).GoodsFromNet(pid);
    }

    public Observable<AddCartBean> AddCartNet(int uid, int pid) {
        return RetrofitManager.getDefault().create(IHomeApi.class).AddCartFromNet(uid, pid);
    }

    public Observable<SearchBean> SearchNet(String keywords, int page, int sort) {
        return RetrofitManager.getDefault().create(IHomeApi.class).SearchFromNet(keywords, page, sort);

    }

}
