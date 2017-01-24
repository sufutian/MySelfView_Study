package selfview.sufutian.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import selfview.sufutian.com.myapplication.draw2d.ColorFilterView;

/**
 * Created by sufutian on 2017/1/13.
 */

public class ColorFilterActivity extends Activity implements SeekBar.OnSeekBarChangeListener {

    @InjectView(R.id.color_image)
    ColorFilterView colorImage;
    @InjectView(R.id.red)
    SeekBar red;
    @InjectView(R.id.green)
    SeekBar green;
    @InjectView(R.id.blue)
    SeekBar blue;
    @InjectView(R.id.aplha)
    SeekBar aplha;


    float redFilter;
    float greenFilter;
    float blueFilter;
    float alphaFilter;

    public static void toActivity(Activity activity) {
        Intent intent = new Intent(activity, ColorFilterActivity.class);
        activity.startActivity(intent);

    }

    /**
     * setColorFilter(ColorFilter filter):设置颜色过滤器,可以通过颜色过滤器过滤掉对应的色值，比如去掉照片颜色，生成老照片效果；
     * <p>
     * ColorFilter有以下几个子类可用:
     * <p>
     * ColorMatrixColorFilter
     * <p>
     * LightingColorFilter
     * <p>
     * PorterDuffColorFilter
     * <p>
     * 修改图片 RGBA 的值需要ColorMatrix类的支持，它定义了一个 4*5 的float[]类型的矩阵，矩阵中每一行表示 RGBA 中的一个参数。
     * <p>
     * 颜色矩阵M是以一维数组m=[a,b,c,d,e,
     * f,g,h,i,j,
     * k,l,m,n,o,
     * p,q,r,s,t]的方式进行存储的；
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colorfilter);
        ButterKnife.inject(this);

        red.setOnSeekBarChangeListener(this);
        green.setOnSeekBarChangeListener(this);
        blue.setOnSeekBarChangeListener(this);
        aplha.setOnSeekBarChangeListener(this);
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        float filter = (float) progress / 100;
        if (seekBar == red) {
            redFilter = filter;
        } else if (seekBar == green) {
            greenFilter = filter;
        } else if (seekBar == blue) {
            blueFilter = filter;
        } else if (seekBar == aplha) {
            alphaFilter = filter;
        }
        colorImage.setArgb(alphaFilter,redFilter,greenFilter,blueFilter);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }




}
