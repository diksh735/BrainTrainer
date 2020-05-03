package com.diksha.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
Button startButton;
TextView tt;
ArrayList<Integer> answers=new ArrayList<Integer>();
int locationOfCorrectAnswer;
int score=0;
int numberofQuestions=0;
TextView scoreTextView;
TextView resultTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumtextView;
    TextView timerTextView;
    ConstraintLayout gameLayout;

    Button playAgain;
    public void playAgain(View view)
    {
        score=0;
        numberofQuestions=0;
timerTextView.setText("30s");
scoreTextView.setText("0/0");
resultTextView.setText("");
playAgain.setVisibility(View.INVISIBLE);
generateQuestion();
        new CountDownTimer(30000,1000)
        {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                resultTextView.setText("YOUR SCORE "+Integer.toString(score)+"/"+Integer.toString(numberofQuestions));
           playAgain.setVisibility(View.VISIBLE);
                MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.horn);
                mplayer.start();

            }
        }.start();
    }

public void generateQuestion()
{
    Random rand=new Random();
    int a=rand.nextInt(21);
    int b=rand.nextInt(21);
    sumtextView.setText(Integer.toString(a)+"+"+Integer.toString(b));
    locationOfCorrectAnswer=rand.nextInt(4);
    answers.clear();
    int incorrectAnswer;
    for(int i=0;i<4;i++)
    {
        if(i==locationOfCorrectAnswer)
            answers.add(a+b);
        else
        {
            incorrectAnswer=rand.nextInt(41);
            while(incorrectAnswer==a+b) {
                incorrectAnswer = rand.nextInt(41);
            }
            answers.add(incorrectAnswer);
        }
    }
    button0.setText(Integer.toString(answers.get(0)));
    button1.setText(Integer.toString(answers.get(1)));
    button2.setText(Integer.toString(answers.get(2)));
    button3.setText(Integer.toString(answers.get(3)));



}

public void chooseAnswer(View view)
{
if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
{
    score++;
    resultTextView.setText("CORRECT!");
}
else
{
    resultTextView.setText("INCORRECT!");
}
    numberofQuestions++;
scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberofQuestions));
generateQuestion();

}



public void start(View view)
{
    tt.setVisibility(View.INVISIBLE);
    startButton.setVisibility(View.INVISIBLE);
    gameLayout.setVisibility(ConstraintLayout.VISIBLE);
    playAgain(findViewById(R.id.playAgainButton));

}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton=(Button)findViewById(R.id.startButton);
        tt=(TextView)findViewById(R.id.tt);
        sumtextView=(TextView)findViewById(R.id.sumtextView);
        resultTextView=(TextView)findViewById(R.id.resultTextView);
        scoreTextView=(TextView)findViewById(R.id.scoreTextView);
        button0=(Button)findViewById(R.id.button0);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        timerTextView=(TextView)findViewById(R.id.timerTextView);
        playAgain=(Button)findViewById(R.id.playAgainButton);
                  gameLayout=(ConstraintLayout)findViewById(R.id.gameLayout);


    }

}
