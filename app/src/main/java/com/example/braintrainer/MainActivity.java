package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    TextView tv_question;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    Button button0;
    Button button1;
    Button button2;
    Button button3;

    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
    }

    public void submitAnswer (View view){
        Log.i("Tag:", view.getTag().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_question = findViewById(R.id.tv_question);
        goButton = findViewById(R.id.bt_start);
        button0 = findViewById(R.id.bt_0);
        button1 = findViewById(R.id.bt_1);
        button2 = findViewById(R.id.bt_2);
        button3 = findViewById(R.id.bt_3);

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        int c = a + b;

        int locationOfCorrectAnswer = rand.nextInt(4);

        tv_question.setText(Integer.toString(a) + " + " + Integer.toString(b));

        for (int i = 0; i < 4; i++){
            if (i == locationOfCorrectAnswer){
                answers.add(a + b);
            } else {
                int wrongAnswer = rand.nextInt(41);
                while (wrongAnswer == a + b){
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }
}
