package selfview.sufutian.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView menuListView;

    ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuListView = (ListView) findViewById(R.id.listview);
        data.add("2D绘制");
        data.add("动画相关");
        menuListView.setItemsCanFocus(true);
        menuAdappter menuAdappter = new menuAdappter();
        menuListView.setAdapter(menuAdappter);
    }

    private class menuAdappter extends BaseAdapter {

        private ViewHolder holder;

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.item_menu, null);
                holder = new ViewHolder();
                holder.textView = (TextView) convertView.findViewById(R.id.item_text);
                convertView.setTag(holder);
            } else {
                ViewHolder holder = (ViewHolder) convertView.getTag();
            }
            convertView.setFocusable(true);
            convertView.setClickable(true);
            holder.textView.setText(data.get(position));
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClick(position);
                }
            });
            convertView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus){
                        holder.textView.setBackgroundResource(R.drawable.item_focus_bg);
                    }else{
                        holder.textView.setBackgroundResource(R.color.trans);
                    }
                }
            });
//            itemClick(holder.textView, position);

            return convertView;
        }


        private class ViewHolder {
            TextView textView;
        }
    }

    public void itemClick(int position){
        Toast.makeText(MainActivity.this,"第"+position+"个item",Toast.LENGTH_SHORT).show();
        switch (position){
            case 0:
                My2dDrawActivity.toActivity(MainActivity.this);
                break;
            case 1:
                AnimationActivity.toActivity(MainActivity.this);
                break;
            case 2:

                break;
        }
    }

}
