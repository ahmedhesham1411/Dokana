package com.example.aldokana.Adapter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.example.aldokana.Model.AddressesModel;
import com.example.aldokana.Model.QuestionAndAnswerModel;
import com.example.aldokana.Model.QuestionsModel;
import com.example.aldokana.R;
import com.example.aldokana.databinding.AddressesItemBinding;
import com.example.aldokana.databinding.QuestionsItemBinding;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {

    Activity activity;
    List<QuestionAndAnswerModel> questionsModels;
    Boolean expandable=false;
    public QuestionsAdapter(Activity activity, List<QuestionAndAnswerModel> questionsModels) {
        this.activity = activity;
        this.questionsModels = questionsModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QuestionsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.questions_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.fullLayout.setBackground(activity.getDrawable(R.drawable.shape_rounded_grey));
        holder.binding.questionTxt.setText(questionsModels.get(position).getQuestion());
        holder.binding.txtAnswer.setText(questionsModels.get(position).getAnswer());

        holder.binding.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!holder.binding.expandable.isExpanded()){
                    //TransitionManager.beginDelayedTransition(holder.binding.fullLayout);
                    holder.binding.expandable.expand();
                    holder.binding.txtAnswer.setVisibility(View.VISIBLE);
                    holder.binding.plusimg.setImageResource(R.drawable.minus);
                    holder.binding.fullLayout.setBackground(activity.getDrawable(R.drawable.shapeeee));
                    expandable=true;
                }else {
                    holder.binding.expandable.collapse(true);
                    //holder.binding.txtAnswer.setVisibility(View.GONE);
                    holder.binding.plusimg.setImageResource(R.drawable.plus);
                    expandable=false;
                    final Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            holder.binding.fullLayout.setBackground(activity.getDrawable(R.drawable.shape_rounded_grey));
                        }
                    }, 1000);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return questionsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private QuestionsItemBinding binding;

        private ViewHolder(QuestionsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
