package com.bwie.jd_demo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.adapter.MyFragmentViewPager;
import com.bwie.jd_demo.mvp.Find.view.FindFragment;
import com.bwie.jd_demo.mvp.classify.view.ClassifyFragment;
import com.bwie.jd_demo.mvp.home.view.HomeFragment;
import com.bwie.jd_demo.mvp.my.view.MyFragment;
import com.bwie.jd_demo.mvp.shopping.view.ShoppingFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    private ViewPager mShowViewpager;
    private BottomBar mShowBottombar;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
        initListener();
    }


    private void initView() {
        mShowViewpager = (ViewPager) findViewById(R.id.show_viewpager);
        mShowBottombar = (BottomBar) findViewById(R.id.show_bottombar);

        fragments.add(new HomeFragment());
        fragments.add(new ClassifyFragment());
        fragments.add(new FindFragment());
        fragments.add(new ShoppingFragment());
        fragments.add(new MyFragment());

        MyFragmentViewPager fragmentViewPager = new MyFragmentViewPager(getSupportFragmentManager(), fragments);
        mShowViewpager.setAdapter(fragmentViewPager);
    }

    private void initListener() {
        mShowBottombar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId) {
                    case R.id.btn1:
                        mShowViewpager.setCurrentItem(0);
                        break;
                    case R.id.btn2:
                        mShowViewpager.setCurrentItem(1);
                        break;
                    case R.id.btn3:
                        mShowViewpager.setCurrentItem(2);
                        break;
                    case R.id.btn4:
                        mShowViewpager.setCurrentItem(3);
                        break;
                    case R.id.btn5:
                        mShowViewpager.setCurrentItem(4);
                        break;
                }
            }
        });

        mShowViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mShowBottombar.selectTabWithId(R.id.btn1);
                        break;
                    case 1:
                        mShowBottombar.selectTabWithId(R.id.btn2);
                        break;
                    case 2:
                        mShowBottombar.selectTabWithId(R.id.btn3);
                        break;
                    case 3:
                        mShowBottombar.selectTabWithId(R.id.btn4);
                        break;
                    case 4:
                        mShowBottombar.selectTabWithId(R.id.btn5);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
