package com.bwie.jd_demo.mvp.my.model;

import com.bwie.jd_demo.mvp.home.model.bean.AdBean;
import com.bwie.jd_demo.mvp.my.model.bean.UploadBean;
import com.bwie.jd_demo.mvp.my.model.bean.UserInfoBean;
import com.bwie.jd_demo.utils.RetrofitManager;

import io.reactivex.Observable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;

public class MyModel {
    public Observable<AdBean> TuijainNet() {
        return RetrofitManager.getDefault().create(IMyApi.class).My_Tuijian();

    }

    public Observable<UploadBean> UpLoadDataNet(int uid, MultipartBody.Part part) {
        return RetrofitManager.getDefault().create(IMyApi.class).UploadData(uid, part);
    }

    public Observable<UserInfoBean> UserInfoData(int uid) {
        return RetrofitManager.getDefault().create(IMyApi.class).UserInfoData(uid);
    }
}
