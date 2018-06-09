package com.example.hp.mn1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hp on 2018/6/9.
 */

public class FlowLayout extends ViewGroup{
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量所有孩子的宽高
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        //测量最大宽度，也就是父控件的宽度
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);
        int widthsize = MeasureSpec.getSize(widthMeasureSpec);
        int heightmode = MeasureSpec.getMode(heightMeasureSpec);
        int heightsize = MeasureSpec.getSize(heightMeasureSpec);
        //定义宽高变量
        int width=0;
        int height=0;
        int lineWidth=0;
        int lineHeight=0;
        int totalHeight=0;
        View childView;
        int childWith=0;
        int childHeight=0;

        for (int i = 0; i < getChildCount(); i++) {
            childView=getChildAt(i);
            childWith=childView.getMeasuredWidth();
            childHeight = childView.getMeasuredHeight();
            if (lineWidth+childWith>widthsize){
                //换行  width就是最大宽,只要有换行的情况出现，说明流式布局的宽度已经不够用了
                width=widthsize;
                //totalHeight不包含最后一行高度
                int preLineHeight = lineHeight;
                totalHeight+=preLineHeight;
                lineHeight=childHeight;
                lineWidth=childWith;
            }else {
                //不换行
                lineWidth+=childWith;
                //当前行的高度
                lineHeight=Math.max(lineHeight,childHeight);
                //如果只有一行的时候,那测量的宽度就是当前行宽,如果有换行那就去最大宽
                width=Math.max(width,childWith);
            }
            //结束遍历时要加上最后一行的高度
            if(i==getChildCount()-1){
                height=totalHeight+lineHeight;
            }
        }
        width=widthmode==MeasureSpec.EXACTLY ?widthsize:width;
        height=heightmode==MeasureSpec.EXACTLY?heightsize:height;

        //确定最后的宽高
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int lineWidth = 0;
        int lineHeight = 0;
        int totalHeight = 0;
        View childView;
        int childWidth = 0;
        int childHeight = 0;

        for (int i = 0; i < getChildCount(); i++) {
            childView = getChildAt(i);
            childWidth = childView.getMeasuredWidth();
            childHeight = childView.getMeasuredHeight();
            if (i %2==0) {
                //换行
                //totalHeight不包含最后一行高度
                totalHeight += childHeight;
                lineWidth = 0;
                layoutChildView(childView, lineWidth, totalHeight, lineWidth + childWidth, totalHeight + childHeight);
                //换行width就是最大宽
              // lineHeight = childHeight;
               //lineWidth = childWidth;
            } else {
                //不换行
                totalHeight += childHeight;
                lineWidth += childWidth;
                layoutChildView(childView, lineWidth, totalHeight, lineWidth + childWidth, totalHeight + childHeight);
                //lineWidth += childWidth;
                //当前行的高度
               // lineHeight = Math.max(lineHeight, childHeight);
            }
        }
    }
    public void layoutChildView(View child, int l, int t, int r, int b) {
        child.layout(l, t, r, b);
    }

}
