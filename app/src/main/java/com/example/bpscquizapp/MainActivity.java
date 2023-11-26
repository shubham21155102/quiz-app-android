package com.example.bpscquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView;
    TextView questionNumberTextView;
    TextView questionTextView;
    TextView ScoreTextView;
    Button ansA;
    Button ansB;
    Button ansC;
    Button ansD;
    Button submitbtn;

    int score = 0;
    int totalQuestions;
    int currentQuestionIndex = 0;

    String selectedAnswer = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsTextView = findViewById(R.id.heading);
        ScoreTextView = findViewById(R.id.score);
        questionTextView = findViewById(R.id.question);
        questionNumberTextView = findViewById(R.id.question_number);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitbtn = findViewById(R.id.submit);

        // Set OnClickListener for answer buttons and submit button
        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitbtn.setOnClickListener(this);

        // Get total questions count

        totalQuestions = QuestionAnswer.questions.length;
        totalQuestionsTextView.setText("Total Questions: " + totalQuestions);


        // Load the first question when the app starts
        loadNextQuestion();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ans_A || v.getId() == R.id.ans_B || v.getId() == R.id.ans_C || v.getId() == R.id.ans_D) {
            selectedAnswer = ((Button) v).getText().toString();
            //change the button color
            Button selectedButton = (Button) v;
//            selectedButton.setBackgroundColor(Color.parseColor("#FF0000"));
            checkAnswer(selectedAnswer,selectedButton);
        } else if (v.getId() == R.id.submit) {
            // Handle submission or move to the next question
            if (currentQuestionIndex < totalQuestions - 1) {
                currentQuestionIndex++;
                loadNextQuestion();
            } else {
                showScore();
            }
        }
    }

    void loadNextQuestion() {
        questionTextView.setText(QuestionAnswer.questions[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.options[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.options[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.options[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.options[currentQuestionIndex][3]);
        Button AnsA = findViewById(R.id.ans_A);
        Button AnsB = findViewById(R.id.ans_B);
        Button AnsC = findViewById(R.id.ans_C);
        Button AnsD = findViewById(R.id.ans_D);
        AnsA.setBackgroundColor(Color.parseColor("#FFFFFF"));
        AnsB.setBackgroundColor(Color.parseColor("#FFFFFF"));
        AnsC.setBackgroundColor(Color.parseColor("#FFFFFF"));
        AnsD.setBackgroundColor(Color.parseColor("#FFFFFF"));
        questionNumberTextView.setText("Question: " + (currentQuestionIndex + 1) + "/" + totalQuestions);
    }

    void checkAnswer(String selectedAnswer,Button selectedButton) {
        if (selectedAnswer.equals(QuestionAnswer.answers[currentQuestionIndex])) {
            score++;
            //change the button color to green
            Button AnsA = findViewById(R.id.ans_A);
            Button AnsB = findViewById(R.id.ans_B);
            Button AnsC = findViewById(R.id.ans_C);
            Button AnsD = findViewById(R.id.ans_D);
            AnsA.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnsB.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnsC.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnsD.setBackgroundColor(Color.parseColor("#FFFFFF"));
            selectedButton.setBackgroundColor(Color.parseColor("#008000"));
            ScoreTextView.setText("Score: " + score);
            currentQuestionIndex++;
            //wait for 5 second and then load the next question
            questionTextView.setText(QuestionAnswer.questions[currentQuestionIndex]);
            ansA.setText(QuestionAnswer.options[currentQuestionIndex][0]);
            ansB.setText(QuestionAnswer.options[currentQuestionIndex][1]);
            ansC.setText(QuestionAnswer.options[currentQuestionIndex][2]);
            ansD.setText(QuestionAnswer.options[currentQuestionIndex][3]);
            AnsA.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnsB.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnsC.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnsD.setBackgroundColor(Color.parseColor("#FFFFFF"));
            questionNumberTextView.setText("Question: " + (currentQuestionIndex + 1) + "/" + totalQuestions);
//            Toast.makeText(this, "Correct! ", Toast.LENGTH_SHORT).show();
        } else {
            ScoreTextView.setText("Score: " + score);
            //change the button color to red and remove the green color if it was selected before
            Button AnsA = findViewById(R.id.ans_A);
            Button AnsB = findViewById(R.id.ans_B);
            Button AnsC = findViewById(R.id.ans_C);
            Button AnsD = findViewById(R.id.ans_D);
            AnsA.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnsB.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnsC.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnsD.setBackgroundColor(Color.parseColor("#FFFFFF"));
            selectedButton.setBackgroundColor(Color.parseColor("#FF0000"));
            questionNumberTextView.setText("Question: " + (currentQuestionIndex + 1) + "/" + totalQuestions);
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }
    }

    void showScore() {
        // Display final score when all questions are answered
        Toast.makeText(this, "Quiz completed! Your Score: " + score + "/" + totalQuestions, Toast.LENGTH_LONG).show();
    }
}
