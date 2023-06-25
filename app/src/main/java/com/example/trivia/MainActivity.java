package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    QuestionAdapter adapter;
    TextView current_question;
    TextView current_answer;
    Button next;
    Context context;
    int current_index_question = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        next = (Button)findViewById(R.id.next);
        current_question = (TextView)findViewById(R.id.question);
        current_answer = (TextView)findViewById(R.id.answer);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<ResponseModel> call = service.getResponseModel();
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Log.d("connection", "success!");
                Log.d("output",response.body().getResults()[0].getCategory());
                QuestionModel[] questionModelList = response.body().getResults();
                Log.d("output",questionModelList[0].getCorrect_answer());
                    current_question.setText(questionModelList[current_index_question].getQuestion());
                current_answer.setText("");
                    adapter = new QuestionAdapter(context, questionModelList[current_index_question], current_answer);
//                adapter = new QuestionAdapter(Arrays.asList(response.body().getResults()));
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(current_index_question+1 < questionModelList.length && questionModelList[current_index_question].isClicked()) {
                            current_index_question++;
                            current_question.setText(questionModelList[current_index_question].getQuestion());
                            adapter = new QuestionAdapter(context, questionModelList[current_index_question], current_answer);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            current_answer.setText("");
                        }
                        else {
                            //finished questions!
                            Log.d("Finished", "you finished the game");

                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.d("connection", "failed!");
            }
        });


    }


}