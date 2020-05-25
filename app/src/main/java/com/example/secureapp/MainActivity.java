package com.example.secureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button clickButton = (Button) findViewById(R.id.clickButton);
        clickButton.setOnClickListener( new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent myIntent = new Intent(this, Connection.class);
                //myIntent.putExtra("key", value); //Optional parameters
                this.startActivity(myIntent);
            }
        });
    }
}
