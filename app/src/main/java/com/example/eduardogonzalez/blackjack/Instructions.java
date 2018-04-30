package com.example.eduardogonzalez.blackjack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Instructions extends AppCompatActivity {

    private Button goHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        goHome = findViewById(R.id.goHome);
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Instructions.this, HomeScreen.class);
                startActivity(i);
            }
        });


        Bundle extras = getIntent().getExtras();
        // check to make sure it is not null
    }
}
