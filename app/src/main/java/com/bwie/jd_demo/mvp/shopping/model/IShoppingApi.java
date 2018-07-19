package com.bwie.jd_demo.mvp.shopping.model;

import com.bwie.jd_demo.constant.ConstantApi;
import com.bwie.jd_demo.mvp.home.model.bean.AdBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.DeleteCartBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.ShoppingBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.UpDataCartBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IShoppingApi {
    @GET(ConstantApi.SHOPPING_URL)
    Observable<ShoppingBean> ShopingNetData(@Query("uid") int uid);

    @GET(ConstantApi.deleteCart)
    Observable<DeleteCartBean> deleteCart(@Query("uid") int uid, @Query("pid") int pid);

    @GET(ConstantApi.UpdateCarts)
    Observable<UpDataCartBean> UpDataCart(@Query("uid") int uid, @Query("sellerid") int sellerid, @Query("pid") int pid, @Query("num") int num, @Query("selected") int selected);
}
