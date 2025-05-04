package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int currOne = 10;
    private int currTwo = 5;
    private String currSumm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editTextAnswer = findViewById(R.id.editTextAnswer);
        Button buttonAnswer = findViewById(R.id.buttonAnswer);
        TextView textViewCorrectAnswer = findViewById(R.id.textViewCorrectAnswer);
        TextView textViewIncorrectAnswer = findViewById(R.id.textViewIncorrectAnswer);
        TextView textViewExample = findViewById(R.id.textViewExample);

        // Инициализация currSumm
        currSumm = currSummCalc();
        textViewExample.setText(currStr());

        buttonAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editTextAnswer.getText().toString();
                if(text.equals(currSumm)){
                    textViewCorrectAnswer.setVisibility(View.VISIBLE);
                    textViewIncorrectAnswer.setVisibility(View.INVISIBLE);
                }
                else {
                    textViewCorrectAnswer.setVisibility(View.GONE);
                    textViewIncorrectAnswer.setVisibility(View.VISIBLE);
                }
                editTextAnswer.setText("");
                textViewExample.setText(randomK());
            }
        });
    }

    private String randomK() {
        currOne = new Random().nextInt(32);
        currTwo = new Random().nextInt(32);
        currSumm = currSummCalc();
        return currStr();
    }

    private String currStr() {
        return currOne + " + " + currTwo + " = ?";
    }

    private String currSummCalc() {
        return Integer.toString(currOne + currTwo);
    }
}