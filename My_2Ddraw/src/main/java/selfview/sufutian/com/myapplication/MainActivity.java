package selfview.sufutian.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView menuList;

    ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuList = (ListView) findViewById(R.id.listview);
        data.add("2D绘制001");
        menuAdappter menuAdappter = new menuAdappter();
        menuList.setAdapter(menuAdappter);
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
                holder.button = (Button) convertView.findViewById(R.id.item_text);
                convertView.setTag(holder);
            } else {
                ViewHolder holder = (ViewHolder) convertView.getTag();
            }
            holder.button.setText(data.get(position));
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,"第"+position+"个item",Toast.LENGTH_SHORT).show();
                }
            });
            
            return convertView;
        }


        private class ViewHolder {
            Button button;
        }
    }
}
