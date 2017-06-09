package com.xbl.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.Image;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import my.xbl.com.cutcircledemo.R;

/**
 * Created by April on 2017/6/9.
 * 圆形头像View
 */

public class MyCircleImageView extends View {
    private int srcImage;

    //方便代码的初始化
    public MyCircleImageView(Context context) {
        super(context);
    }

    //方便在XML文件中使用
    public MyCircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //得到所有属性的集合
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyCircleImageView);
        //得到src属性所附的值
        srcImage = ta.getResourceId(R.styleable.MyCircleImageView_src, R.mipmap.ic_launcher);
        //因为ta中有很多数据，所以回收数据
        ta.recycle();
    }

    //获取圆形的图片
    private Bitmap getCircleImage() {
        //绘制的图片内容
        Bitmap srcBitmap = BitmapFactory.decodeResource(getResources(), srcImage);
        int w = srcBitmap.getWidth();
        int h = srcBitmap.getHeight();
        //desc层，设置最终要的形状
        //根据原图的宽高创建一个bitmap图片
        // RGB.565最小的内存保存，ARGB_4444，8888更加清楚
        Bitmap descBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_4444);
        //绘制画布形状
        RectF rectF = new RectF(0, 0, w, h);
        //创建内切椭圆
        //得到最底层desc的画布
        Canvas descCanvas = new Canvas(descBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        //抗锯齿和防抖动
        paint.setAntiAlias(true);
        paint.setDither(true);
        descCanvas.drawOval(rectF, paint);
        //设置显示取相交部分
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //将内容绘制在形状上
        descCanvas.drawBitmap(srcBitmap, 0, 0, paint);
        return descBitmap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(getCircleImage(), 0, 0, null);
    }
    //解决wramp_content不显示问题

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取父亲的测量结果（widthMeasureSpec 是一个32位的int值，
        // 其中高2位为测量的模式，低30位为测量的大小，模式常用的为两种
        // EXACTLY 精确模式   宽高为一个精确值  xxdp或者match_parent
        //AT_MOST  最大值   宽高为wrap_content
        // ）
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        //实际图片宽高
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), srcImage);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        //如果是自适应，则手动为控件设置宽高
        if (wMode == MeasureSpec.AT_MOST) {
            width = w;
        }
        if (hMode == MeasureSpec.AT_MOST) {
            height = h;
        }
        //为控件设置宽高属性，宽高为图片的宽高
        setMeasuredDimension(width, height);
    }
}
