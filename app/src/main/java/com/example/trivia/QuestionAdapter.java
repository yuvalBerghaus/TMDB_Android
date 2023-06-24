package com.example.trivia;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private String[] answers;
    private Context context;
    private List<QuestionModel> questionModelList;

    public QuestionAdapter(List<QuestionModel> questionModelList) {
        this.questionModelList = questionModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.button_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        viewHolder.question.setText(questionModelList.get(position).getQuestion());
        if (!questionModelList.get(position).isShuffled)
            questionModelList.get(position).shuffleOptions();

        viewHolder.option1.setText(questionModelList.get(position).getShuffeled_options().get(0));
        viewHolder.option2.setText(questionModelList.get(position).getShuffeled_options().get(1));
        viewHolder.answer.setText(questionModelList.get(position).getCorrect_answer());

        // Set click listeners
        viewHolder.option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                onButtonClicked(button, viewHolder.answer);
            }
        });
        viewHolder.option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                onButtonClicked(button, viewHolder.answer);
            }
        });
        viewHolder.option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                onButtonClicked(button, viewHolder.answer);
            }
        });
        viewHolder.option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                onButtonClicked(button, viewHolder.answer);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView question;
        private final Button option1;
        private final Button option2;
        private final Button option3;
        private final Button option4;
        private final TextView answer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            option1 = itemView.findViewById(R.id.option1);
            option2 = itemView.findViewById(R.id.option2);
            option3 = itemView.findViewById(R.id.option3);
            option4 = itemView.findViewById(R.id.option4);
            question = itemView.findViewById(R.id.question);
            answer = itemView.findViewById(R.id.answer);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void onButtonClicked(Button button, TextView answerTextView) {
        if (answerTextView.getVisibility() == View.INVISIBLE) {
            String selectedAnswer = button.getText().toString();
            String correctAnswer = answerTextView.getText().toString();

            if (selectedAnswer.equals(correctAnswer)) {
                button.setBackgroundColor(Color.parseColor("#00D100"));
                answerTextView.setText("Correct!");
            } else {
                button.setBackgroundColor(Color.parseColor("#BF181D"));
                answerTextView.setText("Wrong");
            }

            button.setClickable(false);
            answerTextView.setVisibility(View.VISIBLE);
        }
    }
}
