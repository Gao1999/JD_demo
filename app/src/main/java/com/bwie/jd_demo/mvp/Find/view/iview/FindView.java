package com.bwie.jd_demo.mvp.Find.view.iview;

import com.bwie.jd_demo.base.IView;
import com.bwie.jd_demo.mvp.Find.model.bean.NewsBean;
import com.bwie.jd_demo.mvp.Find.model.bean.WelfareBean;

public interface FindView extends IView {
    void NewSuccess(NewsBean newsBean);

    void Error(String error);

    void WelfareSuccess(WelfareBean welfareBean);
}
