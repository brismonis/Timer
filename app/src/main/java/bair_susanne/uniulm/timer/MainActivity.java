package bair_susanne.uniulm.timer;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //List of Array Strings which will serve as List Items
    final ArrayList<String> listItems=new ArrayList<String>();

    //Defining a String Adapter which will handle the Data of the ListView
    ArrayAdapter<String> adapter;

    int timerCounter = 1;

    Button btn;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list);
        btn = (Button) findViewById(R.id.addBtn);

        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        list.setAdapter(adapter);

        final String[] listTime = new String[1];

        /*Bundle timetransfer = getIntent().getExtras();
        long time =timetransfer.getLong("timer");
        new CountDownTimer(time, 500) {

            public void onTick(long time) {
                int seconds = (int) (time / 1000) % 60 ;
                int minutes = (int) ((time / (1000*60)) % 60);
                int hours   = (int) ((time / (1000*60*60)) % 24);
                listTime[0] = String.format("%d:%d:%d",hours,minutes,seconds);

            }

            public void onFinish() {
            }

        }.start();*/

        Intent intent = getIntent();
        if (intent.hasExtra("title")) {
            String title = this.getIntent().getExtras().getString("title");
            //String time = this.getIntent().getExtras().getString("time");
            listItems.add(title);
            adapter.notifyDataSetChanged();
        }


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                if(position == 0) {
                    //Intent intent = getIntent();
                    Intent intent = new Intent(view.getContext(), TimerView.class);
                    //String title = intent.getExtras().getString("title");
                    //String time = intent.getExtras().getString("time");
                    startActivityForResult(intent, 0);
                    //Intent intent = new Intent(view.getContext(), TimerView.class);
                    //startActivity(intent);
                    //listItems.add(getIntent().getExtras().getString("restaurent"));
                }
                if(position == 1) {
                    Intent intent = new Intent(view.getContext(), TimerView.class);
                    startActivity(intent);
                }
                if(position == 2) {
                    Intent intent = new Intent(view.getContext(), TimerView.class);
                    startActivity(intent);
                }
            }
        });

        list.setLongClickable(true);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {
                listItems.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems(View v) {
        Intent intent = new Intent(this, TimerView.class);
        startActivity(intent);
        // listItems.add("Timer " + timerCounter++);
        //adapter.notifyDataSetChanged();
    }
}
