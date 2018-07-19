package com.bwie.jd_demo.mvp.home.view.iview;

import com.bwie.jd_demo.base.IView;
import com.bwie.jd_demo.mvp.home.model.bean.AddCartBean;
import com.bwie.jd_demo.mvp.home.model.bean.GoodsDetailBean;
import com.bwie.jd_demo.mvp.home.model.bean.SearchBean;

public interface IGoodsDetailsView extends IView {
    void SUCCESS(GoodsDetailBean goodsDetailBean);

    void ERROR(String error);

    void GETSUCCESS(AddCartBean addCartBean);

    void GETERROR(String error);

    void SeachSuccess(SearchBean searchBean);
}
