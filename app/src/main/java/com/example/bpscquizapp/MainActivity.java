package com.example.bpscquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsTextView = findViewById(R.id.heading);
        ScoreTextView = findViewById(R.id.score);
        questionTextView = findViewById(R.id.question);
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
            checkAnswer(selectedAnswer);
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
    }

    void checkAnswer(String selectedAnswer) {
        if (selectedAnswer.equals(QuestionAnswer.answers[currentQuestionIndex])) {
            score++;
            ScoreTextView.setText("Score: " + score);
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            ScoreTextView.setText("Score: " + score);
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }
    }

    void showScore() {
        // Display final score when all questions are answered
        Toast.makeText(this, "Quiz completed! Your Score: " + score + "/" + totalQuestions, Toast.LENGTH_LONG).show();
    }
}
