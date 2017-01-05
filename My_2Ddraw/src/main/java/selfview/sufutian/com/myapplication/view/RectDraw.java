package selfview.sufutian.com.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sufutian on 2017/1/5.
 */

public class RectDraw extends View {

    private Paint mPaint;

    public RectDraw(Context context) {
        super(context);
    }

    public RectDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);//大小
    }

    public RectDraw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF=new RectF(0,0,getWidth()/4,getHeight());
        canvas.drawRect(rectF,mPaint);

        RectF rectF2=new RectF(getWidth()/4,0,getWidth()/2,getHeight());
        canvas.drawRoundRect(rectF2,100,100,mPaint);
    }
}
