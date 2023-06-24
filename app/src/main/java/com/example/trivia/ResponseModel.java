package com.example.trivia;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseModel {
    @SerializedName("results")
    @Expose
    private QuestionModel[] results;
    @SerializedName("response_code")
    private String response_code;

    public QuestionModel[] getResults() {
        return results;
    }


    public String getResponse_code() {
        return response_code;
    }
}
