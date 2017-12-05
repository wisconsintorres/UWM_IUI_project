package com.uwm_iui.uwm_iui_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Joshua Torres on 12/5/2017.
 */

public class instructions extends AppCompatActivity {

    public Button OK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions);

    OK = (Button)findViewById(R.id.instructions);


        //button back to home
        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //This is setting the login in button to bring you to the sign-up screen
                Intent new_activity_home = new Intent(instructions.this, HomeScreen.class);

                startActivity(new_activity_home);


            }
        });



    }



}
