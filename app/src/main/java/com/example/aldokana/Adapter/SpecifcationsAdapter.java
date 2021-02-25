package com.example.aldokana.Adapter;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aldokana.Activities.QuestionAndAnswerActivity;
import com.example.aldokana.Model.ProductsDataModel;
import com.example.aldokana.Model.QuestionAndAnswerModel;
import com.example.aldokana.Model.SpecificationsDataModel;
import com.example.aldokana.R;
import com.example.aldokana.databinding.QuestionsItemBinding;
import com.example.aldokana.databinding.SpecificationsItemBinding;

import java.util.List;

public class SpecifcationsAdapter extends RecyclerView.Adapter<SpecifcationsAdapter.ViewHolder> {

    Activity activity;
    List<SpecificationsDataModel> specificationsDataModels;
    Boolean expandable=false;
    SpecifcationsExpandableAdapter specifcationsExpandableAdapter;

    public SpecifcationsAdapter(Activity activity, List<SpecificationsDataModel> specificationsDataModels) {
        this.activity = activity;
        this.specificationsDataModels = specificationsDataModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SpecificationsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.specifications_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.fullLayout.setBackground(activity.getDrawable(R.drawable.shape_rounded_grey));
        holder.binding.questionTxt.setText(specificationsDataModels.get(position).getTitle());

        specifcationsExpandableAdapter = new SpecifcationsExpandableAdapter(activity,specificationsDataModels.get(position).getSub_specifications());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        holder.binding.rec.setLayoutManager(linearLayoutManager);
        holder.binding.rec.setHasFixedSize(false);
        holder.binding.rec.setAdapter(specifcationsExpandableAdapter);


        holder.binding.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!holder.binding.expandable.isExpanded()){
                    //TransitionManager.beginDelayedTransition(holder.binding.fullLayout);
                    holder.binding.expandable.expand();
                    holder.binding.rec.setVisibility(View.VISIBLE);
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
        return specificationsDataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private SpecificationsItemBinding binding;

        private ViewHolder(SpecificationsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
