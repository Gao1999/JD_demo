package com.bwie.jd_demo.mvp.my.view.iview;

import com.bwie.jd_demo.base.IView;
import com.bwie.jd_demo.mvp.my.model.bean.RegBean;

public interface RegView extends IView {
    void getSuccess(RegBean regBean);

    void getError(String error);
}
