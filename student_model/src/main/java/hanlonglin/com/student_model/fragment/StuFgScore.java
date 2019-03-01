package hanlonglin.com.student_model.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.litepal.crud.DataSupport;

import java.util.List;

import hanlonglin.com.common.AppData.StuData;
import hanlonglin.com.common.database.model.Homework_do;
import hanlonglin.com.student_model.R;
import hanlonglin.com.student_model.adapter.StuHomeworkdoAdapter;

public class StuFgScore extends BaseFragment {
    RecyclerView recyclerView_score;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=LayoutInflater.from(getActivity()).inflate(R.layout.stu_fg_score,container,false);
        initView(v);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initView(View v) {
        recyclerView_score=(RecyclerView)v.findViewById(R.id.recyclerView_score);
    }

    private void initData() {
        List<Homework_do> homework_doList=DataSupport.where("sid=?",StuData.loginer.getSid()+"").find(Homework_do.class);
        recyclerView_score.setLayoutManager(new LinearLayoutManager(getActivity()));
        StuHomeworkdoAdapter stuHomeworkdoAdapter=new StuHomeworkdoAdapter(homework_doList,getActivity());
        recyclerView_score.setAdapter(stuHomeworkdoAdapter);
    }
}
