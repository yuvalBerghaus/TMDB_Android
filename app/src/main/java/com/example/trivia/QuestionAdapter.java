package com.example.trivia;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
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
    QuestionModel currentModel;
    private Context context;
    private List<QuestionModel> questionModelList;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.button_item, viewGroup, false);
        return new ViewHolder(v);
    }
    public QuestionAdapter(List<QuestionModel> questionModelList) {
        this.questionModelList = questionModelList;
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");
        if (position != questionModelList.size()) {

            currentModel = questionModelList.get(position);
            currentModel.shuffleOptions();
//            viewHolder.category.setText(currentModel.getCategory());
//            viewHolder.difficulty.setText(currentModel.getDifficulty());
            viewHolder.question.setText(currentModel.getQuestion());
            viewHolder.option1.setText(currentModel.getShuffeled_options().get(0));
            viewHolder.option2.setText(currentModel.getShuffeled_options().get(1));
            viewHolder.answer.setText(currentModel.getCorrect_answer());
            viewHolder.answer.setVisibility(View.INVISIBLE);
//            viewHolder.option3.setText(currentModel.getShuffeled_options().get(2));
//            viewHolder.option4.setText(currentModel.getShuffeled_options().get(3));
        }
    }

    @Override
    public int getItemCount() {
        return questionModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        // thats where we define click listener for the item
        // thats where we assign the text
//        private final TextView category;
////        private final TextView type;
//        private final TextView difficulty;
        private final TextView question;
        private String right_answer;
        private final Button option1;
        private final Button option2;
        private final Button option3;
        private final Button option4;
        private final TextView answer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            option1 = (Button) itemView.findViewById(R.id.option1);
            option2 = (Button) itemView.findViewById(R.id.option2);
            option3 = (Button) itemView.findViewById(R.id.option3);
            option4 = (Button) itemView.findViewById(R.id.option4);
//            category = (TextView) itemView.findViewById(R.id.category);
//            difficulty = (TextView) itemView.findViewById(R.id.difficulty);
            question = (TextView) itemView.findViewById(R.id.question);
            answer = (TextView) itemView.findViewById(R.id.answer);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "", Toast.LENGTH_SHORT).show();
                }
            });
            option1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button button = (Button)v;
                    Log.d("buttonText",button.getText().toString());
                    onButtonClicked(button);
                }
            });
            option2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button button = (Button)v;
                    onButtonClicked(button);
                }
            });
            option3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button button = (Button)v;
                    onButtonClicked(button);
                }
            });
            option4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button button = (Button)v;
                    onButtonClicked(button);
                }
            });
        }



        @SuppressLint("ResourceAsColor")
        public void onButtonClicked(Button button) {

            answer.setVisibility(View.VISIBLE);
            if(button.getText().toString().equals(answer.getText().toString())) {
                right_answer = answer.getText().toString();
                answer.setText("Correct!");
            }
            else {
                button.setBackgroundColor(androidx.cardview.R.color.cardview_shadow_end_color);
                answer.setText("Wrong");
            }
        }
    }
}
