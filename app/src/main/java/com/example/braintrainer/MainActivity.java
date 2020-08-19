package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView;
    TextView questionTextView;
    TextView ansCountTextView;
    TextView ansResultTextView;
    Button playAgainButton;
    Button ansButton1;
    Button ansButton2;
    Button ansButton3;
    Button ansButton4;
    GridLayout optionsGridLayout;
    ConstraintLayout gameLayout;
    final int minNumber = 1;
    final int maxNumber = 999;
    final int ansMinNumber = 1;
    final int ansMaxNumber = 1998;
    int totalQuestionAttempt = 0;
    int validAnswerGiven = 0;
    int answer;
    ArrayList<Integer> options = new ArrayList<Integer>();
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupGoView();
    }

    private void setupGoView() {
        ansCountTextView = (TextView) findViewById(R.id.ansCountTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        optionsGridLayout = (GridLayout) findViewById(R.id.optionsGridLayout);
        ansButton1 = (Button) findViewById(R.id.ansButton1);
        ansButton2 = (Button) findViewById(R.id.ansButton2);
        ansButton3 = (Button) findViewById(R.id.ansButton3);
        ansButton4 = (Button) findViewById(R.id.ansButton4);

        gameLayout = (ConstraintLayout) findViewById(R.id.gameLayout);
        if (gameLayout.getVisibility() == View.VISIBLE) {
            gameLayout.setVisibility(View.INVISIBLE);
        }

        ansResultTextView = (TextView) findViewById(R.id.ansResultTextView);
        if (ansResultTextView.getVisibility() == View.VISIBLE) {
            ansResultTextView.setVisibility(View.INVISIBLE);
        }

        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        if (playAgainButton.getVisibility() == View.VISIBLE) {
            playAgainButton.setVisibility(View.INVISIBLE);
        }
    }

    private void setupQuestionView() {
        totalQuestionAttempt = 0;
        validAnswerGiven = 0;
        ansCountTextView.setText("0/0");

        if (ansResultTextView.getVisibility() == View.VISIBLE) {
            ansResultTextView.setVisibility(View.INVISIBLE);
        }

        if (playAgainButton.getVisibility() == View.VISIBLE) {
            playAgainButton.setVisibility(View.INVISIBLE);
        }

        ansButton1.setEnabled(true);
        ansButton2.setEnabled(true);
        ansButton3.setEnabled(true);
        ansButton4.setEnabled(true);

        createQuestion();
        startsCounter();
    }

    private void createQuestion() {
        String num1 = generateRandomNumber(minNumber, maxNumber);
        String num2 = generateRandomNumber(minNumber, maxNumber);
        answer = Integer.parseInt(num1) + Integer.parseInt(num2);

        questionTextView.setText(num1 + "+" + num2);

        createAnswer();
    }

    private void createAnswer() {
        // clear() options before add
        options.clear();

        int randomButton = 0;
        int min = 0;
        int max = 3;
        randomButton = Integer.parseInt(generateRandomNumber(min, max));

        for (int i = 0; i < 4; i++) {
            if (i == randomButton) {
                options.add(answer);
            } else {
                int removeDuplicateOption = Integer.parseInt(generateRandomNumber(ansMinNumber, ansMaxNumber));
                while (removeDuplicateOption == answer) {
                    removeDuplicateOption = Integer.parseInt(generateRandomNumber(ansMinNumber, ansMaxNumber));
                }
                options.add(removeDuplicateOption);
            }
        }

        ansButton1.setText(Integer.toString(options.get(0)));
        ansButton2.setText(Integer.toString(options.get(1)));
        ansButton3.setText(Integer.toString(options.get(2)));
        ansButton4.setText(Integer.toString(options.get(3)));
    }

    public void selectAnswer(View view) {
        Button answerButtons = (Button) view;

        if (ansResultTextView.getVisibility() == View.INVISIBLE) {
            ansResultTextView.setVisibility(View.VISIBLE);
        }

        totalQuestionAttempt = 1 + totalQuestionAttempt;

        if (answer == Integer.parseInt(answerButtons.getText().toString())) {
            validAnswerGiven = 1 + validAnswerGiven;
            ansResultTextView.setText("Correct :)");
        } else {
            ansResultTextView.setText("Wrong :(");
        }

        ansCountTextView.setText(Integer.toString(validAnswerGiven) + "/" + Integer.toString(totalQuestionAttempt));

        createQuestion();
    }

    private void timeOutView() {
        if (playAgainButton.getVisibility() == View.INVISIBLE) {
            playAgainButton.setVisibility(View.VISIBLE);
        }

        ansButton1.setEnabled(false);
        ansButton2.setEnabled(false);
        ansButton3.setEnabled(false);
        ansButton4.setEnabled(false);
    }

    public void playAgain(View view) {
        setupQuestionView();
    }

    public void go(View view) {
        Button goButton = (Button) findViewById(R.id.goButton);
        goButton.setVisibility(View.INVISIBLE);

        gameLayout.setVisibility(View.VISIBLE);

        setupQuestionView();
    }

    private String generateRandomNumber(int minimum, int maximum) {
        int min = minimum;
        int max = maximum;

        Random r = new Random();
        int i1 = r.nextInt(max - min + 1) + min;
        return Integer.toString(i1);
    }

    private void startsCounter() {
        countDownTimer = new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int) millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                timeOutView();
                resetTimer();
            }
        }.start();
    }

    private void updateTimer(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes * 60);
        String secondString = Integer.toString(seconds);

        if (seconds <= 9) {
            secondString = "0" + secondString + "s";
        }

        timerTextView.setText(secondString);
    }

    private void resetTimer() {
        timerTextView.setText("30s");
        countDownTimer.cancel();
    }

}