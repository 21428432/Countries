package com.example.thammy.me;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {
    TextView CODE,NAME;
    Button DONE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        CODE = (TextView)findViewById(R.id.txtoutputcode);
        NAME = (TextView)findViewById(R.id.txtoutputname);
        DONE = (Button)findViewById(R.id.btnDone);
        
        Bundle b = getIntent().getExtras();
        NAME.setText(b.getString("cname"));
        CODE.setText(b.getString("ccode"));

        DONE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
