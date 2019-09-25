package com.lixiaodaoaaa.view.pieview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.gcssloop.graphics.R;
import com.lixiaodaoaaa.uitls.DensityUtils;

public class BarColumn extends View {
    int MAX = 100;//最大
    int corner = 40;
    int data = 0;//显示的数
    int tempData = 0;
    int textPadding = 20;
    Paint mPaint;
    int mColor;

    Context mContext;

    public BarColumn(Context context) {
        super(context);
        mContext = context;
    }

    public BarColumn(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initPaint();
    }

    public BarColumn(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mColor = mContext.getResources().getColor(R.color.colorPrimary);
        mPaint.setColor(mColor);
    }
    
    //设置颜色
    public  void setmPaintColor(int  mColor){
        mPaint.setColor(mColor);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        mPaint.setColor(mColor);
        if (data == 0) {
            mPaint.setTextSize(getWidth() / 2);
            RectF oval3 = new RectF(0, getHeight() - DensityUtils.pxTodip(mContext, 20), getWidth(), getHeight());// 设置个新的长方形
            canvas.drawRoundRect(oval3, DensityUtils.pxTodip(mContext, corner), DensityUtils.pxTodip(mContext, corner), mPaint);
            return;
        }

        //防止数值很大的的时候，动画时间过长
        int step = data / 100 + 1;

        if (tempData < data - step) {
            tempData = tempData + step;
        } else {
            tempData = data;
        }
        //画圆角矩形
        float textH = mPaint.ascent() + mPaint.descent();
        float MaxH = getHeight() - textH - 2 * DensityUtils.pxTodip(mContext, textPadding);
        //圆角矩形的实际高度
        float realH = MaxH / MAX * tempData;
        RectF oval3 = new RectF(0, getHeight() - realH, getWidth(), getHeight() );// 设置个新的长方形
        canvas.clipRect(0, getHeight() - realH, getWidth(), getHeight()  - 10);  // 必须先设置显示区域再绘制
        canvas.drawRoundRect(oval3, DensityUtils.pxTodip(mContext, corner), DensityUtils.pxTodip(mContext, corner), mPaint);
        if (tempData != data) {
            postInvalidate();
        }
    }

    public void setData(int data, int MAX,int  mColor) {
        this.mColor =mColor;
        this.data = data;
        tempData = 0;
        this.MAX = MAX;
        postInvalidate();
    }

}
