package com.xbl.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import my.xbl.com.cutcircledemo.R;

/**
 * Created by April on 2017/6/9.
 * 类似于TextView的效果
 * 实现两个构造器
 * View给子类的属性  宽高，背景，选择，属性
 */

public class MyView extends View {
    //画笔
    private Paint mPaint;
    //方便代码的初始化
    public MyView(Context context) {
        super(context);
        initPaint();
    }
    //方便在XML文件中使用
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }
    //初始化画笔
    private void initPaint(){
        mPaint=new Paint();
        //设置画笔的颜色
        mPaint.setColor(Color.BLUE);
        //设置笔头粗细
        mPaint.setStrokeWidth(10);
        //文字的大小(以像素为单位)
        mPaint.setTextSize(50);
        //设置抗锯齿
        mPaint.setAntiAlias(true);
        //防抖动
        mPaint.setDither(true);
//        //绘制空心的画笔,绘制文字的时候不能用空心画笔
//        mPaint.setStyle(Paint.Style.STROKE);
    }
    //绘制组件的内容
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//     //   canvas.drawRect(10,10,210,80,mPaint);
//        //绘制椭圆，先创建矩形，绘制内切椭圆
//        RectF oval=new RectF(10,210,210,410);
//        canvas.drawOval(oval,mPaint);
//        //将资源转换成Bitmap
//        Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.kongqi);
//        //绘制图片,图片是不需要画笔的
//        canvas.drawBitmap(bitmap,10,10,null);

        //为了测量文字大小，直接将文字放置到一个矩形框中，直接设置为矩形的宽高
        String text="hello word";
        Rect rect=new Rect();
        //得到围绕这个文字的外接矩形
        mPaint.getTextBounds(text,0,text.length(),rect);
        int height=rect.height();
        //绘制文字,文字，初始位置，文字的高度，画笔
        canvas.drawText(text,0,height,mPaint);



    }
}
