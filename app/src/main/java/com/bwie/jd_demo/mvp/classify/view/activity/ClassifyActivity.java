package com.bwie.jd_demo.mvp.classify.view.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.base.BaseActivity;
import com.bwie.jd_demo.mvp.classify.model.bean.ClassifyBean;
import com.bwie.jd_demo.mvp.classify.model.bean.ClassifyLeftBean;
import com.bwie.jd_demo.mvp.classify.model.bean.ClassifyRightBean;
import com.bwie.jd_demo.mvp.classify.presenter.ClassifyPresenter;
import com.bwie.jd_demo.mvp.classify.view.adapter.MyRecycleProduct;
import com.bwie.jd_demo.mvp.classify.view.iview.ClassifyView;
import com.bwie.jd_demo.mvp.home.view.GoodsdetailsActivity;

import java.util.List;

public class ClassifyActivity extends BaseActivity<ClassifyPresenter> implements ClassifyView {
    private int pscid;

    private RecyclerView recyclerView;
    private Button btnMr;
    private Button btnXL;
    private Button btnJG;
    private MyRecycleProduct myRecycleProduct;


    @Override
    protected void initData() {
        int page = 1;
        int sort = 0;
        presenter.Classifyproduct(pscid, page, sort);
    }

    @Override
    protected void initListener() {
        btnJG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int page = 1;
                int sort = 2;
                presenter.Classifyproduct(pscid, page, sort);
            }
        });
        btnMr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int page = 1;
                int sort = 0;
                presenter.Classifyproduct(pscid, page, sort);
            }
        });
        btnXL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int page = 1;
                int sort = 1;
                presenter.Classifyproduct(pscid, page, sort);
            }
        });
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.classify_recycle);
        btnMr = findViewById(R.id.classify_moreng);
        btnXL = findViewById(R.id.classify_xiaoliang);
        btnJG = findViewById(R.id.classify_jiage);

        Intent intent = getIntent();
        pscid = intent.getIntExtra("pscid", 1);
        Log.e("tag", "onCreate: " + pscid);


    }

    @Override
    protected ClassifyPresenter providerPresenter() {
        return presenter = new ClassifyPresenter(this);
    }

    @Override
    protected int providerLayout() {
        return R.layout.activity_classify;
    }

    @Override
    public void getSuccess(ClassifyLeftBean classifyLeftBean) {

    }

    @Override
    public void getError(String error) {

    }

    @Override
    public void Success(ClassifyRightBean classifyRightBean) {

    }

    @Override
    public void Error(String error) {
        Log.e("tag", "Error: " + error);
    }

    @Override
    public void SUCCESS(final ClassifyBean classifyBean) {
        Log.e("log", "ClassifyActivity---: "+ classifyBean.getData().size());

        Log.e("tag", "SUCCESS: " + classifyBean.getMsg());
        List<ClassifyBean.DataBean> list = classifyBean.getData();
        myRecycleProduct = new MyRecycleProduct(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ClassifyActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myRecycleProduct);

        myRecycleProduct.setOnClickItemListener(new MyRecycleProduct.setOnClickItemListener() {
            @Override
            public void OnClickItemListener(int position) {
                int pid = classifyBean.getData().get(position).getPid();
                Intent intent = new Intent(ClassifyActivity.this, GoodsdetailsActivity.class);
                intent.putExtra("pid", pid);
                startActivity(intent);
            }
        });

    }
}
