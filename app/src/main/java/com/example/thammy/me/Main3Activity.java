package com.example.thammy.me;


/**
 * Created by THAMMY on 8/26/2016.
 */

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Main3Activity extends AppCompatActivity {
     Button buttonstart;
    TextView text1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        buttonstart = (Button)findViewById(R.id.buttonstart);
        text1 = (TextView)findViewById(R.id.textView3) ;
        buttonstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 new loadasync().execute();
            }
        });
    }


    private class loadasync extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
        int i , j;
            j=0;
            for (i=0 ; i<9000000; i++){

               j=i;

            }
                  return String.valueOf(j);
        }

        @Override
        protected void onPreExecute() {
            text1.setText("loading on preexecute ...........");
        }

        @Override
        protected void onPostExecute(String s) {

            text1.setText(s);

        }
    }


}
