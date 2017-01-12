package selfview.sufutian.com.myapplication.animi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import selfview.sufutian.com.myapplication.util.UiUtils;

/**
 * Created by sufutian on 2017/1/12.
 */

public class WaveView extends View {
    String TAG = "sufutian";
    // 波纹颜色
    private static final int WAVE_PAINT_COLOR = 0x880000aa;
    // y = Asin(wx+b)+h
    private static final float STRETCH_FACTOR_A = 20;
    private static final int OFFSET_Y = 0;
    // 第一条水波移动速度
    private static final int TRANSLATE_X_SPEED_ONE = 7;
    // 第二条水波移动速度
    private static final int TRANSLATE_X_SPEED_TWO = 5;
    private float mCycleFactorW;

    private int mWidth, mHeight;
    /**
     * 原始的点数组
     */
    private float[] mYArray;
    /**
     * 第一条点的数组
     */
    private float[] mOnePointArray;
    /**
     * 第二条点的数组
     */
    private float[] mTwoPointArray;
    private int mXOffsetSpeedOne;
    private int mXOffsetSpeedTwo;
    private int mXOneOffset;
    private int mXTwoOffset;

    private Paint mPaint;
    private DrawFilter mDrawFilter;


    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 将dp转化为px，用于控制不同分辨率上移动速度基本一致
        mXOffsetSpeedOne = UiUtils.dipToPx(context, TRANSLATE_X_SPEED_ONE);
        mXOffsetSpeedTwo = UiUtils.dipToPx(context, TRANSLATE_X_SPEED_TWO);

        // 初始绘制波纹的画笔
        mPaint = new Paint();
        // 去除画笔锯齿
        mPaint.setAntiAlias(true);
        // 设置风格为实线
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        // 设置画笔颜色
        mPaint.setColor(WAVE_PAINT_COLOR);
        mDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "w:---" + w);
        Log.e(TAG, "h:---" + h);

        // 记录下view的宽高
        mWidth = w;
        mHeight = h;
        // 用于保存原始波纹的y值
        mYArray = new float[mWidth];
        // 用于保存波纹一的y值
        mOnePointArray = new float[mWidth];
        // 用于保存波纹二的y值
        mTwoPointArray = new float[mWidth];

        // 将周期定为view总宽度
        mCycleFactorW = (float) (2 * Math.PI / mWidth);
        Log.e(TAG, "mCycleFactorW:---" + mCycleFactorW);

        // 根据view总宽度得出所有对应的y值
        for (int i = 0; i < mWidth; i++) {
            mYArray[i] = (float) (STRETCH_FACTOR_A * Math.sin(mCycleFactorW * i) + OFFSET_Y);
            Log.e(TAG, "mYArray[i]:---" + mYArray[i]);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**从canvas层面去除绘制时锯齿
         * 绘制时去锯齿
         */
        canvas.setDrawFilter(mDrawFilter);
        resetPositonY();
        for (int i = 0; i < mWidth; i++) {

            // 减400只是为了控制波纹绘制的y的在屏幕的位置，大家可以改成一个变量，然后动态改变这个变量，从而形成波纹上升下降效果
            // 绘制第一条水波纹
            canvas.drawLine(i, mHeight - mOnePointArray[i] - 300, i,
                    mHeight,
                    mPaint);

            // 绘制第二条水波纹
            canvas.drawLine(i, mHeight - mTwoPointArray[i] - 300, i,
                    mHeight,
                    mPaint);
        }

        // 改变两条波纹的移动点
        mXOneOffset += mXOffsetSpeedOne;
        mXTwoOffset += mXOffsetSpeedTwo;

        // 如果已经移动到结尾处，则重头记录
        if (mXOneOffset >= mWidth) {
            mXOneOffset = 0;
        }
        if (mXTwoOffset > mWidth) {
            mXTwoOffset = 0;
        }

        // 引发view重绘，一般可以考虑延迟20-30ms重绘，空出时间片
        postInvalidate();


    }

    private void resetPositonY() {
        // mXOneOffset代表当前第一条水波纹要移动的距离
        int yOneInterval = mYArray.length - mXOneOffset;
        // 使用System.arraycopy方式重新填充第一条波纹的数据
        /**
         * src:源数组；	    srcPos:源数组要复制的起始位置；
         * dest:目的数组；	destPos:目的数组放置的起始位置；	length:复制的长度。
         */
        System.arraycopy(mYArray, mXOneOffset, mOnePointArray, 0, yOneInterval);
        System.arraycopy(mYArray, 0, mOnePointArray, yOneInterval, mXOneOffset);

        int yTwoInterval = mYArray.length - mXTwoOffset;
        System.arraycopy(mYArray, mXTwoOffset, mTwoPointArray, 0, yTwoInterval);
        System.arraycopy(mYArray, 0, mTwoPointArray, yTwoInterval, mXTwoOffset);
    }
}
