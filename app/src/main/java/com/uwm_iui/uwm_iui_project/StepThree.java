package com.uwm_iui.uwm_iui_project;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Joshua Torres on 12/2/2017.
 */

public class StepThree extends AppCompatActivity {

    private ImageView camera;
    public static final int REQUEST_CAPTURE = 1;
    ImageView result_photo;
    Button step3_back;
    Button step3_next;
    boolean camera_check;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stepthree);

        camera = (ImageView) findViewById(R.id.camera);
        result_photo = (ImageView) findViewById(R.id.photoview);
        step3_back = (Button) findViewById(R.id.step3_back);
        step3_next = (Button) findViewById(R.id.step3_next);

        camera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, REQUEST_CAPTURE);

                camera_check = true;


            }
        });


        //button back to step 2
        step3_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    //This is setting the login in button to bring you to the sign-up screen
                    Intent new_activity_step3 = new Intent(StepThree.this, StepTwo.class);

                    startActivity(new_activity_step3);


            }
        });

        //button back to home
        step3_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if(camera_check == false){

                //message letting the user know that it worked
                Toast sucess = Toast.makeText(StepThree.this, "You need to caputre an image!", Toast.LENGTH_LONG);
                sucess.show();
            }else {

                //This is setting the login in button to bring you to the sign-up screen
                Intent new_activity_home = new Intent(StepThree.this, HomeScreen.class);

                //message letting the user know that it worked
                Toast sucess = Toast.makeText(StepThree.this, "Entry successfully added to Database!", Toast.LENGTH_LONG);
                sucess.show();


                startActivity(new_activity_home);

            }


            }
        });


        //to check if there is a camera
        if (!hasCamera()) camera.setEnabled(false);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            result_photo.setImageBitmap(photo);


        }
    }

    public boolean hasCamera() {

        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);

    }


}




