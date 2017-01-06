package selfview.sufutian.com.myapplication.draw2d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by sufutian on 2017/1/5.
 * RectF
 * drawArc
 * drawCircle
 */

public class TaiJiView extends View {
    private String TAG="sufutian==";
    public TaiJiView(Context context) {
        super(context);
    }

    public TaiJiView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TaiJiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Paint mPaint = new Paint();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        Log.i(TAG, "width: "+width);
        Log.i(TAG, "height: "+height);

        //绘制最外层大圆
        mPaint.setColor(Color.BLACK);//设置画笔颜色为黑色
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);//设置画笔style实心
        mPaint.setAntiAlias(true);//抗锯齿
        RectF rect = new RectF(0.0f,0.0f, getWidth(),getHeight());//圆弧的外接矩形
        canvas.drawArc(rect, 270, 180, false, mPaint);//270度开始,180画半圆
        mPaint.setColor(Color.WHITE);//设置画笔颜色为白色
        canvas.drawArc(rect, 90, 180, false, mPaint);
        //绘制中间层上边圆
        mPaint.setColor(Color.BLACK);
        rect = new RectF(getWidth() / 2 - getHeight() / 4, 0, getWidth() / 2 + getHeight() / 4, getHeight() / 2);
        canvas.drawArc(rect, 90, 180, false, mPaint);
        //绘制中间层下边圆
        mPaint.setColor(Color.WHITE);
        rect = new RectF(getWidth() / 2 - getHeight() / 4, getHeight() / 2, getWidth() / 2 + getHeight() / 4, getHeight());
        canvas.drawArc(rect, 270, 180, false, mPaint);
        //绘制最上层白色小圆
        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(getWidth() / 2, getHeight() / 4, getHeight() / 10, mPaint);
        //绘制最上层黑色小圆
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(getWidth() / 2, getHeight() * 3 / 4, getHeight() / 10, mPaint);
    }
}
