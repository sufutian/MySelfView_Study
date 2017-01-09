package selfview.sufutian.com.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by sufutian on 2017/1/6.
 */

public class AnimationActivity extends Activity {

    /**
     * 动画插值器
     * AccelerateDecelerateInterpolator============动画开始与结束的地方速率改变比较慢，在中间的时候加速。
     * AccelerateInterpolator===================动画开始的地方速率改变比较慢，然后开始加速。
     * AnticipateInterpolator ==================开始的时候向后然后向前甩。
     * AnticipateOvershootInterpolator=============开始的时候向后然后向前甩一定值后返回最后的值。
     * BounceInterpolator=====================动画结束的时候弹起。
     * CycleInterpolator======================动画循环播放特定的次数，速率改变沿着正弦曲线。
     * DecelerateInterpolator===================在动画开始的地方快然后慢。
     * LinearInterpolator======================以常量速率改变。
     * OvershootInterpolator====================向前甩一定值后再回到原来位置。
     * PathInterpolator========================新增的，就是可以定义路径坐标，然后可以按照路径坐标来跑
     */

    @InjectView(R.id.view_animi)
    ImageView viewAnimi;
    @InjectView(R.id.view_animi_btn)
    Button viewAnimiBtn;
    @InjectView(R.id.zhen_animi)
    ImageView zhenAnimi;
    @InjectView(R.id.object_animi)
    ImageView objectAnimi;

    private AnimationDrawable zhenDrawable;

    public static void toActivity(Activity activity) {
        Intent intent = new Intent(activity, AnimationActivity.class);
        activity.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.inject(this);
    }


    public void zhenAnimation() {
        zhenDrawable = (AnimationDrawable) zhenAnimi.getBackground();
        zhenDrawable.setOneShot(false);
        zhenDrawable.start();
    }

    public void viewAnimation(View view) {
        AnimationSet set = new AnimationSet(false);
        //渐变
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);
        //旋转
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setFillAfter(true);
        //平移
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.7f,
                Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
        translateAnimation.setDuration(2000);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(new AnticipateInterpolator());
        translateAnimation.setFillEnabled(true);
        //缩放
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setFillAfter(true);

        set.addAnimation(alphaAnimation);
        set.addAnimation(rotateAnimation);
        set.addAnimation(translateAnimation);
        set.addAnimation(scaleAnimation);
        view.startAnimation(set);
    }


    /**
     * translationX,translationY           View相对于原始位置的偏移量
     * rotation,rotationX,rotationY       旋转，rotation用于2D旋转角度，3D中用到后两个
     * scaleX,scaleY                      缩放比
     * x,y                                View的最终坐标，是View的left，top位置加上translationX，translationY
     * alpha                              透明度
     */
    public void ObjectAnimi(View view) {


        AnimatorSet animationSet = new AnimatorSet();

        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        alpha.setDuration(2000);//设置动画时间
//        alpha.setInterpolator(new DecelerateInterpolator());//设置动画插入器，减速
        alpha.setRepeatCount(-1);//设置动画重复次数，这里-1代表无限
        alpha.setRepeatMode(ObjectAnimator.REVERSE);//设置动画循环模式。
//        alpha.start();//启动动画。

        ObjectAnimator translation=ObjectAnimator.ofFloat(view,"translationX",0.0f,-1080+400.0f);
        translation.setDuration(2000);
        translation.setRepeatCount(-1);
        translation.setRepeatMode(ObjectAnimator.REVERSE);

        animationSet.play(translation).with(alpha);
        animationSet.start();






    }

    boolean isStart;

    @OnClick({R.id.view_animi_btn, R.id.view_animi, R.id.zhen_animi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_animi_btn:
                viewAnimation(viewAnimi);
                zhenAnimation();
                ObjectAnimi(objectAnimi);
                break;
            case R.id.view_animi:
                break;
            case R.id.zhen_animi:
                if (isStart) {
                    zhenDrawable.start();
                } else {
                    zhenDrawable.stop();
                }
                isStart = !isStart;
                break;
        }
    }
}
