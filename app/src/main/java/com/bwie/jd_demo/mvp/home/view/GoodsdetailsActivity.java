package com.bwie.jd_demo.mvp.home.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.base.BaseActivity;
import com.bwie.jd_demo.mvp.home.model.bean.AddCartBean;
import com.bwie.jd_demo.mvp.home.model.bean.GoodsDetailBean;
import com.bwie.jd_demo.mvp.home.model.bean.SearchBean;
import com.bwie.jd_demo.mvp.home.presenter.GoodsPresenter;
import com.bwie.jd_demo.mvp.home.view.adapter.MyRecycleGoods;
import com.bwie.jd_demo.mvp.home.view.iview.IGoodsDetailsView;
import com.bwie.jd_demo.mvp.my.view.activity.LoginActivity;
import com.bwie.jd_demo.mvp.shopping.model.bean.CreateOrderBean;
import com.bwie.jd_demo.mvp.shopping.view.SelectOrdersActivity;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.lang.reflect.Array;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoodsdetailsActivity extends BaseActivity<GoodsPresenter> implements IGoodsDetailsView {
    private int pid;
    private XBanner xBanner;
    private Button addcart;
    private ImageView back;
    private TextView price;
    private TextView title;
    private TextView name;
    private int uid;
    private Button createOrder;
    private double dZprice;

    @Override
    protected void initData() {
        presenter.GoodsXbannerNet(pid);
    }

    @Override
    protected void initListener() {

        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("jd", MODE_PRIVATE);
                uid = sharedPreferences.getInt("uid", 0);
                boolean login = sharedPreferences.getBoolean("login", false);
                if (login) {
                    presenter.AddCartNet(uid, pid);
                } else {
                    AlertDialog dialog = new AlertDialog.Builder(GoodsdetailsActivity.this)
                            .setTitle("需要去登录吗?")
                            .setIcon(R.mipmap.ic_launcher)
                            .setNeutralButton("取消", null)
                            .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(GoodsdetailsActivity.this, LoginActivity.class);

                                    startActivity(intent);
                                }
                            })
                            .create();
                    dialog.show();
                }

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        createOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("tag", "onClick: " + dZprice);
                presenter.createOrderNet(uid, dZprice);
            }
        });
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        pid = intent.getIntExtra("pid", 57);
        xBanner = findViewById(R.id.goodsDetails_xbanner);
        addcart = findViewById(R.id.goods_add_shooping);
        back = findViewById(R.id.goods_back);
        price = findViewById(R.id.goodsDetails_price);
        title = findViewById(R.id.goodsDetails_title);
        name = findViewById(R.id.goodsDetails_name);
        createOrder = findViewById(R.id.goods_createOrder);


    }

    @Override
    protected GoodsPresenter providerPresenter() {
        return presenter = new GoodsPresenter(this);
    }

    @Override
    protected int providerLayout() {
        return R.layout.activity_goodsdetails;
    }

    @Override


    public void SUCCESS(GoodsDetailBean goodsDetailBean) {
        Log.e("log", "ClassifyActivity---: " + goodsDetailBean.getData().getTitle());

        GoodsDetailBean.DataBean data = goodsDetailBean.getData();

        String images = goodsDetailBean.getData().getImages();

        final List<String> list = new ArrayList<>();
        String[] split = images.split("\\|");
        List<String> list1 = Arrays.asList(split);
        for (int i = 0; i < list1.size(); i++) {
            list.add(split[i]);
        }

        xBanner.setData(R.layout.image_fresco, list, null);

        xBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                SimpleDraweeView simpleDraweeView = view.findViewById(R.id.my_image_view);

                simpleDraweeView.setImageURI(list.get(position));
            }
        });
        xBanner.setPageTransformer(Transformer.Alpha);

        // 设置XBanner页面切换的时间，即动画时长
        xBanner.setPageChangeDuration(1000);
        dZprice = data.getBargainPrice();
        price.setText("￥:" + dZprice);
        title.setText(data.getTitle());
        name.setText(data.getSubhead());
    }

    @Override
    public void ERROR(String error) {
        Log.e("tag", "ERROR: " + error);
    }

    @Override
    public void GETSUCCESS(AddCartBean addCartBean) {
        Toast.makeText(this, "加入成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void GETERROR(String error) {
        Log.e("tag", "GETERROR: " + error);
        Toast.makeText(this, "加入失败", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void SeachSuccess(SearchBean searchBean) {

    }

    @Override
    public void createOrderSuccess(CreateOrderBean createOrderBean) {
        Log.e("tag", "GETERROR: " + createOrderBean.getMsg());
        Intent intent = new Intent(GoodsdetailsActivity.this, SelectOrdersActivity.class);
        startActivity(intent);
    }
}
