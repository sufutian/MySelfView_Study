package selfview.sufutian.com.myapplication.draw2d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;

/**
 * Created by sufutian on 2017/1/5.
 * <p>
 * postInvalidate();
 */

public class PointDraw extends View {

    //画笔
    private Paint mPaint;
    //点间隔(步长)
    private int step = 15;
    //生成随机数
    private Random random;
    //points点数组
    private float[] mPoints;
    //是否第一次绘制
    private boolean isFrist = true;

    private String TAG = "sufutian==";

    public PointDraw(Context context) {
        super(context);
    }

    public PointDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(10);//大小
        random = new Random();
    }

    public PointDraw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPoint(0, 0, mPaint);

        synchronized (this) {
            if (isFrist) {
                //获取X轴上要画多少个点
                int xCount = getWidth() / step;
                Log.i(TAG, "xCount: " + xCount);
                //获取Y轴上要画多少个点
                int yCount = getHeight() / step;
                Log.i(TAG, "yCount: " + yCount);
                //初始化数组，一个点要两个值(x,y)
                mPoints = new float[xCount * yCount * 2];
                /*mPoints = new float[12];
                mPoints[0] = 0;//x
                mPoints[1] = 0;//y
                mPoints[2] = 20;
                mPoints[3] = 0;
                mPoints[4] = 40;
                mPoints[5] = 0;
                mPoints[6] = 60;
                mPoints[7] = 0;
                mPoints[8] = 80;
                mPoints[9] = 0;
                mPoints[10]=600;
                mPoints[11]=0;*/
                //填充数组
                for (int j = 0; j < yCount; j++) {
                    for (int i = 0; i < xCount * 2; i++) {
                        if (i % 2 == 0) {//绘制横坐标
                            mPoints[j * xCount * 2 + i] = i / 2 * step;
                        } else {//绘制纵坐标
                            mPoints[j * xCount * 2 + i] = j * step;
                        }
                        Log.i(TAG, "mPoints: " + (j * xCount * 2 + i) + "--" + mPoints[j * xCount * 2 + i]);
                    }
                    isFrist = !isFrist;
                }
            }
            mPaint.setARGB(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));

            //用给出的坐标点数组画出所有的点
            canvas.drawPoints(mPoints, mPaint);
            //从第1001个数值开始取1000个数值，相当于画500个点
            mPaint.setARGB(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));
            canvas.drawPoints(mPoints, random.nextInt(2000), 500, mPaint);
            //延迟1秒刷新界面
            postInvalidateDelayed(500);

        }

    }

}
