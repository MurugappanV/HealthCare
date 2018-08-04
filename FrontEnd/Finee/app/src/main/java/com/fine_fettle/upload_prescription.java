package com.fine_fettle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Calendar;

public class upload_prescription extends AppCompatActivity{
    EditText hos,hos1,hos2,hos3,hos4,date,date1,date2,date3,date4,med,med1,med2,med3,med4;
    Button upload;
    private ProgressBar progressBar;
    private Calendar newCalendar;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makeapp);
        tv = findViewById(R.id.tvView1);
        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        System.out.println(id);
        tv.setText(id);
    }
}
