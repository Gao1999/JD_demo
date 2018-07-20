package com.bwie.jd_demo.mvp.Find.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.mvp.Find.adapter.FindFragmentViewPager;
import com.bwie.jd_demo.mvp.Find.fragment.FoodFragment;
import com.bwie.jd_demo.mvp.Find.fragment.NewsFragment;
import com.bwie.jd_demo.mvp.Find.fragment.WelfareFragment;

import java.util.ArrayList;
import java.util.List;

public class FindFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fs = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        initViews(view);
        return view;
    }


    private void initViews(View view) {

        tabLayout = view.findViewById(R.id.find_tablayout);
        viewPager = view.findViewById(R.id.find_viewpager);
        fs.clear();
        NewsFragment newsFragment = NewsFragment.create("新闻");
        fs.add(newsFragment);
        WelfareFragment welfareFragment = WelfareFragment.create("福利");
        fs.add(welfareFragment);
        FoodFragment foodFragment = FoodFragment.create("美食");
        fs.add(foodFragment);


        FindFragmentViewPager findFragmentViewPager = new FindFragmentViewPager(getChildFragmentManager(), fs);
        viewPager.setAdapter(findFragmentViewPager);
        //添加选项卡
        TabLayout.Tab tab1 = tabLayout.newTab();
        tabLayout.addTab(tab1);
        TabLayout.Tab tab2 = tabLayout.newTab();
        tabLayout.addTab(tab2);
        TabLayout.Tab tab3 = tabLayout.newTab();
        tabLayout.addTab(tab3);
        //将viewpager关联到tablayout
        tabLayout.setupWithViewPager(viewPager);
        //设置可以滑动
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
}
