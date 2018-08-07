package com.fine_fettle;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Crashactivity extends AppCompatActivity {
    public static String firstN,secondN;
    public int flag;
    public int setText = 0;
    public EditText edT1;
    public EditText edT2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //hjh
//                Intent i = new Intent();
//                i.setComponent(new ComponentName("com.android.contacts", "com.android.contacts.DialtactsContactsEntryActivity"));
//                i.setAction("android.intent.action.MAIN");
//                i.addCategory("android.intent.category.LAUNCHER");
//                i.addCategory("android.intent.category.DEFAULT");
//                Toast.makeText(getApplicationContext(),"Copy the particular contact's number",Toast.LENGTH_LONG).show();
//                startActivity(i);
//            }
//        });
        edT1 = (EditText) findViewById(R.id.firstNumber);
        edT2 = (EditText) findViewById(R.id.secondNumber);
        try {
            File myFile = new File("/sdcard/.emergencyNumbers.txt");
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(
                    new InputStreamReader(fIn));
            edT1.setText(myReader.readLine());
            edT2.setText(myReader.readLine());
            setText = 1;
            myReader.close();
        } catch (Exception e) {
            Toast.makeText(Crashactivity.this, "Please update your emergency contact numbers",
                    Toast.LENGTH_SHORT).show();
        }
        final ImageButton serviceB=(ImageButton)findViewById(R.id.serviceB);
        flag=1;
        serviceB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (flag == 1) {
//                    Toast.makeText(Crashactivity.this, "ACTIVATED!", Toast.LENGTH_LONG).show();
                if(setText == 1) {
                    startService(new Intent(getApplicationContext(), ShakeService.class));
                    Picasso.get()
                            .load(R.drawable.on)
                            .placeholder(R.drawable.on)
                            .error(R.drawable.on)
                            .into(serviceB);
                    flag = 0;

                } else {
                    Toast.makeText(Crashactivity.this, "Please update your emergency contact numbers",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
//                    Toast.makeText(Crashactivity.this, "DEACTIVATED!", Toast.LENGTH_LONG).show();
                stopService(new Intent(getApplicationContext(), ShakeService.class));
                Picasso.get()
                        .load(R.drawable.off)
                        .placeholder(R.drawable.off)
                        .error(R.drawable.off)
                        .into(serviceB);
                flag = 1;
            }

            }
        });
        Button doneB=(Button)findViewById(R.id.doneButton);
        if (doneB != null) {
            doneB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(edT1.getText()!=null)
                        firstN=edT1.getText().toString();
                    if(edT2.getText()!=null)
                        secondN=edT2.getText().toString();
                    try {
                        File myFile = new File("/sdcard/.emergencyNumbers.txt");
                        myFile.createNewFile();
                        FileOutputStream fOut = new FileOutputStream(myFile);
                        OutputStreamWriter myOutWriter =
                                new OutputStreamWriter(fOut);
                        myOutWriter.append(firstN);
                        myOutWriter.append("\n");
                        myOutWriter.append(secondN);
                        myOutWriter.close();
                        fOut.close();
                        Toast.makeText(getApplicationContext(),
                                "The emergency contact numbers have been saved.",
                                Toast.LENGTH_SHORT).show();
                        setText = 1;
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                    Log.d(getPackageName(), "Done! button pressed.");
                }
            });
        }


    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.aboutM) {
//            startActivity(new Intent(Crashactivity.this,About.class));
//        }
//        else if(id == R.id.close){
//            System.exit(1);
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
