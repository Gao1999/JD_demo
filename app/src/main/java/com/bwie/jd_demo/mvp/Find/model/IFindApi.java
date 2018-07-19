package com.bwie.jd_demo.mvp.Find.model;

import com.bwie.jd_demo.mvp.Find.model.bean.NewsBean;
import com.bwie.jd_demo.mvp.Find.model.bean.WelfareBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface IFindApi {
    @GET()
    Observable<NewsBean> newsNetFrom(@Url String url);

    @GET()
    Observable<WelfareBean> WelfareNetFrom(@Url String url);
}
