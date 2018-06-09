package com.example.hp.mn1;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by hp on 2018/6/9.
 */

public class MyTitleView extends LinearLayout implements View.OnClickListener {

    public Button btn_left,btn_center,btn_right;
    public IncanInterFace incanInterFace;

    public MyTitleView(Context context) {
        super(context);
    }

    public MyTitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = inflate(context, R.layout.title_main, this);
        btn_left = view.findViewById(R.id.btn_left);
        btn_center = view.findViewById(R.id.btn_center);
        btn_right = view.findViewById(R.id.btn_right);
        btn_left.setOnClickListener(this);
        btn_center.setOnClickListener(this);
        btn_right.setOnClickListener(this);


        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyTitleView, 0, 0);
        String btnleft = typedArray.getString(R.styleable.MyTitleView_btn_left);
        String btncenter = typedArray.getString(R.styleable.MyTitleView_btn_center);
        String btnright = typedArray.getString(R.styleable.MyTitleView_btn_right);
        float btnsize = typedArray.getDimension(R.styleable.MyTitleView_btn_size, 40);

        btn_left.setText(btnleft);
        btn_left.setTextSize(btnsize);
        btn_center.setText(btncenter);
        btn_center.setTextSize(btnsize);
        btn_right.setText(btnright);
        btn_right.setTextSize(btnsize);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_left:
                  if (incanInterFace!=null){
                      incanInterFace.getLiftBtn();
                  }
                break;
            case R.id.btn_center:
                if (incanInterFace!=null){
                    incanInterFace.getCenterBtn();
                }
                break;
            case R.id.btn_right:
                if (incanInterFace!=null){
                    incanInterFace.getRightBtn();
                }
                break;
        }
    }

    public interface IncanInterFace {
        void getLiftBtn();
        void getCenterBtn();
        void getRightBtn();
    }

    public void  getIncanInterFace(IncanInterFace incanInterFace) {
       this.incanInterFace=incanInterFace;
    }
}
