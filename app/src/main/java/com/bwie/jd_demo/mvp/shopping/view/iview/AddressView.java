package com.bwie.jd_demo.mvp.shopping.view.iview;

import com.bwie.jd_demo.base.IView;
import com.bwie.jd_demo.mvp.shopping.model.bean.AddadderssBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.SelectAddressBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.SetAddressBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.UpdataAddrBean;

public interface AddressView extends IView {
    void AddressSuccess(SelectAddressBean selectAddressBean);

    void Error(String error);

    void AddaddressSuccess(AddadderssBean addadderssBean);

    void UpdataAddrSuccess(UpdataAddrBean updataAddrBean);

    void SetAddressSuccess(SetAddressBean setAddressBean);
}
