package hanlonglin.com.teacher_model.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

import hanlonglin.com.common.AppData.TeaData;
import hanlonglin.com.common.database.model.Dayoff;
import hanlonglin.com.common.database.model.Question;
import hanlonglin.com.teacher_model.R;
import hanlonglin.com.teacher_model.activitys.TeaAnswerActivity;
import hanlonglin.com.teacher_model.adapter.DayoffAdapter;
import hanlonglin.com.teacher_model.adapter.QuestionsAdatper;

public class TeaFgDayoffs extends BaseFragment {
    RadioGroup radioGroup;
    RecyclerView recyclerView_dayoffs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.tea_fg_dayoffs, container, false);
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
        List<Dayoff> dayoffList = DataSupport.where("tid=? and state=?", TeaData.loginer.getTid() + "",state+"").find(Dayoff.class);
        DayoffAdapter dayoffAdapter=new DayoffAdapter(dayoffList,getActivity());
        recyclerView_dayoffs.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_dayoffs.setAdapter(dayoffAdapter);

        dayoffAdapter.setOnAuthedListener(new DayoffAdapter.OnAuthedListener() {
            @Override
            public void onAuthed(Dayoff dayoff) {
                dayoff.setState(1);
                dayoff.save();
                if(radioGroup.getCheckedRadioButtonId()==R.id.ra_authed)
                    initData(1);
                else
                    initData(0);

                Toast.makeText(getActivity(), "请假审批通过！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
