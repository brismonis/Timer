package bair_susanne.uniulm.timer;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TimerView extends AppCompatActivity implements View.OnClickListener {

    private Button buttonStartTime, buttonStopTime, save;
    private EditText edtTimerValue1, edtTimerValue2, edtTimerValue3, editText;
    private TextView textViewShowTime; // will show the time
    private CountDownTimer countDownTimer; // built in android class
    // CountDownTimer
    private long totalTimeCountInMilliseconds; // total count down time in
    // milliseconds
    private long timeBlinkInMilliseconds; // start time of start blinking
    private boolean blink; // controls the blinking .. on and off


    long remainingtime;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_view);

        buttonStartTime = (Button) findViewById(R.id.start);
        buttonStopTime = (Button) findViewById(R.id.stop);
        textViewShowTime = (TextView) findViewById(R.id.tvTimeCount);
        edtTimerValue1 = (EditText) findViewById(R.id.edtTimerValue1);
        edtTimerValue2 = (EditText) findViewById(R.id.edtTimerValue2);
        edtTimerValue3 = (EditText) findViewById(R.id.edtTimerValue3);

        buttonStartTime.setOnClickListener(this);
        buttonStopTime.setOnClickListener(this);

        //String item = this.getTitle().toString();
        //intent.putExtra("key", item);
        //String test = getIntent().getExtras().getString("title");

        //SAVE NAME TO LISTITEM
        editText = (EditText) findViewById(R.id.name);
        save = (Button) findViewById(R.id.save);
        /*save.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View arg0) {
                        //EditText edit = (EditText) findViewById(R.id.tskname);
                        Intent i = new Intent(this, MainActivity.class);
                        //Bundle bundle = new Bundle();
                        String title = editText.getText().toString();
                        //bundle.putString("NewTask", TaskName);
                        i.putExtra("name", title);
                        //i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        //i.putExtras(bundle);
                        startActivity(i);
                    }
                });*/

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start) {
            textViewShowTime.setTextAppearance(getApplicationContext(),
                    R.style.AppTheme);
            setTimer();
            buttonStopTime.setVisibility(View.VISIBLE);
            buttonStartTime.setVisibility(View.GONE);
            edtTimerValue1.setVisibility(View.GONE);
            edtTimerValue1.setText("");
            edtTimerValue2.setVisibility(View.GONE);
            edtTimerValue2.setText("");
            edtTimerValue3.setVisibility(View.GONE);
            edtTimerValue3.setText("");

            startTimer();

        } else if (v.getId() == R.id.stop) {
            countDownTimer.cancel();
            buttonStartTime.setVisibility(View.VISIBLE);
            buttonStopTime.setVisibility(View.GONE);
            edtTimerValue1.setVisibility(View.VISIBLE);
            edtTimerValue2.setVisibility(View.VISIBLE);
            edtTimerValue3.setVisibility(View.VISIBLE);
        }
    }

    private void setTimer() {
        int time = 0;
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        if (!(edtTimerValue1.getText().toString().equals("") && edtTimerValue2.getText().toString().equals("") && edtTimerValue3.getText().toString().equals(""))) {
                hours = Integer.parseInt(edtTimerValue1.getText().toString());
                minutes = Integer.parseInt(edtTimerValue2.getText().toString());
                seconds = Integer.parseInt(edtTimerValue3.getText().toString());
            //time = Integer.parseInt(edtTimerValue.getText().toString());
        } else
            Toast.makeText(this, "Please Enter Time",
                    Toast.LENGTH_LONG).show();

        time = (hours * 60 * 60) + (minutes * 60) + seconds;
        totalTimeCountInMilliseconds = time * 1000;

        timeBlinkInMilliseconds = 30 * 1000;
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(totalTimeCountInMilliseconds, 500) {
            // 500 means, onTick function will be called at every 500
            // milliseconds

            @Override
            public void onTick(long leftTimeInMilliseconds) {
                //long seconds = leftTimeInMilliseconds / 1000;

                if (leftTimeInMilliseconds < timeBlinkInMilliseconds) {
                    textViewShowTime.setTextAppearance(getApplicationContext(),
                            R.style.AppTheme);
                    // change the style of the textview .. giving a red
                    // alert style

                    if (blink) {
                        textViewShowTime.setVisibility(View.VISIBLE);
                        // if blink is true, textview will be visible
                    } else {
                        textViewShowTime.setVisibility(View.INVISIBLE);
                    }

                    blink = !blink; // toggle the value of blink
                }

                /*textViewShowTime.setText(String.format("%02d", seconds / 60 / 60)
                        + ":" + String.format("%02d", seconds / 60)
                        + ":" + String.format("%02d", seconds % 60));*/
                // format the textview to show the easily readable format

                //textViewShowTime.setText(String.format("%02d:%02d:%02d", seconds / 3600,
                //        (seconds % 3600) / 60, (seconds % 60)));

                //textViewShowTime.setText(String.format("%02d:%02d:%02d", minutes / 60, durationMinutes % 60, 0));

                /*textViewShowTime.setText(""+String.format("%d:%d:%d",
                        TimeUnit.MILLISECONDS.toHours(leftTimeInMilliseconds),
                        TimeUnit.MILLISECONDS.toMinutes(leftTimeInMilliseconds),
                        TimeUnit.MILLISECONDS.toSeconds(leftTimeInMilliseconds) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                        toMinutes(leftTimeInMilliseconds))));*/

                int seconds = (int) (leftTimeInMilliseconds / 1000) % 60 ;
                int minutes = (int) ((leftTimeInMilliseconds / (1000*60)) % 60);
                int hours   = (int) ((leftTimeInMilliseconds / (1000*60*60)) % 24);
                textViewShowTime.setText(String.format("%d:%d:%d",hours,minutes,seconds));
                remainingtime = leftTimeInMilliseconds;


                //String.format(Locale.getDefault(), "Time Remaining %02d min: %02d sec",TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60);

            }

            @Override
            public void onFinish() {
                // this function will be called when the timecount is finished
                textViewShowTime.setText("Time up");
                textViewShowTime.setVisibility(View.VISIBLE);
                buttonStartTime.setVisibility(View.VISIBLE);
                buttonStopTime.setVisibility(View.GONE);
                edtTimerValue1.setVisibility(View.VISIBLE);
                edtTimerValue2.setVisibility(View.VISIBLE);
                edtTimerValue3.setVisibility(View.VISIBLE);
            }

        }.start();

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void changeName(View view) {
        //EditText editText = (EditText) findViewById(R.id.name);
        String title = editText.getText().toString();
        setTitle(title);
        //String test = getIntent().getExtras().getString("title");
        //test = title;
    }


    public void save(View view) {
        //Intent intent = new Intent(this, MainActivity.class);
        //String title = this.getTitle().toString();
        //intent.putExtra("title", title);
        //intent.putExtra("time", textViewShowTime.getText());


        Intent i = new Intent(TimerView.this, MainActivity.class);

        //Bundle timetransfer = new Bundle();
        //timetransfer.putLong("timer", remainingtime);
        //i.putExtras(timetransfer);
        i.putExtra("title", this.getTitle().toString());
        startActivityForResult(i, 0);
    }
}
