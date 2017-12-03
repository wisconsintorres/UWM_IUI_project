package com.uwm_iui.uwm_iui_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    public Button button_signup1;
    Database_Helper helper = new Database_Helper(this);

    public void init() {

        button_signup1 = (Button) findViewById(R.id.button_signup1);

        button_signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //this is grabbing the name,email,username,password field from signup.xml and converting it to a string
                EditText name = (EditText) findViewById(R.id.TFname);
                EditText email = (EditText) findViewById(R.id.TFemail);
                EditText uname = (EditText) findViewById(R.id.TFuname);
                EditText pass1 = (EditText) findViewById(R.id.TFpass1);
                EditText pass2 = (EditText) findViewById(R.id.TFpass2);

                String name_str = name.getText().toString();
                String email_str = email.getText().toString();
                String uname_str = uname.getText().toString();
                String pass1_str = pass1.getText().toString();
                String pass2_str = pass2.getText().toString();

                if(!pass1_str.equals(pass2_str))
                {
                    //pop-up msg that the passwords do not match
                    Toast pass = Toast.makeText(signup.this, "Passwords do not match!", Toast.LENGTH_SHORT);
                    pass.show();

                }
                else
                {

                    //insert the details in the database
                    Contact c = new Contact();
                    c.setName(name_str);
                    c.setEmail(email_str);
                    c.setUname(uname_str);
                    c.setPass(pass1_str);

                    helper.insertContact(c);

                    //message letting the user know that it worked
                    Toast sucess = Toast.makeText(signup.this, "Success!", Toast.LENGTH_SHORT);
                    sucess.show();

                    //This is setting the login in button to bring you to the home screen
                    Intent new_activity_home = new Intent(signup.this, login_screen.class);
                    startActivity(new_activity_home);

                }


                //This is setting the login in button to bring you to the home screen
                //Intent new_activity_home = new Intent(signup.this, HomeScreen.class);
                //new_activity_home.putExtra("Username", str);

                //startActivity(new_activity_home);

            }
        });


    }


   //sets the layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
            init();

                }
}