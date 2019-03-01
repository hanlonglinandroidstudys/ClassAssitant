package hanlonglin.com.student_model.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import org.litepal.crud.DataSupport;

import java.util.List;

import hanlonglin.com.common.AppData.StuData;
import hanlonglin.com.common.database.model.Dayoff;
import hanlonglin.com.common.database.model.Question;
import hanlonglin.com.student_model.R;
import hanlonglin.com.student_model.adapter.DayoffAdapter;
import hanlonglin.com.student_model.adapter.QuestionsAdatper;

public class StuFgDayoffRecord extends BaseFragment {
    RecyclerView recyclerView_dayoffs;
    RadioGroup radioGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.stu_fg_dayoff_records, container, false);
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
        recyclerView_dayoffs = (RecyclerView) v.findViewById(R.id.recyclerView_dayoffs);

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
        List<Dayoff> dayoffList = DataSupport.where("sid=? and state=?", StuData.loginer.getSid() + "",state+"").find(Dayoff.class);
        DayoffAdapter dayoffAdapter=new DayoffAdapter(dayoffList,getActivity());
        recyclerView_dayoffs.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_dayoffs.setAdapter(dayoffAdapter);
    }

}
