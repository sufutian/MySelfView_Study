package selfview.sufutian.com.myapplication.draw2d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.ImageView;

import selfview.sufutian.com.myapplication.R;

/**
 * Created by sufutian on 2017/1/13.
 */

public class ColorFilterView extends ImageView {
    float redFilter;
    float greenFilter;
    float blueFilter;
    float alphaFilter;
    private ColorMatrix mColorMatrix;
    private Paint paint;
    private Bitmap mBitmap;

    public ColorFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        initRes(context);
    }

    private void initRes(Context context) {
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.image_1);

        mBitmap = Bitmap.createScaledBitmap(mBitmap, 400, 400, false);
        // 创建位图渲染
        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        // 将位图渲染设置给paint
        paint.setShader(bitmapShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*canvas.drawBitmap(mBitmap, new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight()),
                new RectF(0, 0, mBitmap.getWidth(), mBitmap.getHeight()), paint);*/


        canvas.drawCircle(mBitmap.getWidth()/2, mBitmap.getWidth()/2, mBitmap.getWidth()/2, paint);


    }

    public void setArgb(float alpha, float red, float green, float blue) {
        redFilter = red;
        greenFilter = green;
        blueFilter = blue;
        alphaFilter = alpha;
        mColorMatrix = new ColorMatrix(new float[]{
                redFilter, 0, 0, 0, 0,
                0, greenFilter, 0, 0, 0,
                0, 0, blueFilter, 0, 0,
                0, 0, 0, alphaFilter, 0,
                /*0.22f, 0.5f, 0.1f, 0, 0,
                0.22f, 0.5f, 0.1f, 0, 0,
                0.22f, 0.5f, 0.1f, 0, 0,
                0, 0, 0, alphaFilter, 0,*/
        });
        paint.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
        postInvalidate();
    }
}
