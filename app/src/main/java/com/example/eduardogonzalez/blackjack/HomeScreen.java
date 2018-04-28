package com.example.eduardogonzalez.blackjack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;



public class HomeScreen extends AppCompatActivity {
    private Button cpu1;
    private Button cpu2;
    private Button cpu3;
    private Button instr;
    private TextView name;
    private Switch difficultySwitch;
    private Switch cardSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


        difficultySwitch = findViewById(R.id.difficultySwitch);
        cardSwitch = findViewById(R.id.cardSwitch);
        name = findViewById(R.id.editText);
        cpu1 = findViewById(R.id.cpu1);
        cpu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeScreen.this, MainActivity.class);
                i.putExtra("cpu", 1);
                i.putExtra("name", String.valueOf(name.getText()));
                i.putExtra("difficulty", difficultySwitch.isChecked());
                i.putExtra("showCards", cardSwitch.isChecked());
                startActivity(i);
            }
        });

        cpu2 = findViewById(R.id.cpu2);
        cpu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeScreen.this, MainActivity.class);
                i.putExtra("cpu", 2);
                i.putExtra("name", String.valueOf(name.getText()));
                i.putExtra("difficulty", difficultySwitch.isChecked());
                i.putExtra("showCards", cardSwitch.isChecked());
                startActivity(i);
            }
        });

        cpu3 = findViewById(R.id.cpu3);
        cpu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeScreen.this, MainActivity.class);
                i.putExtra("cpu", 3);
                i.putExtra("name", String.valueOf(name.getText()));
                i.putExtra("difficulty", difficultySwitch.isChecked());
                i.putExtra("showCards", cardSwitch.isChecked());
                startActivity(i);
            }
        });

        difficultySwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (difficultySwitch.isChecked()) {
                    difficultySwitch.setText("Hard");
                } else {
                    difficultySwitch.setText("Easy");
                }
            }
        });

        cardSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cardSwitch.isChecked()) {
                    cardSwitch.setText("Show Cards");
                } else {
                    cardSwitch.setText("Don't Show Cards");
                }
            }
        });
    }
}
