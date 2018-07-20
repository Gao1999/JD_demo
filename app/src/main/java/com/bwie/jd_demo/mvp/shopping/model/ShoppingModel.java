package com.bwie.jd_demo.mvp.shopping.model;

import com.bwie.jd_demo.mvp.home.model.bean.AdBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.CreateOrderBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.DefaultAddressBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.DeleteCartBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.OrdersBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.ShoppingBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.UpDataCartBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.UpdataOrderBean;
import com.bwie.jd_demo.utils.RetrofitManager;

import io.reactivex.Observable;

public class ShoppingModel {

    public Observable<ShoppingBean> ShoppingNetData(int uid) {
        return RetrofitManager.getDefault().create(IShoppingApi.class).ShopingNetData(uid);

    }

    public Observable<DeleteCartBean> deleteCart(int uid, int pid) {
        return RetrofitManager.getDefault().create(IShoppingApi.class).deleteCart(uid, pid);
    }

    public Observable<UpDataCartBean> UpDataCartNet(int uid, int sellerid, int pid, int num, int Selected) {
        return RetrofitManager.getDefault().create(IShoppingApi.class).UpDataCart(uid, sellerid, pid, num, Selected);
    }

    public Observable<CreateOrderBean> CreateOrderNet(int uid, double price) {
        return RetrofitManager.getDefault().create(IShoppingApi.class).CreateOrderNet(uid, price);
    }

    public Observable<OrdersBean> SelectOrderNet(int uid,int status) {
        return RetrofitManager.getDefault().create(IShoppingApi.class).GetOrdersNet(uid,status);

    }

    public Observable<UpdataOrderBean> UpdateOrderNetFrom(int uid, int status, int orderId) {
        return RetrofitManager.getDefault().create(IShoppingApi.class).UpdateOrder(uid, status, orderId);
    }

    public Observable<DefaultAddressBean> DefaultAddressNet(int uid) {
        return RetrofitManager.getDefault().create(IShoppingApi.class).DefaultAddr(uid);
    }


}
