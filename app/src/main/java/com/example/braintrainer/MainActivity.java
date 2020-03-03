package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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

    ArrayList<Integer> answers = new ArrayList<Integer>();
    TextView tv_question;
    TextView tv_result;
    TextView tv_score;
    TextView tv_timer;
    Button goButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button reset;
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    CountDownTimer countDownTimer;
    ConstraintLayout gameLayout;

    public void playAgain (View view){
        score = 0;
        numberOfQuestions = 0;
        tv_timer.setText("30s");
        tv_score.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
        reset.setVisibility(View.INVISIBLE);
        tv_result.setText("");
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                tv_timer.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                tv_result.setText("Done!");
                reset.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();
    }

    public void start(View view) {
        goButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.tv_timer));
        gameLayout.setVisibility(View.VISIBLE);

    }


    public void submitAnswer(View view) {
        Log.i("Tag:", view.getTag().toString());
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            Log.i("correct!", "you got it!");
            tv_result.setVisibility(View.VISIBLE);
            tv_result.setText("Correct!");
            score++;
        } else {
            Log.i("wrong!", ":(");
            tv_result.setVisibility(View.VISIBLE);
            tv_result.setText("Wrong!");
        }
        numberOfQuestions++;
        tv_score.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
    }

    public void newQuestion() {
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        tv_question.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                int wrongAnswer = rand.nextInt(41);
                while (wrongAnswer == a + b) {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_score = findViewById(R.id.tv_score);
        tv_question = findViewById(R.id.tv_question);
        goButton = findViewById(R.id.bt_start);
        button0 = findViewById(R.id.bt_0);
        button1 = findViewById(R.id.bt_1);
        button2 = findViewById(R.id.bt_2);
        button3 = findViewById(R.id.bt_3);
        reset = findViewById(R.id.bt_playAgain);
        tv_result = findViewById(R.id.tv_result);
        tv_timer = findViewById(R.id.tv_timer);
        gameLayout = findViewById(R.id.gameLayout);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);


    }
}
