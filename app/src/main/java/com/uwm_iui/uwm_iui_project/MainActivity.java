package com.uwm_iui.uwm_iui_project;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText txtSpeechInput;
    //private ImageButton btnSpeak;
    private ImageView btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    public Button step1_next;
    public Button step1_back;
    Database_Helper helper = new Database_Helper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        //btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
        btnSpeak = (ImageView) findViewById(R.id.btnSpeak);
        txtSpeechInput = (EditText) findViewById(R.id.txtSpeechInput);
        //buttons for movement
        step1_back = (Button)findViewById(R.id.step1_back);
        step1_next = (Button)findViewById(R.id.step1_next);






        // hide the action bar
       // getActionBar().hide();

        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

        //button to step 2
        step1_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               //placing the text input into the Database
                EditText name = (EditText) findViewById(R.id.txtSpeechInput);

                String name_str = name.getText().toString();

                Record r = new Record();
                r.setName(name_str);
                helper.insertRecord(r);




                //This is setting the login in button to bring you to the sign-up screen
                Intent new_activity_step2 = new Intent(MainActivity.this, StepTwo.class);


                startActivity(new_activity_step2);


            }
        });



        //button back to home
        step1_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //This is setting the login in button to bring you to the sign-up screen
                Intent new_activity_home = new Intent(MainActivity.this, HomeScreen.class);

                startActivity(new_activity_home);


            }
        });

    }

    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtSpeechInput.setText(result.get(0));
                }
                break;
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //disable device back button
    @Override
    public void onBackPressed(){

    }

}