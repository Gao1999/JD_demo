package com.bwie.jd_demo.mvp.home.model;

import com.bwie.jd_demo.constant.ConstantApi;
import com.bwie.jd_demo.mvp.home.model.bean.AdBean;
import com.bwie.jd_demo.mvp.home.model.bean.AddCartBean;
import com.bwie.jd_demo.mvp.home.model.bean.ClassBean;
import com.bwie.jd_demo.mvp.home.model.bean.GoodsDetailBean;
import com.bwie.jd_demo.mvp.home.model.bean.SearchBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.CreateOrderBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IHomeApi {
    @GET(ConstantApi.ADNET)
    Observable<AdBean> adFromNet();

    @GET(ConstantApi.ClassNet)
    Observable<ClassBean> ClassFromNet();

    @GET(ConstantApi.GOODSDETAILS_PID_URL)
    Observable<GoodsDetailBean> GoodsFromNet(@Query("pid") int pid);

    @GET(ConstantApi.ADDCart)
    Observable<AddCartBean> AddCartFromNet(@Query("uid") int uid, @Query("pid") int pid);

    @GET(ConstantApi.SearchProducts)
    Observable<SearchBean> SearchFromNet(@Query("keywords") String keywords, @Query("page") int page, @Query("sort") int sort);

    @GET(ConstantApi.CreateOrder)
    Observable<CreateOrderBean> CreateOrderNet(@Query("uid") int uid, @Query("price") double price);
}
