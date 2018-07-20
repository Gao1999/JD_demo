package com.bwie.jd_demo.mvp.shopping.model;

import com.bwie.jd_demo.constant.ConstantApi;
import com.bwie.jd_demo.mvp.home.model.bean.AdBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.AddadderssBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.CreateOrderBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.DefaultAddressBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.DeleteCartBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.OrdersBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.SelectAddressBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.SetAddressBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.ShoppingBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.UpDataCartBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.UpdataAddrBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.UpdataOrderBean;

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

    @GET(ConstantApi.CreateOrder)
    Observable<CreateOrderBean> CreateOrderNet(@Query("uid") int uid, @Query("price") double price);

    @GET(ConstantApi.GetOrders)
    Observable<OrdersBean> GetOrdersNet(@Query("uid") int uid, @Query("status") int status);

    @GET(ConstantApi.UpdateOrder)
    Observable<UpdataOrderBean> UpdateOrder(@Query("uid") int uid, @Query("status") int status, @Query("orderId") int orderId);

    @GET(ConstantApi.DefaultAddr)
    Observable<DefaultAddressBean> DefaultAddr(@Query("uid") int uid);

    @GET(ConstantApi.GetAddrs)
    Observable<SelectAddressBean> SelectAddress(@Query("uid") int uid);

    @GET(ConstantApi.AddAddr)
    Observable<AddadderssBean> Addadderss(@Query("uid") int uid, @Query("addr") String addr, @Query("mobile") String mobile, @Query("name") String name);

    @GET(ConstantApi.UpdateAddr)
    Observable<UpdataAddrBean> UpdateAddr(@Query("uid") int uid, @Query("addrid") int addrid, @Query("addr") String addr, @Query("mobile") String mobile, @Query("name") String name);

    @GET(ConstantApi.SetAddr)
    Observable<SetAddressBean> SetAddress(@Query("uid") int uid, @Query("addrid") int addrid, @Query("status") int status);


}
