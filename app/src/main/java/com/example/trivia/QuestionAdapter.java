package com.example.trivia;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    Context context;
    private final String answer;
    private final TextView txt_answer;
    private final String question;
    ArrayList<String> questionList;
    QuestionModel questionModel;
    public QuestionAdapter(Context context, QuestionModel questionModel, TextView txt_answer) {
        this.questionModel = questionModel;
        this.question = questionModel.getQuestion();
        this.answer = questionModel.getCorrect_answer();
        this.questionList = questionModel.getShuffeled_options();
        this.context = context;
        this.txt_answer = txt_answer;
    }

    @NonNull
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.button_item, parent, false);
        return new QuestionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.ViewHolder holder, int position) {
        holder.button.setText(questionList.get(position));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                Log.d("clicked!", holder.button.getText().toString());
                if(!questionModel.isClicked()) {
                    if(holder.button.getText().equals(answer)) {
                        txt_answer.setText("Correct!");
                        holder.button.setBackgroundResource(R.drawable.button_pressed);
                    }
                    else {
                        holder.button.setBackgroundResource(R.drawable.button_disabled);
                        txt_answer.setText("Wrong!");
                    }
                    questionModel.setClicked(true);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.option);
        }
    }

}

