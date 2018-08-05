package com.fine_fettle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;

/**
 * Created by Priyadharshini on 05-Jun-18.
 */

public class Appointment extends AppCompatActivity {
    Button makeapp, viewapp, rescheduleapp, cancelapp, remainderapp;
    TextView tv;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        Intent intent = getIntent();
        id=intent.getStringExtra("id");
//        tv=findViewById(R.id.tvView);
//        tv.setText(id);
        makeapp = findViewById(R.id.make);
//        viewapp = findViewById(R.id.view);
//        rescheduleapp = findViewById(R.id.reschedule);
//        cancelapp = findViewById(R.id.cancel);
//        remainderapp = findViewById(R.id.remainder);


        makeapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Appointment.this, MakeAppointment.class);
                intent.putExtra("id", id);
                Toast.makeText(getApplicationContext(), "Id" + id,Toast.LENGTH_LONG).show();
                startActivity(intent);

            }
        });
//        viewapp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(Appointment.this, ViewAppointment.class);
//                intent.putExtra("id", tv.getText().toString());
//               Toast.makeText(getApplicationContext(), "Id" + id,
//                        Toast.LENGTH_LONG).show();
//                startActivity(intent);
//            }
//        });
//        rescheduleapp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(Appointment.this, RescheduleAppointment.class);
//                startActivity(intent);
//            }
//        });

    }
}

