package selfview.sufutian.com.myapplication.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import selfview.sufutian.com.myapplication.R;

/**
 * Created by sufutian on 2017/1/5.
 */

public class CircleDraw extends View {

    private Paint mPaint;

    public CircleDraw(Context context) {
        super(context);

    }

    public CircleDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public CircleDraw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 4, getHeight() / 2, getHeight()/2-10, mPaint);

        canvas.drawArc(getWidth()/2+getWidth()/8,0,getWidth()/2+getWidth()/8+getHeight(),getHeight(),
                90,270,false,mPaint);

        RectF rectF=new RectF(0,0,getHeight(),getHeight());
        canvas.drawOval(rectF,mPaint);

        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(40);
        canvas.drawText("hello",getHeight(),getHeight()/2,mPaint);

//        canvas.drawVertices();

        Drawable drawable=getResources().getDrawable(R.drawable.image);
        BitmapDrawable bitmapDrawable= (BitmapDrawable) drawable;
        Bitmap bitmap = bitmapDrawable.getBitmap();


        canvas.drawBitmap(bitmap,0,0,mPaint);

    }
}
