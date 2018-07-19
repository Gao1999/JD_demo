package com.bwie.jd_demo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.jd_demo.R;

public class MyAddAndRemove extends LinearLayout implements View.OnClickListener {

    private View view;
    private TextView addTv;
    private TextView numTv;
    private TextView removeTv;
    private int number;

    public MyAddAndRemove(Context context) {
        super(context);
    }

    public MyAddAndRemove(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        view = inflate(context, R.layout.addandremove_view, this);
        addTv = view.findViewById(R.id.tv_add);
        numTv = view.findViewById(R.id.tv_num);
        removeTv = view.findViewById(R.id.tv_remove);

        addTv.setOnClickListener(this);
        removeTv.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_remove:
                if (number > 1) {
                    --number;
                    numTv.setText(number + "");
                    if (onNumChangeListener != null) {
                        onNumChangeListener.onNumChangeListener(number);
                    }
                }
                break;
            case R.id.tv_add:
                ++number;
                numTv.setText(number + "");
                if (onNumChangeListener != null) {
                    onNumChangeListener.onNumChangeListener(number);
                }
                break;
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        numTv.setText(number + "");
    }

    private setOnNumChangeListener onNumChangeListener;

    public void setOnNumChangeListener(setOnNumChangeListener onNumChangeListener) {
        this.onNumChangeListener = onNumChangeListener;
    }

    public interface setOnNumChangeListener {
        void onNumChangeListener(int num);
    }
}
