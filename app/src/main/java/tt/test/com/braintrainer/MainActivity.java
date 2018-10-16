package tt.test.com.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static android.widget.RelativeLayout.*;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    TextView sumTextView;
    TextView resultTextView;
    TextView scoreTextView;
    TextView timerTextView;
    RelativeLayout gameRelativeLayout;
    ArrayList<Integer> answers=new ArrayList();
    int locationOfCorrectAnswer=0;
    int numberOfQuestions=0;
    int score=0;
    public void activeButtons()
    {
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

    }
    public void disableButtons()
    {
        button0.setEnabled(false);
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
    }
    public void start(View view)
    {
        startButton.setVisibility(view.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }
    public  void playAgain(View view)
    {
        score=0;
        numberOfQuestions=0;
        playAgainButton.setVisibility(view.INVISIBLE);
        timerTextView.setText("30s");
        scoreTextView.setText("0/0");
        resultTextView.setText("");
        activeButtons();
        generateQuestion();
        CountDownTimer countDownTimer = new CountDownTimer(30100, 1000) {


            @Override
            public void onTick(long l) {

                timerTextView.setText(Integer.toString((int) (l/1000))+"s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                resultTextView.setText("Your Score:"+Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
                playAgainButton.setVisibility(View.VISIBLE);
                disableButtons();

            }
        }.start();



    }
    public void generateQuestion()
    {
        Random random=new Random();
        int a=random.nextInt(21);
        int b=random.nextInt(21);
        locationOfCorrectAnswer=random.nextInt(4);
        answers.clear();
        int generateOperation = random.nextInt(4);
        int inCorrectAnswer = 0;

        switch(generateOperation)
        {
            case 2:  while(a < b)
            {
                a = random.nextInt(21);
                b = random.nextInt(21);
            } break;

            case 3:  while(a < b && b == 0)
            {
                a = random.nextInt(21);
                b = random.nextInt(21);
            } break;
            default: break;
        }

        switch (generateOperation)
        {
            case 0: sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b)); break;
            case 1: sumTextView.setText(Integer.toString(a) + " * " + Integer.toString(b)); break;
            case 2: sumTextView.setText(Integer.toString(a) + " - " + Integer.toString(b)); break;
            case 3: sumTextView.setText(Integer.toString(a) + " / " + Integer.toString(b)); break;
            default: break;
        }

        for(int i=0;i<4;i++)
        {
            if (i == locationOfCorrectAnswer)
            {

                switch(generateOperation)
                {
                    case 0: answers.add(a + b); break;
                    case 1: answers.add(a * b); break;
                    case 2: answers.add(a - b); break;
                    case 3: answers.add(a / b); break;
                    default: break;
                }

            }
            else
            {
                switch(generateOperation)
                {
                    case 0: inCorrectAnswer = random.nextInt(41);
                        while(inCorrectAnswer == a + b){
                            inCorrectAnswer = random.nextInt(41);
                        }  break;

                    case 1: inCorrectAnswer = random.nextInt(401);
                        while(inCorrectAnswer == a * b){
                            inCorrectAnswer = random.nextInt(401);
                        } break;

                    case 2: inCorrectAnswer = random.nextInt(21);
                        while(inCorrectAnswer == a - b){
                            inCorrectAnswer = random.nextInt(21);
                        } break;

                    case 3: inCorrectAnswer = random.nextInt(21);
                        while(inCorrectAnswer == a / b){
                            inCorrectAnswer = random.nextInt(21);
                        } break;
                    default: break;
                }

              /*  if( Arrays.asList(answers).contains(inCorrectAnswer))
                {
                    inCorrectAnswer = random.nextInt(41);
                }*/
               if (!answers.contains(inCorrectAnswer))
               {

                   answers.add(inCorrectAnswer);
               }
               else
               {
                   i=i-1;
               }
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
             resultTextView.setText("Correct!");
             score++;
         }
         else
         {
             resultTextView.setText("Wrong!");

         }
         numberOfQuestions++;
         scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));

         generateQuestion();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         sumTextView=(TextView)findViewById(R.id.sumTextView);
         startButton=(Button)findViewById(R.id.startButton);
         button0=(Button)findViewById(R.id.button0);
         button1=(Button)findViewById(R.id.button1);
         button2=(Button)findViewById(R.id.button2);
         button3=(Button)findViewById(R.id.button3);
        resultTextView=(TextView)findViewById(R.id.resultTextView);
        scoreTextView=(TextView)findViewById(R.id.scoreTextView);
        timerTextView=(TextView)findViewById(R.id.timerTextView);
        playAgainButton=(Button)findViewById(R.id.playAgainButton);
        gameRelativeLayout=(RelativeLayout)findViewById(R.id.gameRelativeLayout);


    }


}

