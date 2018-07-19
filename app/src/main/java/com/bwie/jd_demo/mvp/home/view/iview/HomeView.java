package com.bwie.jd_demo.mvp.home.view.iview;

import com.bwie.jd_demo.base.IView;
import com.bwie.jd_demo.mvp.home.model.bean.AdBean;
import com.bwie.jd_demo.mvp.home.model.bean.ClassBean;

public interface HomeView extends IView {
    void getSuccess(AdBean adBean);

    void getError(String error);

    void Success(ClassBean classBean);

    void Error(String error);
}
