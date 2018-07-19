package com.bwie.jd_demo.mvp.my.view.activity;

import android.os.Bundle;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.base.BaseActivity;
import com.bwie.jd_demo.mvp.my.model.bean.RegBean;
import com.bwie.jd_demo.mvp.my.presenter.RegPresenter;
import com.bwie.jd_demo.mvp.my.view.iview.RegView;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegAcyivity extends BaseActivity<RegPresenter> implements RegView {

    private EditText mobile;
    private EditText pass;
    private Button regBtn;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = RegAcyivity.this.mobile.getText().toString().trim();
                String password = pass.getText().toString().trim();
                presenter.RegDataNet(mobile, password);
            }
        });
    }

    @Override
    protected void initView() {
        mobile = findViewById(R.id.my_reg_user);
        pass = findViewById(R.id.my_reg_pass);
        regBtn = findViewById(R.id.my_reg_btn);
    }

    @Override
    protected RegPresenter providerPresenter() {
        return presenter = new RegPresenter(this);
    }

    @Override
    protected int providerLayout() {
        return R.layout.activity_reg;
    }

    @Override
    public void getSuccess(RegBean regBean) {
        Toast.makeText(this, "" + regBean.getMsg(), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void getError(String error) {
        Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();

    }
}
