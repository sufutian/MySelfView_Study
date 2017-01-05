package selfview.sufutian.com.myapplication;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.InjectView;
import selfview.sufutian.com.myapplication.view.TaiJiView;

/**
 * Created by sufutian on 2017/1/5.
 */

public class TaiJiActivity extends Activity {


    @InjectView(R.id.taiji_view)
    TaiJiView taijiView;

    public static void toActivity(Activity activity) {
        Intent intent = new Intent(activity, TaiJiActivity.class);
        activity.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taiji_activity);
        ButterKnife.inject(this);
        rotaAnimi(taijiView);

    }

    public void rotaAnimi(View view){
        ObjectAnimator animi=ObjectAnimator.ofFloat(view,"rotation",0,360);
        animi.setDuration(1000);
        animi.start();

    }
}
