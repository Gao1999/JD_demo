package com.bwie.jd_demo.mvp.my.view.iview;

import com.bwie.jd_demo.base.IView;
import com.bwie.jd_demo.mvp.my.model.bean.LoginBean;

public interface LoginView extends IView {
    void getSuccess(LoginBean loginBean);

    void getError(String error);

}
