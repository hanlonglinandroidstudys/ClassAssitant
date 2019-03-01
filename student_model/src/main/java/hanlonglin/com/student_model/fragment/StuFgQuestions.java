package hanlonglin.com.student_model.fragment;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

import hanlonglin.com.common.AppData.StuData;
import hanlonglin.com.common.database.model.Question;
import hanlonglin.com.common.database.model.Student;
import hanlonglin.com.student_model.R;
import hanlonglin.com.student_model.adapter.QuestionsAdatper;

public class StuFgQuestions extends BaseFragment {
    RecyclerView recyclerView_questions;
    RadioGroup radioGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.stu_fg_questions, container, false);
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
        Log.e("TAG","初始化问题数据");
        List<Question> questionList = DataSupport.where("sid=? and state=?", StuData.loginer.getSid() + "",state+"").find(Question.class);

        QuestionsAdatper questionsAdatper = new QuestionsAdatper(questionList, getActivity());
        recyclerView_questions.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_questions.setAdapter(questionsAdatper);
    }

}
