package com.bwie.jd_demo.mvp.classify.model;

import com.bwie.jd_demo.constant.ConstantApi;
import com.bwie.jd_demo.mvp.classify.model.bean.ClassifyBean;
import com.bwie.jd_demo.mvp.classify.model.bean.ClassifyLeftBean;
import com.bwie.jd_demo.mvp.classify.model.bean.ClassifyRightBean;
import com.bwie.jd_demo.mvp.classify.view.ClassifyFragmentRight;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IClassifyApi {
    @GET(ConstantApi.ClassNet)
    Observable<ClassifyLeftBean> Classify_left();

    @GET(ConstantApi.CLASSIFY_RIGHT)
    Observable<ClassifyRightBean> Classify_right(@Query("cid") int cid);

    @GET(ConstantApi.Classify_Products)
    Observable<ClassifyBean> Classify_products(@Query("pscid") int pscid, @Query("page") int page, @Query("sort") int sort);
}
