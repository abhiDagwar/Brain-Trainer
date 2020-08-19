package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    final int minNumber = 1;
    final int maxNumber = 999;
    final int ansMinNumber = 1;
    final int ansMaxNumber = 1998;
    int totalQuestionAttempt = 0;
    int validAnswerGiven = 0;
    String answer;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupGoView();
    }

    private void setupGoView() {
        ansCountTextView = (TextView) findViewById(R.id.ansCountTextView);
        if (ansCountTextView.getVisibility() == View.VISIBLE) {
            ansCountTextView.setVisibility(View.INVISIBLE);
        }

        ansResultTextView = (TextView) findViewById(R.id.ansResultTextView);
        if (ansResultTextView.getVisibility() == View.VISIBLE) {
            ansResultTextView.setVisibility(View.INVISIBLE);
        }

        timerTextView = (TextView) findViewById(R.id.timerTextView);
        if (timerTextView.getVisibility() == View.VISIBLE) {
            timerTextView.setVisibility(View.INVISIBLE);
        }

        questionTextView = (TextView) findViewById(R.id.questionTextView);
        if (questionTextView.getVisibility() == View.VISIBLE) {
            questionTextView.setVisibility(View.INVISIBLE);
        }

        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        if (playAgainButton.getVisibility() == View.VISIBLE) {
            playAgainButton.setVisibility(View.INVISIBLE);
        }

        ansButton1 = (Button) findViewById(R.id.ansButton1);
        if (ansButton1.getVisibility() == View.VISIBLE) {
            ansButton1.setVisibility(View.INVISIBLE);
        }
        ansButton2 = (Button) findViewById(R.id.ansButton2);
        if (ansButton2.getVisibility() == View.VISIBLE) {
            ansButton2.setVisibility(View.INVISIBLE);
        }
        ansButton3 = (Button) findViewById(R.id.ansButton3);
        if (ansButton3.getVisibility() == View.VISIBLE) {
            ansButton3.setVisibility(View.INVISIBLE);
        }
        ansButton4 = (Button) findViewById(R.id.ansButton4);
        if (ansButton4.getVisibility() == View.VISIBLE) {
            ansButton4.setVisibility(View.INVISIBLE);
        }
    }

    private void setupQuestionView() {
        totalQuestionAttempt = 0;
        validAnswerGiven = 0;
        ansCountTextView.setText("0/0");

        if (ansCountTextView.getVisibility() == View.INVISIBLE) {
            ansCountTextView.setVisibility(View.VISIBLE);
        }

        if (ansResultTextView.getVisibility() == View.VISIBLE) {
            ansResultTextView.setVisibility(View.INVISIBLE);
        }

        if (timerTextView.getVisibility() == View.INVISIBLE) {
            timerTextView.setVisibility(View.VISIBLE);
        }

        if (questionTextView.getVisibility() == View.INVISIBLE) {
            questionTextView.setVisibility(View.VISIBLE);
        }

        if (playAgainButton.getVisibility() == View.VISIBLE) {
            playAgainButton.setVisibility(View.INVISIBLE);
        }

        if (ansButton1.getVisibility() == View.INVISIBLE) {
            ansButton1.setVisibility(View.VISIBLE);
        }
        if (ansButton2.getVisibility() == View.INVISIBLE) {
            ansButton2.setVisibility(View.VISIBLE);
        }
        if (ansButton3.getVisibility() == View.INVISIBLE) {
            ansButton3.setVisibility(View.VISIBLE);
        }
        if (ansButton4.getVisibility() == View.INVISIBLE) {
            ansButton4.setVisibility(View.VISIBLE);
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
        answer = Integer.toString(Integer.parseInt(num1) + Integer.parseInt(num2));

        questionTextView.setText(num1 + "+" + num2);

        createAnswer();
    }

    private void createAnswer() {
        int randomButton = 0;
        int min = 1;
        int max = 4;
        randomButton = Integer.parseInt(generateRandomNumber(min, max));
        if (randomButton == 1) {
            ansButton1.setText(answer);
            ansButton2.setText(generateRandomNumber(ansMinNumber, ansMaxNumber));
            ansButton3.setText(generateRandomNumber(ansMinNumber, ansMaxNumber));
            ansButton4.setText(generateRandomNumber(ansMinNumber, ansMaxNumber));
        } else if (randomButton == 2) {
            ansButton1.setText(generateRandomNumber(ansMinNumber, ansMaxNumber));
            ansButton2.setText(answer);
            ansButton3.setText(generateRandomNumber(ansMinNumber, ansMaxNumber));
            ansButton4.setText(generateRandomNumber(ansMinNumber, ansMaxNumber));
        } else if (randomButton == 3) {
            ansButton1.setText(generateRandomNumber(ansMinNumber, ansMaxNumber));
            ansButton2.setText(generateRandomNumber(ansMinNumber, ansMaxNumber));
            ansButton3.setText(answer);
            ansButton4.setText(generateRandomNumber(ansMinNumber, ansMaxNumber));
        } else if (randomButton == 4) {
            ansButton1.setText(generateRandomNumber(ansMinNumber, ansMaxNumber));
            ansButton2.setText(generateRandomNumber(ansMinNumber, ansMaxNumber));
            ansButton3.setText(generateRandomNumber(ansMinNumber, ansMaxNumber));
            ansButton4.setText(answer);
        }
    }

    public void selectAnswer(View view) {
        Button answerButtons = (Button) view;

        int tappedCounter = Integer.parseInt(answerButtons.getTag().toString());
        Toast.makeText(this, answerButtons.getTag().toString(), Toast.LENGTH_SHORT).show();

        if (ansResultTextView.getVisibility() == View.INVISIBLE) {
            ansResultTextView.setVisibility(View.VISIBLE);
        }

        totalQuestionAttempt = 1 + totalQuestionAttempt;

        if (Integer.parseInt(answer) == Integer.parseInt(answerButtons.getText().toString())) {
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