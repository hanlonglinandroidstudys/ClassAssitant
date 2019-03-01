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
import android.widget.BaseAdapter;

import org.litepal.crud.DataSupport;

import java.util.List;

import hanlonglin.com.common.AppData.StuData;
import hanlonglin.com.common.database.model.Homework_publish;
import hanlonglin.com.student_model.R;
import hanlonglin.com.student_model.adapter.StuHomeworkAdapter;

public class StuFgHomework extends BaseFragment {

    RecyclerView recyclerView_homework;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.stu_fg_homework, container, false);
        initView(v);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initView(View v) {
        recyclerView_homework = (RecyclerView) v.findViewById(R.id.recyclerView_homework);
    }

    private void initData() {
        List<Homework_publish> homework_publishList=DataSupport.where("gid=? and pid=?",StuData.loginer.getGid()+"",StuData.loginer.getPid()+"").find(Homework_publish.class);
        StuHomeworkAdapter stuHomeworkAdapter=new StuHomeworkAdapter(homework_publishList,getActivity());
        recyclerView_homework.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_homework.setAdapter(stuHomeworkAdapter);
    }
}
