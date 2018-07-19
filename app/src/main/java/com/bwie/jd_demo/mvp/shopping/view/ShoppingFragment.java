package com.bwie.jd_demo.mvp.shopping.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.base.BaseFragment;
import com.bwie.jd_demo.mvp.shopping.model.bean.DeleteCartBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.ShoppingBean;
import com.bwie.jd_demo.mvp.shopping.model.bean.UpDataCartBean;
import com.bwie.jd_demo.mvp.shopping.presenter.ShoppingPresenter;
import com.bwie.jd_demo.mvp.shopping.view.adapter.MyExpandableListView;
import com.bwie.jd_demo.mvp.shopping.view.iview.ShoppingView;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ShoppingFragment extends BaseFragment<ShoppingPresenter> implements ShoppingView, View.OnClickListener {
    private ExpandableListView expandableListView;
    private MyExpandableListView myExpandableListView;
    private CheckBox checkBox;
    private TextView priceTv;
    private Button numBtn;
    private int uid;
    private boolean login;
    private List<ShoppingBean.DataBean> list = new ArrayList<>();

    @Override
    protected ShoppingPresenter providePresenter() {
        return presenter = new ShoppingPresenter(this);
    }

    @Override
    protected void initData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("jd", MODE_PRIVATE);
        login = sharedPreferences.getBoolean("login", false);
        if (login) {
            uid = sharedPreferences.getInt("uid", 0);
            presenter.ShoppingNetData(uid);
        } else {
            Toast.makeText(getActivity(), "您还没有登录,请先去登录...", Toast.LENGTH_SHORT).show();
            if (myExpandableListView!=null){
                list.clear();
                myExpandableListView.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initViews(View view) {
        expandableListView = view.findViewById(R.id.shopping_exListview);
        checkBox = view.findViewById(R.id.shopping_checkbox);
        priceTv = view.findViewById(R.id.shopping_price);
        numBtn = view.findViewById(R.id.shopping_number);
        checkBox.setOnClickListener(this);


    }

    @Override
    protected int provideLayout() {
        return R.layout.fragment_shopping;
    }

    @Override
    public void Success(final ShoppingBean shoppingBean) {
        Log.e("tag", "Success: " + "saksadasdasdadsadads");
        final List<ShoppingBean.DataBean> data = shoppingBean.getData();
        Log.e("tag", "Success: " + data.size());
        list.clear();
        list.addAll(data);
        myExpandableListView = new MyExpandableListView(list);

        myExpandableListView.setOnShoppingClickListener(new MyExpandableListView.setOnShoppingClickListener() {
            @Override
            public void setOnClickSurllentChange(int groupPosition) {


                boolean surllentAllStatus = myExpandableListView.isSurllentAllStatus(groupPosition);
                myExpandableListView.onChangeSurllent(!surllentAllStatus, groupPosition);
                List<ShoppingBean.DataBean.ListBean> list = ShoppingFragment.this.list.get(groupPosition).getList();
                for (int i = 0; i < list.size(); i++) {
                    int sellerid = list.get(i).getSellerid();
                    int pid = list.get(i).getPid();
                    int num = list.get(i).getNum();
                    int selected = list.get(i).getSelected();

                    presenter.UpDataCartNet(uid, sellerid, pid, num, selected);
                }
                myExpandableListView.notifyDataSetChanged();
                setChangeNumberAndPriceStatus();
            }

            @Override
            public void setOnClickProductChange(int groupPosition, int childPosition) {
                ShoppingBean.DataBean.ListBean listBean = list.get(groupPosition).getList().get(childPosition);
                int sellerid = listBean.getSellerid();
                int pid = listBean.getPid();
                int num = listBean.getNum();
                int selected = listBean.getSelected();
                presenter.UpDataCartNet(uid, sellerid, pid, num, selected);
                myExpandableListView.onChangeProduct(groupPosition, childPosition);
                myExpandableListView.notifyDataSetChanged();
                setChangeNumberAndPriceStatus();
            }

            @Override
            public void setOnClickNumberAddAndRemoveChange(int groupPosition, int childPosition, int num) {
                ShoppingBean.DataBean.ListBean listBean = list.get(groupPosition).getList().get(childPosition);
                int sellerid = listBean.getSellerid();
                int pid = listBean.getPid();
                int selected = listBean.getSelected();
                presenter.UpDataCartNet(uid, sellerid, pid, num, selected);

                myExpandableListView.onChangeNumberAndPriceStatus(groupPosition, childPosition, num);
                myExpandableListView.notifyDataSetChanged();
                setChangeNumberAndPriceStatus();
            }

            @Override
            public void setOnClickItemDeleteListener(final int groupPosition, int childPosition) {
                ShoppingBean.DataBean dataBean = shoppingBean.getData().get(groupPosition);
                final ShoppingBean.DataBean.ListBean listBean = dataBean.getList().get(childPosition);


                AlertDialog dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("确定删除吗?")
                        .setIcon(R.mipmap.ic_launcher)
                        .setNeutralButton("取消", null)
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int pid = listBean.getPid();
                                presenter.deleteCart(uid, pid);
                                myExpandableListView.notifyDataSetChanged();
                                setChangeNumberAndPriceStatus();

                            }
                        })
                        .create();
                dialog.show();

            }
        });
        expandableListView.setAdapter(myExpandableListView);

        for (int i = 0; i < list.size(); i++) {
            expandableListView.expandGroup(i);
        }

        expandableListView.setGroupIndicator(null);
        setChangeNumberAndPriceStatus();

    }

    @Override
    public void Error(String error) {
        Log.d("tag", "Error: " + error);
        if(myExpandableListView!=null){
            list.clear();
            myExpandableListView.notifyDataSetChanged();
        }

    }

    @Override
    public void SUCCESS(DeleteCartBean deleteCartBean) {
        Toast.makeText(getContext(), "" + deleteCartBean.getMsg(), Toast.LENGTH_SHORT).show();
        presenter.ShoppingNetData(uid);
        myExpandableListView.notifyDataSetChanged();
        setChangeNumberAndPriceStatus();

    }

    @Override
    public void UpDataCartSuccess(UpDataCartBean upDataCartBean) {
        Log.e("tag", "UpDataCartSuccess: " + upDataCartBean.getMsg());
    }


    public void setChangeNumberAndPriceStatus() {
        //判断所有的商品是否被选中
        boolean clickChangeSurllendStatus = myExpandableListView.isClickChangeSurllendStatus();
        checkBox.setChecked(clickChangeSurllendStatus);

        double priceStutus = myExpandableListView.isChangePriceStutus();
        priceTv.setText("￥:" + priceStutus);

        int numberStatus = myExpandableListView.isChangeNumberStatus();
        numBtn.setText("去结算(" + numberStatus + ")");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shopping_checkbox:
                boolean clickChangeSurllendStatus = myExpandableListView.isClickChangeSurllendStatus();
                myExpandableListView.isClickCheckedChangerStatus(!clickChangeSurllendStatus);
                myExpandableListView.notifyDataSetChanged();
                setChangeNumberAndPriceStatus();

                break;
        }
    }

}
