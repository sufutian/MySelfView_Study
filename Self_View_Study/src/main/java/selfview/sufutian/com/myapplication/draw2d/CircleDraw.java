package selfview.sufutian.com.myapplication.draw2d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
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
                90,270,true,mPaint);

        RectF rectF=new RectF(0,0,getHeight(),getHeight());
        canvas.drawOval(rectF,mPaint);

        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(40);
        canvas.drawText("hello",getHeight(),getHeight()/2,mPaint);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image);
        canvas.drawBitmap(bitmap,
                new Rect(getWidth()/2,0,bitmap.getWidth(), bitmap.getHeight()),
                new Rect(getWidth()/2,0,getWidth()/2+100,getHeight()),mPaint
                );


        //通过绘制多边形绘制三角形
        canvas.drawVertices(Canvas.VertexMode.TRIANGLES, 6, new float[]{20,35,45,100,55,80}, 0, null, 0, null, 0, null, 0, 0, mPaint);

    }
}
