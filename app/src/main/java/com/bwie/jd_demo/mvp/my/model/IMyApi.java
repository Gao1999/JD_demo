package com.bwie.jd_demo.mvp.my.model;

import com.bwie.jd_demo.constant.ConstantApi;
import com.bwie.jd_demo.mvp.home.model.bean.AdBean;
import com.bwie.jd_demo.mvp.my.model.bean.LoginBean;
import com.bwie.jd_demo.mvp.my.model.bean.RegBean;
import com.bwie.jd_demo.mvp.my.model.bean.UploadBean;
import com.bwie.jd_demo.mvp.my.model.bean.UserInfoBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface IMyApi {
    @GET(ConstantApi.MY_LOGINURL)
    Observable<LoginBean> loginDataNet(@Query("mobile") String mobile, @Query("password") String pasword);

    @GET(ConstantApi.MY_REG)
    Observable<RegBean> RegDataNet(@Query("mobile") String mobile, @Query("password") String pasword);

    @GET(ConstantApi.ADNET)
    Observable<AdBean> My_Tuijian();

    @Multipart
    @POST(ConstantApi.UploadData)
    Observable<UploadBean> UploadData(@Query("uid") int uid, @Part MultipartBody.Part part);

    @GET(ConstantApi.UserInfo)
    Observable<UserInfoBean> UserInfoData(@Query("uid") int uid);
}
