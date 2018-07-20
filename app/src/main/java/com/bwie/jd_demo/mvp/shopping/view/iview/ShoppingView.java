package com.bwie.jd_demo.mvp.shopping.view.iview;

import com.bwie.jd_demo.base.IView;
import com.bwie.jd_demo.mvp.home.model.bean.AdBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.CreateOrderBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.DefaultAddressBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.DeleteCartBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.OrdersBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.ShoppingBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.UpDataCartBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.UpdataOrderBean;

public interface ShoppingView extends IView {
    void Success(ShoppingBean shoppingBean);

    void Error(String error);

    void SUCCESS(DeleteCartBean deleteCartBean);

    void UpDataCartSuccess(UpDataCartBean upDataCartBean);

    void CreateOrdeSuccess(CreateOrderBean createOrderBean);

    void SelectOrderNetSuccess(OrdersBean ordersBean);

    void UpdateOrderNetSuccess(UpdataOrderBean updataOrderBean);

    void DefaultAddrSuccess(DefaultAddressBean defaultAddressBean);

}
