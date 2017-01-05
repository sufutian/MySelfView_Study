package selfview.sufutian.com.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sufutian on 2017/1/5.
 */

public class LineDraw extends View {

    private Paint mPaint;

    public LineDraw(Context context) {
        super(context);
    }

    public LineDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(10);
    }

    public LineDraw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine(getWidth()/2,0,getWidth()/2,getHeight(),mPaint);

        canvas.drawLine(0,getHeight()/2,getWidth(),getHeight()/2,mPaint);

    }
}
