package com.bwie.jd_demo.mvp.shopping.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.base.BaseActivity;
import com.bwie.jd_demo.mvp.shopping.model.bean.CreateOrderBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.DefaultAddressBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.DeleteCartBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.OrdersBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.ShoppingBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.UpDataCartBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.UpdataOrderBean;
import com.bwie.jd_demo.mvp.shopping.presenter.ShoppingPresenter;
import com.bwie.jd_demo.mvp.shopping.view.adapter.MyOrdersRecycle;
import com.bwie.jd_demo.mvp.shopping.view.iview.ShoppingView;

import java.util.List;

public class SelectOrdersActivity extends BaseActivity<ShoppingPresenter> implements ShoppingView, View.OnClickListener {

    private RecyclerView recyclerView;
    private int uid;
    private String[] item = {"待支付", "已支付", "已取消", "取消"};
    private MyOrdersRecycle myOrdersRecycle;
    private LinearLayout linearLayout;
    private TextView userTv;
    private TextView phoneTv;
    private TextView addressTv;
    private TextView dzfTv;
    private TextView yzfTv;
    private TextView yqxTv;
    private SharedPreferences sharedPreferences;
    private boolean login;

    @Override
    protected void initData() {
        presenter.SelectOrderNet(uid, 0);
        if (login) {
            presenter.DefaultAddressNet(uid);
        }

    }

    @Override
    protected void initListener() {
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectOrdersActivity.this, AddressActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.SelectOrder_recycle);
        linearLayout = findViewById(R.id.SelectOrder_linear);
        userTv = findViewById(R.id.SelectOrder_user);
        phoneTv = findViewById(R.id.SelectOrder_phone);
        addressTv = findViewById(R.id.SelectOrder_address);
        dzfTv = findViewById(R.id.SelectOrder_dzf);
        yzfTv = findViewById(R.id.SelectOrder_yzf);
        yqxTv = findViewById(R.id.SelectOrder_yqx);

        dzfTv.setOnClickListener(this);
        yzfTv.setOnClickListener(this);
        yqxTv.setOnClickListener(this);

        sharedPreferences = getSharedPreferences("jd", MODE_PRIVATE);
        login = sharedPreferences.getBoolean("login", false);

        uid = sharedPreferences.getInt("uid", 0);
    }

    @Override
    protected ShoppingPresenter providerPresenter() {
        return presenter = new ShoppingPresenter(this);
    }

    @Override
    protected int providerLayout() {
        return R.layout.activity_select_orders;
    }

    @Override
    public void Success(ShoppingBean shoppingBean) {

    }

    @Override
    public void Error(String error) {
        Log.e("tag", "Error: " + error);
    }

    @Override
    public void SUCCESS(DeleteCartBean deleteCartBean) {

    }

    @Override
    public void UpDataCartSuccess(UpDataCartBean upDataCartBean) {

    }

    @Override
    public void CreateOrdeSuccess(CreateOrderBean createOrderBean) {

    }

    @Override
    public void SelectOrderNetSuccess(OrdersBean ordersBean) {
        Log.e("tag", "SelectOrderNetSuccess: " + ordersBean.getMsg());

        final List<OrdersBean.DataBean> data = ordersBean.getData();
        myOrdersRecycle = new MyOrdersRecycle(data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SelectOrdersActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myOrdersRecycle);
        myOrdersRecycle.notifyDataSetChanged();

        myOrdersRecycle.setOnItemClickListener(new MyOrdersRecycle.setOnItemClickListener() {
            @Override
            public void OnItemClickListener(final int position) {
                final int orderid = data.get(position).getOrderid();

                AlertDialog dialog = new AlertDialog.Builder(SelectOrdersActivity.this)
                        .setTitle("修改订单状态")
                        .setIcon(R.mipmap.ic_launcher)
                        .setItems(item, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:

                                        presenter.UpdateOrderNetFrom(uid, 0, orderid);

                                        break;
                                    case 1:
                                        presenter.UpdateOrderNetFrom(uid, 1, orderid);

                                        break;

                                    case 2:
                                        presenter.UpdateOrderNetFrom(uid, 2, orderid);

                                        break;
                                }
                            }
                        })

                        .create();
                dialog.show();
            }
        });
    }

    @Override
    public void UpdateOrderNetSuccess(UpdataOrderBean updataOrderBean) {
        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        presenter.SelectOrderNet(uid, 0);
        dzfTv.setTextColor(Color.RED);
        yzfTv.setTextColor(Color.BLACK);
        yqxTv.setTextColor(Color.BLACK);
        myOrdersRecycle.notifyDataSetChanged();
    }

    @Override
    public void DefaultAddrSuccess(DefaultAddressBean defaultAddressBean) {
        Log.e("tag", "DefaultAddrSuccess: " + defaultAddressBean.getMsg());
        userTv.setText("姓名:" + defaultAddressBean.getData().getName());
        phoneTv.setText("手机号:" + defaultAddressBean.getData().getMobile());
        addressTv.setText("收货地址:" + defaultAddressBean.getData().getAddr());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SelectOrder_dzf:
                presenter.SelectOrderNet(uid, 0);
                dzfTv.setTextColor(Color.RED);
                yzfTv.setTextColor(Color.BLACK);
                yqxTv.setTextColor(Color.BLACK);
                break;
            case R.id.SelectOrder_yzf:
                dzfTv.setTextColor(Color.BLACK);
                yzfTv.setTextColor(Color.RED);
                yqxTv.setTextColor(Color.BLACK);
                presenter.SelectOrderNet(uid, 1);
                break;
            case R.id.SelectOrder_yqx:
                dzfTv.setTextColor(Color.BLACK);
                yzfTv.setTextColor(Color.BLACK);
                yqxTv.setTextColor(Color.RED);
                presenter.SelectOrderNet(uid, 2);
                break;
        }
    }
}
