package com.example.aldokana.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.aldokana.Adapter.AddressesAdapter;
import com.example.aldokana.Adapter.QuestionsAdapter;
import com.example.aldokana.Model.AddressesModel;
import com.example.aldokana.Model.QuestionAndAnswerResponse;
import com.example.aldokana.Model.QuestionsModel;
import com.example.aldokana.R;
import com.example.aldokana.databinding.ActivityQuestionAndAnswerBinding;
import com.example.aldokana.viewModels.QuestionAndAnswerViewModel;

import java.util.ArrayList;
import java.util.List;

public class QuestionAndAnswerActivity extends BaseActivity {

    public ActivityQuestionAndAnswerBinding binding;
    QuestionsAdapter questionsAdapter;
    QuestionAndAnswerViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_question_and_answer);

        viewModel = new QuestionAndAnswerViewModel(this);
        binding.setQandAVM(viewModel);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void initQuestionsRecycler(QuestionAndAnswerResponse questionAndAnswerResponse){
        questionsAdapter = new QuestionsAdapter(QuestionAndAnswerActivity.this,questionAndAnswerResponse.getData().getFAQ());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.questionsRecycler.setLayoutManager(linearLayoutManager);
        binding.questionsRecycler.setHasFixedSize(false);
        binding.questionsRecycler.setAdapter(questionsAdapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        viewModel = new QuestionAndAnswerViewModel(this);
        binding.setQandAVM(viewModel);
    }
}