package dev.den.mycountdowntimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import java.util.ArrayList;
import java.util.List;

public class ChronometerActivity extends AppCompatActivity {
    private Chronometer myChronometer;
    private RecyclerView myRecyclerView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private Button buttonPlay, buttonStop, buttonRefresh;
    private LinearLayoutManager linearLayoutManager;
    private List<MyTime> timeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myChronometer = findViewById(R.id.my_chronometer);
        myRecyclerView = findViewById(R.id.recyclerView_list);
        buttonPlay = findViewById(R.id.button_play);
        buttonStop = findViewById(R.id.button_stop);
        buttonRefresh = findViewById(R.id.button_refresh);

        buttonStop.setEnabled(false);
        buttonRefresh.setEnabled(false);

        timeList = new ArrayList<>();


        initRecyclerView();

    }


    public void initRecyclerView() {

        myRecyclerViewAdapter = new MyRecyclerViewAdapter(timeList);
        linearLayoutManager = new LinearLayoutManager(this);

        myRecyclerView.setAdapter(myRecyclerViewAdapter);
        myRecyclerView.setLayoutManager(linearLayoutManager);

    }

    private void showElapsedTime() {
        float elapsedMillis = (float) ((SystemClock.elapsedRealtime() - myChronometer.getBase()));
        timeList.add(new MyTime("Last time: " + elapsedMillis/1000));
        myRecyclerViewAdapter.notifyDataSetChanged();
    }


    public void buttonClick(View view) {
        switch (view.getId()) {
            case R.id.button_play:
                buttonPlay.setEnabled(false);
                buttonStop.setEnabled(true);
                buttonRefresh.setEnabled(true);
                myChronometer.setBase(SystemClock.elapsedRealtime());
                myChronometer.start();
                break;
            case R.id.button_stop:
                buttonPlay.setEnabled(true);
                buttonStop.setEnabled(false);
                buttonRefresh.setEnabled(false);
                myChronometer.stop();
                showElapsedTime();
                break;
            case R.id.button_refresh:
                buttonPlay.setEnabled(false);
                showElapsedTime();
                myChronometer.setBase(SystemClock.elapsedRealtime());
                break;
        }
    }
}