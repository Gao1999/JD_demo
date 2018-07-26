package com.bwie.jd_demo.mvp.home.view;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.base.BaseFragment;
import com.bwie.jd_demo.mvp.home.model.bean.AdBean;
import com.bwie.jd_demo.mvp.home.model.bean.ClassBean;
import com.bwie.jd_demo.mvp.home.presenter.HomePresenter;
import com.bwie.jd_demo.mvp.home.view.adapter.MyRecycleViewClass;
import com.bwie.jd_demo.mvp.home.view.adapter.MyRecycleViewMS;
import com.bwie.jd_demo.mvp.home.view.adapter.MyRecycleViewTJ;
import com.bwie.jd_demo.mvp.home.view.iview.HomeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.scwang.smartrefresh.header.TaurusHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView {
    private static final String TAG = "HomeFragment---";
    private XBanner xBanner;
    private RecyclerView recyclerView;
    private MarqueeView marqueeView;
    private RecyclerView recyclerViewMS;
    private RecyclerView recyclerViewTJ;
    private RefreshLayout refreshLayout;
    private EditText search;

    @Override
    protected HomePresenter providePresenter() {
        return presenter = new HomePresenter(this);
    }

    @Override
    protected void initData() {


        presenter.ClassFromDataNet();
    }

    @Override
    protected void initListener() {
        List<String> info = new ArrayList<>();
        info.add("IphonX 降价了");
        info.add("双十一活动");
        info.add("国庆七天乐");
        info.add("哈哈哈哈哈");
        marqueeView.startWithList(info);
        // 在代码里设置自己的动画
        marqueeView.startWithList(info, R.anim.anim_bottom_in, R.anim.anim_top_out);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000);//传入false表示加载失败
            }
        });

        //设置 Header 为 贝塞尔雷达 样式
        refreshLayout.setRefreshHeader(new TaurusHeader(getActivity()));
        //设置 Footer 为 球脉冲 样式
        refreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initViews(View view) {
        xBanner = view.findViewById(R.id.home_xbanner);
        recyclerView = view.findViewById(R.id.home_recycle);
        marqueeView = view.findViewById(R.id.marqueeview);
        recyclerViewMS = view.findViewById(R.id.home_recycle_miaosha);
        recyclerViewTJ = view.findViewById(R.id.home_recycle_tuijian);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        search = view.findViewById(R.id.home_search);
    }

    @Override
    protected int provideLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void getSuccess(final AdBean adBean) {
        Log.d(TAG, "getSuccess: " + "成功");
        List<AdBean.DataBean> adData = adBean.getData();
        final List<String> listIcon = new ArrayList<>();
        List<String> listTitle = new ArrayList<>();
        for (int i = 0; i < adData.size(); i++) {
            listIcon.add(adData.get(i).getIcon());
            listTitle.add(adData.get(i).getTitle());
        }

        xBanner.setData(R.layout.image_fresco, listIcon, listTitle);

        xBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                SimpleDraweeView simpleDraweeView = view.findViewById(R.id.my_image_view);
                simpleDraweeView.setImageURI(listIcon.get(position));
            }
        });
        xBanner.setPageTransformer(Transformer.Alpha);

        // 设置XBanner页面切换的时间，即动画时长
        xBanner.setPageChangeDuration(1000);

        //秒杀

        List<AdBean.MiaoshaBean.ListBeanX> list = adBean.getMiaosha().getList();
        MyRecycleViewMS myRecycleViewMS = new MyRecycleViewMS(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewMS.setLayoutManager(linearLayoutManager);
        recyclerViewMS.setAdapter(myRecycleViewMS);

        myRecycleViewMS.setOnClickItemMS(new MyRecycleViewMS.setOnClickItemMS() {
            @Override
            public void onClickItemMS(int position) {
                int pid = adBean.getMiaosha().getList().get(position).getPid();
                Intent intent = new Intent(getActivity(), GoodsdetailsActivity.class);
                intent.putExtra("pid", pid);
                startActivityForResult(intent, 100);
            }
        });


        //推荐
        List<AdBean.TuijianBean.ListBean> list1 = adBean.getTuijian().getList();

        MyRecycleViewTJ myRecycleViewTJ = new MyRecycleViewTJ(list1);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewTJ.setLayoutManager(gridLayoutManager);
        recyclerViewTJ.setAdapter(myRecycleViewTJ);

        myRecycleViewTJ.setSetOnClickItemTJ(new MyRecycleViewTJ.setOnClickItemTJ() {
            @Override
            public void onClickItemTJ(int position) {
                int pid = adBean.getTuijian().getList().get(position).getPid();
                Intent intent = new Intent(getActivity(), GoodsdetailsActivity.class);
                intent.putExtra("pid", pid);
                startActivityForResult(intent, 100);
            }
        });

    }

    @Override
    public void getError(String error) {
        Log.d(TAG, "getSuccess: " + "失败");
    }

    @Override
    public void Success(ClassBean classBean) {
        List<ClassBean.DataBean> data = classBean.getData();
        MyRecycleViewClass myRecycleViewClass = new MyRecycleViewClass(data);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(myRecycleViewClass);

        presenter.AdFromDataNet();
    }

    @Override
    public void Error(String error) {

    }

    @Override
    public void onResume() {
        super.onResume();
        xBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        xBanner.startAutoPlay();
    }
}
