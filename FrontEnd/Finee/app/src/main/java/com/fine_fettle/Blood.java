package com.fine_fettle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Priyadharshini on 02-Jul-18.
 */

public class Blood extends AppCompatActivity {
    Button breq,bview;
    TextView tvView,tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood);
        breq=findViewById(R.id.bloodreq);
        bview=findViewById(R.id.bloodview);
        tvView =findViewById(R.id.tvView);
        tv=findViewById(R.id.tvView1);
        Intent intent = getIntent();
        final String username = intent.getStringExtra("user");
        final String id=intent.getStringExtra("id");
        tvView.setText(username);
        tv.setText(id);
        breq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Blood.this, BloodRequest.class);
                intent.putExtra("user", tvView.getText().toString());
                intent.putExtra("id", tv.getText().toString());
                startActivity(intent);
            }
        });

        bview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Blood.this, BloodView.class);
                intent.putExtra("user", tvView.getText().toString());
                intent.putExtra("id", tv.getText().toString());
                startActivity(intent);
            }
        });
    }
}
