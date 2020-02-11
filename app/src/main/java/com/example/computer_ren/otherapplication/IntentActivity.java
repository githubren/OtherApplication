package com.example.computer_ren.otherapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class IntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        TextView textView = findViewById(R.id.show_message_tv);

        Intent intent = getIntent();
        if (intent != null) {
            textView.setText(intent.getStringExtra("data"));
        }
    }
}
