package com.uwm_iui.uwm_iui_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login_screen extends AppCompatActivity {

    public Button button_login;
    public Button button_signup;
    Database_Helper helper = new Database_Helper(this);

    public void init() {

        button_login = (Button)findViewById(R.id.button_login);
        button_signup = (Button)findViewById(R.id.button_signup);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            //this is grabbing the username field and converting it to a string for the Home screen
                EditText un = (EditText)findViewById(R.id.TFusername);
                String str = un.getText().toString();

                //this is grabbing the password field and converting it to a string for the Home screen
                EditText pw = (EditText)findViewById(R.id.TFpassword);
                String pw_str = pw.getText().toString();

                //Making sure that the Username and Password exist in the Database
                String password = helper.searchPass(str);
                if(pw_str.equals(password))
                {

                    //This is setting the login in button to bring you to the home screen
                    Intent new_activity_home = new Intent(login_screen.this, HomeScreen.class);
                    new_activity_home.putExtra("Username", str);

                    startActivity(new_activity_home);


                }
                else
                {
                    //pop-up msg that the passwords do not match
                    Toast passError = Toast.makeText(login_screen.this, "Username and Password do not match!", Toast.LENGTH_SHORT);
                    passError.show();


                }

            }
        });

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //This is setting the login in button to bring you to the sign-up screen
                Intent new_activity_signup = new Intent(login_screen.this, signup.class);

                startActivity(new_activity_signup);


            }
        });







    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        init();


    }
}
