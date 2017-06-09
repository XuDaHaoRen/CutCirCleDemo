package com.xbl.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import my.xbl.com.cutcircledemo.R;

/**
 * Created by April on 2017/6/9.
 * 山寨一个TextView效果
 */

public class MyTextView extends View {
    private String text="";
    private float textSize=14;//单位sp
    private int textColor= Color.BLACK;
    private Paint mPaint;
    //增
    private int rote=3;
    public MyTextView(Context context) {
        super(context);
        initPaint();
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //获取自定义属性的值
        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        text=ta.getString(R.styleable.MyTextView_text);
        textColor=ta.getColor(R.styleable.MyTextView_textColor,textColor);
        textSize=ta.getDimension(R.styleable.MyTextView_textSize,textSize);
        //attrs里面包含所有控件的属性，是一个庞大的类，所以如果在不用的时候将其回收
        ta.recycle();
        initPaint();
    }
    private void initPaint(){
        mPaint=new Paint();
        mPaint.setColor(textColor);
        mPaint.setTextSize(textSize);
        mPaint.setStrokeWidth(20);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //测量 文字大小
        Rect rect=new Rect();
        mPaint.getTextBounds(text,0,text.length(),rect);
        int h=rect.height();
        canvas.drawText(text,10,h,mPaint);
    }
    //当我们的宽高为warp时如果为这个控件设置背景，背景颜色会占据整个布局

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //如果宽高是warp的时候才需要去测量，然后根据实际内容分配高度
        //精准测量模式，
        //前2位测量模式  后30位为测量值
        //获取父亲的测量结果
        int wMode=MeasureSpec.getMode(widthMeasureSpec);
        int wValue=MeasureSpec.getSize(widthMeasureSpec);
        //获取父亲的测量结果
        int hMode=MeasureSpec.getMode(widthMeasureSpec);
        int hValue=MeasureSpec.getSize(widthMeasureSpec);

        //自己测量内容的宽高
        Rect rect=new Rect();
        mPaint.getTextBounds(text,0,text.length(),rect);
        //为背颜色和文字增加间距
        int w=rect.width()+rote*2;
        int h=rect.height()+rote*4;
                if(wMode==MeasureSpec.AT_MOST){
                    wValue=w;
                }
                if (hMode==MeasureSpec.AT_MOST){
                    hValue=h;

                }
                //重新设置宽和高，也
        setMeasuredDimension(wValue,hValue);

    }
}
