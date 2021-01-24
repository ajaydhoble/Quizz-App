package com.example.quizapp;

public class TrueFalse {
    private final int questionID;
    private final boolean answer;
    public TrueFalse(int questionResourceID, boolean trueOrFalse){
        questionID = questionResourceID;
        answer = trueOrFalse;
    }

    public int getQuestionID() {
        return questionID;
    }

    public boolean isAnswer() {
        return answer;
    }

}
