package com.fine_fettle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Murugappan V on 8/10/2018.
 */

public class AmbulanceRequest extends AppCompatActivity {

    Button b;
    String username, id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amb_req);

        Intent intent = getIntent();
        username = intent.getStringExtra("user");
        id =intent.getStringExtra("id");

        b = findViewById(R.id.btn_request);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AmbulanceRequest.this, Ambulance.class);
                intent.putExtra("id",id );
                startActivity(intent);
            }
        });
    }
}
