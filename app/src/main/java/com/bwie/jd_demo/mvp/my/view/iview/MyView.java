package com.bwie.jd_demo.mvp.my.view.iview;

import com.bwie.jd_demo.base.IView;
import com.bwie.jd_demo.mvp.home.model.bean.AdBean;
import com.bwie.jd_demo.mvp.my.model.bean.UploadBean;
import com.bwie.jd_demo.mvp.my.model.bean.UserInfoBean;

public interface MyView extends IView {
    void Success(AdBean adBean);

    void Error(String error);

    void UpLoadSuccess(UploadBean uploadBean);

    void UserInfoSuccess(UserInfoBean userInfoBean);
}
