package com.bwie.jd_demo.mvp.classify.view.iview;

import com.bwie.jd_demo.base.IView;
import com.bwie.jd_demo.mvp.classify.model.bean.ClassifyBean;
import com.bwie.jd_demo.mvp.classify.model.bean.ClassifyLeftBean;
import com.bwie.jd_demo.mvp.classify.model.bean.ClassifyRightBean;

public interface ClassifyView extends IView {
    void getSuccess(ClassifyLeftBean classifyLeftBean);

    void getError(String error);

    void Success(ClassifyRightBean classifyRightBean);

    void Error(String error);

    void SUCCESS(ClassifyBean classifyBean);
}
