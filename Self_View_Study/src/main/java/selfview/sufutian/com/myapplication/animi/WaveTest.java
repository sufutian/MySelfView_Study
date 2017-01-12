package selfview.sufutian.com.myapplication.animi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sufutian on 2017/1/12.
 */

public class WaveTest extends View {

    private final DrawFilter drawFilter;
    private final Paint paint;
    private final float[] yArrays;
    private int height;
    private int width;

    public WaveTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        drawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        paint.setStyle(Paint.Style.FILL);
        yArrays = new float[1080];
            for (int i = 0; i < 1080; i++) {
                yArrays[i] = (float) (20*Math.sin(2 * Math.PI / width*i) + 200);
            }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.setDrawFilter(drawFilter);
        for (int i = 0; i < 1080; i++) {
            canvas.drawLine(i,height-yArrays[i],i,height,paint);
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = h;
        width = w;
    }
}
