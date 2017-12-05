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
    public Button button_instructions;
    public  Button button_catalog;

    public void init() {

        button_begin = (Button)findViewById(R.id.button_begin);
        button_instructions = (Button)findViewById(R.id.button_instructions);
        button_catalog = (Button)findViewById(R.id.button_catalog);


        button_begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //setting the begin button to being you to the main activity

                Intent new_activity = new Intent(HomeScreen.this, MainActivity.class);

                startActivity(new_activity);

            }
        });

        button_catalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //setting the begin button to being you to the main activity

                Intent new_activity = new Intent(HomeScreen.this, dataview.class);

                startActivity(new_activity);

            }
        });

        //button to instructions
        button_instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //This is setting the login in button to bring you to the sign-up screen
                Intent new_activity_step2 = new Intent(HomeScreen.this, instructions.class);


                startActivity(new_activity_step2);


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
