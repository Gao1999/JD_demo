package com.bwie.jd_demo.mvp.shopping.model;


import com.bwie.jd_demo.mvp.shopping.model.bean.AddadderssBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.SelectAddressBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.SetAddressBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.UpdataAddrBean;
import com.bwie.jd_demo.utils.RetrofitManager;

import io.reactivex.Observable;

public class AddressModel {

    public Observable<SelectAddressBean> SelectAddressNet(int uid) {
        return RetrofitManager.getDefault().create(IShoppingApi.class).SelectAddress(uid);
    }

    public Observable<AddadderssBean> addAddrNet(int uid, String dizi, String mobile, String name) {
        return RetrofitManager.getDefault().create(IShoppingApi.class).Addadderss(uid, dizi, mobile, name);
    }

    public Observable<UpdataAddrBean> UpdataAddrNet(int uid, int addrid, String dizi, String model, String name) {
        return RetrofitManager.getDefault().create(IShoppingApi.class).UpdateAddr(uid, addrid, dizi, model, name);
    }

    public Observable<SetAddressBean> SetAddressNet(int uid, int addrid, int status) {
        return RetrofitManager.getDefault().create(IShoppingApi.class).SetAddress(uid, addrid, status);
    }
}
