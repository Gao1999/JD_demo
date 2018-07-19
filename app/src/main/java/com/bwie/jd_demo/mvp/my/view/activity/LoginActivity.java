package com.bwie.jd_demo.mvp.my.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.base.BaseActivity;
import com.bwie.jd_demo.mvp.my.model.bean.LoginBean;
import com.bwie.jd_demo.mvp.my.presenter.LoginPresenter;
import com.bwie.jd_demo.mvp.my.view.iview.LoginView;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {


    private EditText pass;
    private EditText user;
    private Button button;
    private Button regBtn;

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String password = pass.getText().toString().trim();
                final String mobile = user.getText().toString().trim();
                presenter.loginDataNet(mobile, password);
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegAcyivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initView() {
        pass = findViewById(R.id.my_login_pass);
        user = findViewById(R.id.my_login_user);
        button = findViewById(R.id.my_login);
        regBtn = findViewById(R.id.my_reg);



    }

    @Override
    protected LoginPresenter providerPresenter() {
        return presenter = new LoginPresenter(this);
    }

    @Override
    protected int providerLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void getSuccess(LoginBean loginBean) {
        Toast.makeText(this, "登录成功,欢迎:" + loginBean.getData().getMobile(), Toast.LENGTH_SHORT).show();
        String icon = loginBean.getData().getIcon();
        String mobile = loginBean.getData().getMobile();
        int uid = loginBean.getData().getUid();

        SharedPreferences sharedPreferences = getSharedPreferences("jd", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("login", true);
        editor.putString("user", mobile);
        editor.putString("icon", icon);
        editor.putInt("uid", uid);
        editor.commit();

        Intent intent = getIntent();
        intent.putExtra("pic", icon);
        intent.putExtra("mobile", mobile);
        setResult(1, intent);
        finish();

    }

    @Override
    public void getError(String error) {
        Toast.makeText(this, "登录失败:" + error, Toast.LENGTH_SHORT).show();
    }
}
