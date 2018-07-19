package com.bwie.jd_demo.mvp.home.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.base.BaseActivity;
import com.bwie.jd_demo.mvp.home.model.bean.AddCartBean;
import com.bwie.jd_demo.mvp.home.model.bean.GoodsDetailBean;
import com.bwie.jd_demo.mvp.home.model.bean.SearchBean;
import com.bwie.jd_demo.mvp.home.presenter.GoodsPresenter;
import com.bwie.jd_demo.mvp.home.view.adapter.MyRecycleSearchProduct;
import com.bwie.jd_demo.mvp.home.view.iview.IGoodsDetailsView;

import java.util.List;

public class SearchProductActivity extends BaseActivity<GoodsPresenter> implements IGoodsDetailsView {
    private String keywords;
    private RecyclerView recyclerView;
    private Button btnMr;
    private Button btnXL;
    private Button btnJG;


    @Override
    protected void initData() {
        presenter.SearchNet(keywords, 1, 0);
    }

    @Override
    protected void initListener() {
        btnMr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.SearchNet(keywords, 1, 0);
            }
        });
        btnXL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.SearchNet(keywords, 1, 1);
            }
        });
        btnJG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.SearchNet(keywords, 1, 2);
            }
        });
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        keywords = intent.getStringExtra("keywords");
        Log.e("log", "initView: " + keywords);

        recyclerView = findViewById(R.id.search_product_recycle);
        btnMr = findViewById(R.id.search_product_MR);
        btnXL = findViewById(R.id.search_product_XL);
        btnJG = findViewById(R.id.search_product_JG);

    }

    @Override
    protected GoodsPresenter providerPresenter() {
        return presenter = new GoodsPresenter(this);
    }

    @Override
    protected int providerLayout() {
        return R.layout.activity_searchproduct;
    }

    @Override
    public void SUCCESS(GoodsDetailBean goodsDetailBean) {

    }

    @Override
    public void ERROR(String error) {

    }

    @Override
    public void GETSUCCESS(AddCartBean addCartBean) {

    }

    @Override
    public void GETERROR(String error) {

    }

    @Override
    public void SeachSuccess(SearchBean searchBean) {
        Log.e("log", "SeachSuccess: " + searchBean.getMsg());

        final List<SearchBean.DataBean> data = searchBean.getData();

        MyRecycleSearchProduct myRecycleSearchProduct = new MyRecycleSearchProduct(data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myRecycleSearchProduct);

        myRecycleSearchProduct.setOnClickItemListener(new MyRecycleSearchProduct.setOnClickItemListener() {
            @Override
            public void OnClickItemListener(int position) {
                int pid = data.get(position).getPid();
                Intent intent = new Intent(SearchProductActivity.this, GoodsdetailsActivity.class);
                intent.putExtra("pid", pid);
                startActivityForResult(intent, 100);
            }
        });
    }
}
