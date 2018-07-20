package com.bwie.jd_demo.mvp.shopping.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.jd_demo.R;
import com.bwie.jd_demo.mvp.shopping.model.bean.ShoppingBean;
import com.bwie.jd_demo.view.MyAddAndRemove;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyExpandableListView extends BaseExpandableListAdapter {
    private List<ShoppingBean.DataBean> list;

    public MyExpandableListView(List<ShoppingBean.DataBean> list) {
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list== null ? 0 : list.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderGroup viewHolderGroup;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.shopping_expandablelistview_group, null);
            viewHolderGroup = new ViewHolderGroup(convertView);
            convertView.setTag(viewHolderGroup);
        } else {
            viewHolderGroup = (ViewHolderGroup) convertView.getTag();
        }
        viewHolderGroup.shoppingGroupName.setText(list.get(groupPosition).getSellerName());
        //
        boolean surllentAllStatus = isSurllentAllStatus(groupPosition);
        viewHolderGroup.shoppingGroupCheckbox.setChecked(surllentAllStatus);

        viewHolderGroup.shoppingGroupCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onShoppingClickListener != null) {
                    onShoppingClickListener.setOnClickSurllentChange(groupPosition);
                }
            }
        });
        return convertView;
    }

    public boolean isSurllentAllStatus(int groupPosition) {
        ShoppingBean.DataBean dataBean = list.get(groupPosition);
        List<ShoppingBean.DataBean.ListBean> list = dataBean.getList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSelected() == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, final ViewGroup parent) {
        ShoppingBean.DataBean dataBean = list.get(groupPosition);
        List<ShoppingBean.DataBean.ListBean> list = dataBean.getList();
        ShoppingBean.DataBean.ListBean listBean = list.get(childPosition);
        ViewHolderChild viewHolderChild;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.shopping_expandablelistview_child, null);
            viewHolderChild = new ViewHolderChild(convertView);
            convertView.setTag(viewHolderChild);
        } else {
            viewHolderChild = (ViewHolderChild) convertView.getTag();
        }
        viewHolderChild.shoppingGroupPrice.setText("￥:" + listBean.getBargainPrice());
        viewHolderChild.shoppingGroupTitlle.setText(listBean.getTitle());
        String images = listBean.getImages();
        String[] split = images.split("\\|");
        viewHolderChild.shoppingGroupPic.setImageURI(split[0]);

        viewHolderChild.shoppingGroupCheckbox.setChecked(listBean.getSelected() == 1);

        viewHolderChild.shoppingGroupCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onShoppingClickListener != null) {
                    onShoppingClickListener.setOnClickProductChange(groupPosition, childPosition);
                }
            }
        });
        viewHolderChild.MyAddAndRemove.setNumber(listBean.getNum());
        viewHolderChild.MyAddAndRemove.setOnNumChangeListener(new MyAddAndRemove.setOnNumChangeListener() {
            @Override
            public void onNumChangeListener(int num) {
                if (onShoppingClickListener != null) {
                    onShoppingClickListener.setOnClickNumberAddAndRemoveChange(groupPosition, childPosition, num);
                }
            }
        });

        viewHolderChild.shoppingGroupDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onShoppingClickListener != null) {
                    onShoppingClickListener.setOnClickItemDeleteListener(groupPosition, childPosition);
                }
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public boolean isClickChangeSurllendStatus() {
        for (int i = 0; i < list.size(); i++) {
            ShoppingBean.DataBean dataBean = list.get(i);
            List<ShoppingBean.DataBean.ListBean> list = dataBean.getList();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getSelected() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public double isChangePriceStutus() {
        double totalPrice = 0;
        for (int i = 0; i < list.size(); i++) {
            List<ShoppingBean.DataBean.ListBean> list = this.list.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getSelected() == 1) {
                    int num = list.get(j).getNum();
                    double price = list.get(j).getBargainPrice();
                    totalPrice += price * num;
                }
            }
        }
        return totalPrice;
    }

    public int isChangeNumberStatus() {
        int totalnum = 0;
        for (int i = 0; i < list.size(); i++) {
            List<ShoppingBean.DataBean.ListBean> list = this.list.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getSelected() == 1) {
                    int num = list.get(j).getNum();

                    totalnum += num;
                }
            }
        }
        return totalnum;
    }

    public void isClickCheckedChangerStatus(boolean b) {
        for (int i = 0; i < list.size(); i++) {
            List<ShoppingBean.DataBean.ListBean> list = this.list.get(i).getList();
            for (int j = 0; j < list.size(); j++) {
                list.get(j).setSelected(b ? 1 : 0);
            }

        }
    }

    public void onChangeSurllent(boolean surllentAllStatus, int groupPosition) {
        ShoppingBean.DataBean dataBean = list.get(groupPosition);
        List<ShoppingBean.DataBean.ListBean> list = dataBean.getList();
        for (int i = 0; i < list.size(); i++) {
            ShoppingBean.DataBean.ListBean listBean = list.get(i);
            listBean.setSelected(surllentAllStatus ? 1 : 0);
        }
    }

    public void onChangeProduct(int groupPosition, int childPosition) {
        ShoppingBean.DataBean dataBean = list.get(groupPosition);
        List<ShoppingBean.DataBean.ListBean> list = dataBean.getList();
        ShoppingBean.DataBean.ListBean listBean = list.get(childPosition);
        listBean.setSelected(listBean.getSelected() == 0 ? 1 : 0);
    }

    public void onChangeNumberAndPriceStatus(int groupPosition, int childPosition, int num) {
        ShoppingBean.DataBean dataBean = list.get(groupPosition);
        List<ShoppingBean.DataBean.ListBean> list = dataBean.getList();
        ShoppingBean.DataBean.ListBean listBean = list.get(childPosition);
        listBean.setNum(num);
    }

    class ViewHolderGroup {
        @BindView(R.id.shopping_group_checkbox)
        CheckBox shoppingGroupCheckbox;
        @BindView(R.id.shopping_group_name)
        TextView shoppingGroupName;

        ViewHolderGroup(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class ViewHolderChild {
        @BindView(R.id.shopping_group_checkbox)
        CheckBox shoppingGroupCheckbox;
        @BindView(R.id.shopping_group_pic)
        SimpleDraweeView shoppingGroupPic;
        @BindView(R.id.shopping_group_titlle)
        TextView shoppingGroupTitlle;
        @BindView(R.id.shopping_group_price)
        TextView shoppingGroupPrice;
        @BindView(R.id.shopping_group_delete)
        Button shoppingGroupDelete;
        @BindView(R.id.MyAddAndRemove)
        com.bwie.jd_demo.view.MyAddAndRemove MyAddAndRemove;

        ViewHolderChild(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private setOnShoppingClickListener onShoppingClickListener;

    public void setOnShoppingClickListener(setOnShoppingClickListener onShoppingClickListener) {
        this.onShoppingClickListener = onShoppingClickListener;
    }

    public interface setOnShoppingClickListener {
        void setOnClickSurllentChange(int groupPosition);

        void setOnClickProductChange(int groupPosition, int childPosition);

        void setOnClickNumberAddAndRemoveChange(int groupPosition, int childPosition, int num);

        void setOnClickItemDeleteListener(int groupPosition, int childPosition);

    }
}
