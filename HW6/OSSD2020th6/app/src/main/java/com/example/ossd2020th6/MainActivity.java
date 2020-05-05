package com.example.ossd2020th6;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = new Button(this);
    }

    public void onButtonClick1(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cndgh98.github.io/"));
        startActivity(intent); }
    }
