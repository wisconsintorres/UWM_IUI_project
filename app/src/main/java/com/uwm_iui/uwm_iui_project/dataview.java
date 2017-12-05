package com.uwm_iui.uwm_iui_project;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Joshua Torres on 12/5/2017.
 */

public class dataview extends AppCompatActivity{

    Database_Helper helper;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dataview);


            ListView listView = (ListView) findViewById(R.id.listViewRecords);
            helper = new Database_Helper(this);

            ArrayList<String> theList = new ArrayList<>();
            Cursor data = helper.getListContents();

            if(data.getCount() == 0){

                Toast sucess = Toast.makeText(dataview.this, "Database empty", Toast.LENGTH_LONG);
                sucess.show();

            }else {

                    while(data.moveToNext()){

                        theList.add(data.getString(1));
                        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,theList);
                        listView.setAdapter(listAdapter);

                    }




            }









        }







}
