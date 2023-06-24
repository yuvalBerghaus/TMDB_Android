package com.example.trivia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api.php?amount=10")
    Call<ResponseModel> getResponseModel();
}
