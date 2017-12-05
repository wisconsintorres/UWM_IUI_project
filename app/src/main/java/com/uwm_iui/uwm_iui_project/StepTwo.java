package com.uwm_iui.uwm_iui_project;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Joshua Torres on 12/2/2017.
 */

public class StepTwo extends AppCompatActivity {

    private EditText txtSpeechInput2;
    //private ImageButton btnSpeak;
    private ImageView btnSpeak2;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    public Button step2_next;
    public Button step2_back;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.steptwo);

        //txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        //btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
        btnSpeak2 = (ImageView) findViewById(R.id.btnSpeak2);
        txtSpeechInput2 = (EditText) findViewById(R.id.txtSpeechInput2);
        //buttons for movement
        step2_back = (Button)findViewById(R.id.step2_back);
        step2_next = (Button)findViewById(R.id.step2_next);




        // hide the action bar
        // getActionBar().hide();

        btnSpeak2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

        //button to step 3
        step2_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //This is setting the login in button to bring you to the sign-up screen
                Intent new_activity_step3 = new Intent(StepTwo.this, StepThree.class);

                startActivity(new_activity_step3);


            }
        });


        //button back to home
        step2_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //This is setting the login in button to bring you to the sign-up screen
                Intent new_activity_home = new Intent(StepTwo.this, MainActivity.class);

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
                    txtSpeechInput2.setText(result.get(0));
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
