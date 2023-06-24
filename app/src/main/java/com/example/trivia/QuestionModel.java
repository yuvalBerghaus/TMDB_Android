package com.example.trivia;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuestionModel {
    @SerializedName("category")
    private String category;
    @SerializedName("type")
    private String type;
    @SerializedName("difficulty")
    private String difficulty;
    @SerializedName("question")
    private String question;
    @SerializedName("correct_answer")
    private String correct_answer;
    @SerializedName("incorrect_answers")
    @Expose
    private String[] incorrect_answers;
    private ArrayList<String> shuffeled_options;
    public ArrayList<String> getShuffeled_options() {
        return shuffeled_options;
    }
    public boolean isShuffled;
    public QuestionModel() {
        isShuffled = false;
    }
    public void shuffleOptions() {
        if(!isShuffled) {
            isShuffled = true;
            shuffeled_options = new ArrayList<>();
            Log.d("checkArray", getIncorrect_answers()[0]);
            shuffeled_options.add(correct_answer);
            shuffeled_options.addAll(Arrays.asList(incorrect_answers));
            Collections.shuffle(shuffeled_options);
        }

    }
    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public String[] getIncorrect_answers() {
        return incorrect_answers;
    }
}
