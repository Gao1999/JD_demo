package com.bwie.jd_demo.mvp.home.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.database.DaoSession;
import com.bwie.jd_demo.database.ProductTitleDao;
import com.bwie.jd_demo.utils.DaoManager;
import com.bwie.jd_demo.utils.ProductTitle;
import com.bwie.jd_demo.view.FlowLayout;

import java.util.List;

public class SearchActivity extends AppCompatActivity {
    //Product
    private EditText searchET;
    private ImageView searchSS;
    private FlowLayout flowLayout;
    private Button deleteBtn;
    private DaoManager daoManager;
    private DaoSession daoSession;
    private ProductTitleDao productTitleDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();
        initListener();

    }

    private void initListener() {
        searchSS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keywords = searchET.getText().toString().trim();
                if ("".equals(keywords)) {
                    Toast.makeText(SearchActivity.this, "请输入您要查的商品", Toast.LENGTH_SHORT).show();
                    return;
                }
                productTitleDao.insert(new ProductTitle(null, keywords));

                Intent intent = new Intent(SearchActivity.this, SearchProductActivity.class);
                intent.putExtra("keywords", keywords);
                startActivity(intent);

                TextView tv = new TextView(SearchActivity.this);
                tv.setText(keywords);
                tv.setTextSize(24);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                layoutParams.setMargins(10, 10, 10, 10);

                flowLayout.addView(tv, layoutParams);
                finish();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flowLayout.removeAllViews();
                productTitleDao.deleteAll();
            }
        });

        flowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void initViews() {
        searchET = findViewById(R.id.search_et);
        searchSS = findViewById(R.id.search_ss);
        flowLayout = findViewById(R.id.search_flowlayout);
        deleteBtn = findViewById(R.id.search_delete);

        daoManager = DaoManager.instance(this);
        daoSession = daoManager.getDaoSession();
        productTitleDao = daoSession.getProductTitleDao();

        List<ProductTitle> list = productTitleDao.queryBuilder().build().list();

        for (int i = 0; i < list.size(); i++) {
            TextView tv = new TextView(SearchActivity.this);
            tv.setText(list.get(i).getTitle());
            tv.setTextSize(24);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            layoutParams.setMargins(10, 10, 10, 10);

            flowLayout.addView(tv, layoutParams);
        }

    }
}
