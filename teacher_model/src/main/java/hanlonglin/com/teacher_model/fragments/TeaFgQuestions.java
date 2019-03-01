package hanlonglin.com.teacher_model.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.style.QuoteSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

import hanlonglin.com.common.AppData.TeaData;
import hanlonglin.com.common.database.model.Question;
import hanlonglin.com.teacher_model.R;
import hanlonglin.com.teacher_model.activitys.TeaAnswerActivity;
import hanlonglin.com.teacher_model.adapter.QuestionsAdatper;

public class TeaFgQuestions extends BaseFragment {
    RadioGroup radioGroup;
    RecyclerView recyclerView_questions;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.tea_fg_questions, container, false);
        initView(v);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData(0);
    }

    private void initView(View v) {
        radioGroup=(RadioGroup)v.findViewById(R.id.ragroup);
        recyclerView_questions = (RecyclerView) v.findViewById(R.id.recyclerView_questions);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.ra_authed){
                    initData(1);
                }
                else if(checkedId==R.id.ra_not_authed){
                    initData(0);
                }
            }
        });
    }

    private void initData(int state) {
        List<Question> questionList = DataSupport.where("tid=? and state=?", TeaData.loginer.getTid() + "",state+"").find(Question.class);
        QuestionsAdatper questionsAdatper=new QuestionsAdatper(questionList,getActivity());
        recyclerView_questions.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_questions.setAdapter(questionsAdatper);

        questionsAdatper.setOnChooseQuestion(new QuestionsAdatper.OnChooseQuestion() {
            @Override
            public void onItemClick(Question question) {
                //点击一个问题
                //Toast.makeText(getActivity(), "选择问题："+question.getQuestion(), Toast.LENGTH_SHORT).show();
                TeaAnswerActivity.actionStart(question.getQid(),getActivity());
            }
        });
    }
}
