package com.example.quizapp;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // TODO: Declare constants here
    final int PB_INCREMENT = 8;


    // TODO: Declare member variables here:
    Button trueBtn;
    Button falseBtn;
    TextView questionText;
    TextView scoreText;
    ProgressBar progressBar;
    SoundPool soundPool;

    int correctSoundId;
    int wrongSoundId;
    int resSoundId;

    int score;
    int arrIndex;
    int question;

    private final TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC,0);

        trueBtn =  findViewById(R.id.trueButton);
        falseBtn = findViewById(R.id.falseButton);
        questionText = findViewById(R.id.Question);
        scoreText = findViewById(R.id.Score);
        progressBar = findViewById(R.id.progressBar);

        correctSoundId = soundPool.load(getApplicationContext(),R.raw.correctans,1);
        wrongSoundId = soundPool.load(getApplicationContext(),R.raw.wrongans,1);
        resSoundId = soundPool.load(getApplicationContext(),R.raw.finish,1);

        question = mQuestionBank[arrIndex].getQuestionID();
        questionText.setText(question);

        trueBtn.setOnClickListener(v -> {

            checkAnswer(true);
            updateQuestion();
        });
        falseBtn.setOnClickListener(v -> {
            checkAnswer(false);
            updateQuestion();
        });    }

    @SuppressLint("SetTextI18n")
    private void updateQuestion(){
        arrIndex = (arrIndex + 1) % mQuestionBank.length;
        if(arrIndex == 0){
            AlertDialog.Builder alertBox = new AlertDialog.Builder(this);
            alertBox.setCancelable(false);
            alertBox.setTitle("Game OVer!");
            alertBox.setMessage("Your Score is " + score);
            alertBox.setPositiveButton("Close", (dialog, which) -> finish());
            alertBox.show();
            soundPool.play(resSoundId,1,1,0,2,1);
        }
        progressBar.incrementProgressBy(PB_INCREMENT);
        question = mQuestionBank[arrIndex].getQuestionID();
        questionText.setText(question);
        scoreText.setText("Score " + score +"/" + mQuestionBank.length);
    }

    private void  checkAnswer(boolean userAns){
        boolean correctAnswer = mQuestionBank[arrIndex].isAnswer();
        if (userAns == correctAnswer){
            soundPool.play(correctSoundId,1,1,0,0,1);
            score = score + 1;
            Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_SHORT).show();}
        else{
            soundPool.play(wrongSoundId,1,1,0,0,1);
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast,Toast.LENGTH_SHORT).show();}
    }
}