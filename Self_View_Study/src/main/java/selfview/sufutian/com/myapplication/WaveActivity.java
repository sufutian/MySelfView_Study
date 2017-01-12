package selfview.sufutian.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by sufutian on 2017/1/12.
 */

public class WaveActivity extends Activity {


    public static void toActivity(Activity activity) {
        Intent intent = new Intent(activity, WaveActivity.class);
        activity.startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave);
    }


}
