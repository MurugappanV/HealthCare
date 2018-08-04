package com.fine_fettle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Priyadharshini on 03-Jun-18.
 */

public class Personhome extends AppCompatActivity{
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13;
    String username, id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personhome);
        Intent intent = getIntent();
        username = intent.getStringExtra("user");
        id =intent.getStringExtra("id");

        b1 =  findViewById(R.id.b_p_info);
        b2 =findViewById(R.id.b_p_hospital);
        b3 =  findViewById(R.id.b_p_lab);
        b4 = findViewById(R.id.b_p_appointment);
        b5 =  findViewById(R.id.b_p_chat);
        b6 = findViewById(R.id.b_p_order);
        b7 = findViewById(R.id.b_p_ambulance);
        b8 = findViewById(R.id.b_p_bloodbank);
        b9 = findViewById(R.id.b_p_need);
        b10 = findViewById(R.id.b_p_bmi);
        b11 = findViewById(R.id.b_p_health);
        b12 = findViewById(R.id.b_p_tips);
        b13 = findViewById(R.id.b_p_logout);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(Personhome.this, PersonInfo.class);
                    intent.putExtra("user",username );
                    startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Personhome.this,MapsActivity.class );
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Personhome.this, Laboratory.class);
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Personhome.this,Appointment.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Personhome.this, DoctorChat.class);
                startActivity(intent);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Personhome.this, MedicineOreder.class);
                startActivity(intent);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Personhome.this, Ambulance.class);
                startActivity(intent);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Personhome.this, Blood.class);
                intent.putExtra("user", username);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Personhome.this, Need.class);
                startActivity(intent);
            }
        });

        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Personhome.this, BMI.class);
                startActivity(intent);
            }
        });

        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Personhome.this, Health.class);
                intent.putExtra("user", tvView.getText().toString());
                intent.putExtra("id", tv.getText().toString());
                startActivity(intent);
            }
        });

        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Personhome.this, Tips.class);
                startActivity(intent);
            }
        });

        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("LoginInfo", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(Personhome.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
