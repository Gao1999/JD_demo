package com.bwie.jd_demo.mvp.shopping.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.base.BaseActivity;
import com.bwie.jd_demo.mvp.home.view.SearchActivity;
import com.bwie.jd_demo.mvp.shopping.model.bean.AddadderssBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.SelectAddressBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.SetAddressBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.UpdataAddrBean;
import com.bwie.jd_demo.mvp.shopping.presenter.AddressPresenter;
import com.bwie.jd_demo.mvp.shopping.presenter.ShoppingPresenter;
import com.bwie.jd_demo.mvp.shopping.view.adapter.MyAddressRecycle;
import com.bwie.jd_demo.mvp.shopping.view.iview.AddressView;
import com.bwie.jd_demo.mvp.shopping.view.iview.ShoppingView;

import java.util.List;

public class AddressActivity extends BaseActivity<AddressPresenter> implements AddressView {


    private Button address;
    private RecyclerView recyclerView;
    private int uid;
    private MyAddressRecycle myAddressRecycle;
    private boolean login;

    @Override
    protected void initData() {
        if (login) {
            presenter.SelectAddressNet(uid);
        } else {
            Toast.makeText(this, "请先进行登录", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void initListener() {

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater from = LayoutInflater.from(AddressActivity.this);
                View view = from.inflate(R.layout.address_alertdialog_view, null);

                final EditText name = view.findViewById(R.id.alertdialog_name);
                final EditText phone = view.findViewById(R.id.alertdialog_phone);
                final EditText dizi = view.findViewById(R.id.alertdialog_address);
                AlertDialog dialog = new AlertDialog.Builder(AddressActivity.this)
                        .setTitle("添加地址")
                        .setIcon(R.mipmap.ic_launcher)
                        .setView(view)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String iuser = name.getText().toString().trim();
                                String iphones = phone.getText().toString().trim();
                                String iaddrss = dizi.getText().toString().trim();
                                Log.e("tag", "onClick: " + iuser + iphones + iaddrss);
                                presenter.addAddrNet(uid, iaddrss, iphones, iuser);
                            }
                        })
                        .setNegativeButton("取消", null)


                        .create();
                dialog.show();
            }
        });
    }

    @Override
    protected void initView() {
        address = findViewById(R.id.address_insertaddress);
        recyclerView = findViewById(R.id.address_recycle);

        SharedPreferences sharedPreferences = getSharedPreferences("jd", MODE_PRIVATE);
        uid = sharedPreferences.getInt("uid", 0);
        login = sharedPreferences.getBoolean("login", false);
    }

    @Override
    protected AddressPresenter providerPresenter() {
        return presenter = new AddressPresenter(this);
    }

    @Override
    protected int providerLayout() {
        return R.layout.activity_address;
    }

    @Override
    public void AddressSuccess(SelectAddressBean selectAddressBean) {
        Log.e("tag", "AddressSuccess: " + selectAddressBean.getMsg());
        final List<SelectAddressBean.DataBean> data = selectAddressBean.getData();
        myAddressRecycle = new MyAddressRecycle(data, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAddressRecycle);


        myAddressRecycle.setOnClickItemLister(new MyAddressRecycle.setOnClickItemLister() {
            @Override
            public void OnClickItemLister(int position) {

                final int addrid = data.get(position).getAddrid();
                String name1 = data.get(position).getName();
                String mobile = String.valueOf(data.get(position).getMobile());
                String addr = data.get(position).getAddr();

                LayoutInflater from = LayoutInflater.from(AddressActivity.this);
                View view = from.inflate(R.layout.address_alertdialog_view, null);

                final EditText name = view.findViewById(R.id.alertdialog_name);
                final EditText phone = view.findViewById(R.id.alertdialog_phone);
                final EditText dizi = view.findViewById(R.id.alertdialog_address);

                name.setText(name1);
                phone.setText(mobile);
                dizi.setText(addr);

                AlertDialog dialog = new AlertDialog.Builder(AddressActivity.this)
                        .setTitle("添加地址")
                        .setIcon(R.mipmap.ic_launcher)
                        .setView(view)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String iuser = name.getText().toString().trim();
                                String iphones = phone.getText().toString().trim();
                                String iaddrss = dizi.getText().toString().trim();
                                Log.e("tag", "OnClickItemLister: " + iuser + iphones + iaddrss);

                                presenter.UpdataAddrNet(uid, addrid, iaddrss, iphones, iuser);


                            }
                        })
                        .setNegativeButton("取消", null)


                        .create();
                dialog.show();

            }

            @Override
            public void OnClickItemCheckboxListener(int position) {
                int addrid = data.get(position).getAddrid();

                presenter.SetAddressNet(uid, addrid, 1);

            }
        });

    }

    @Override
    public void Error(String error) {
        Log.e("tag", "Error: " + error);
    }

    @Override
    public void AddaddressSuccess(AddadderssBean addadderssBean) {
        Log.e("tag", "AddaddressSuccess: " + addadderssBean.getMsg());
        presenter.SelectAddressNet(uid);
        myAddressRecycle.notifyDataSetChanged();
        Intent intent = new Intent(AddressActivity.this, SelectOrdersActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void UpdataAddrSuccess(UpdataAddrBean updataAddrBean) {
        Log.e("tag", "UpdataAddrSuccess: " + updataAddrBean.getMsg());
        presenter.SelectAddressNet(uid);
        myAddressRecycle.notifyDataSetChanged();
        Intent intent = new Intent(AddressActivity.this, SelectOrdersActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void SetAddressSuccess(SetAddressBean setAddressBean) {
        Log.e("tag", "UpdataAddrSuccess: " + setAddressBean.getMsg());
        presenter.SelectAddressNet(uid);
        myAddressRecycle.notifyDataSetChanged();
        Intent intent = new Intent(AddressActivity.this, SelectOrdersActivity.class);
        startActivity(intent);
        finish();
    }
}
