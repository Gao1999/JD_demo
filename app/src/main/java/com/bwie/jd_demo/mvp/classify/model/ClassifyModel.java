package com.bwie.jd_demo.mvp.classify.model;

import com.bwie.jd_demo.mvp.classify.model.bean.ClassifyBean;
import com.bwie.jd_demo.mvp.classify.model.bean.ClassifyLeftBean;
import com.bwie.jd_demo.mvp.classify.model.bean.ClassifyRightBean;
import com.bwie.jd_demo.utils.RetrofitManager;

import io.reactivex.Observable;

public class ClassifyModel {

    public Observable<ClassifyLeftBean> classifyNetLeft() {
        return RetrofitManager.getDefault().create(IClassifyApi.class).Classify_left();
    }

    public Observable<ClassifyRightBean> ClassifyNetFromRight(int cid) {
        return RetrofitManager.getDefault().create(IClassifyApi.class).Classify_right(cid);
    }

    public Observable<ClassifyBean> Classifyproduct(int pscid, int page, int sort) {
        return RetrofitManager.getDefault().create(IClassifyApi.class).Classify_products(pscid, page, sort);
    }
}
