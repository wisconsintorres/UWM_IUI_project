package com.uwm_iui.uwm_iui_project;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class HomeScreen extends AppCompatActivity {

    public Button button_begin;

    public void init() {

        button_begin = (Button)findViewById(R.id.button_begin);

        button_begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //setting the begin button to being you to the main activity

                Intent new_activity = new Intent(HomeScreen.this, MainActivity.class);

                startActivity(new_activity);

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        //getting the username from login_screen.java and displaying on Home
        String username = getIntent().getStringExtra("Username");
        TextView tv = (TextView)findViewById(R.id.TVusername);
        tv.setText(username);


        init();


    }
}
