package com.example.eduardogonzalez.blackjack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Instructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        Bundle extras = getIntent().getExtras();
        // check to make sure it is not null
    }
}
