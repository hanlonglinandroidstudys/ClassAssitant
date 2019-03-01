package hanlonglin.com.teacher_model.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hanlonglin.com.common.AppData.TeaData;
import hanlonglin.com.common.database.model.Homework_do;
import hanlonglin.com.common.database.util.DBTool;
import hanlonglin.com.teacher_model.R;
import hanlonglin.com.teacher_model.adapter.HomeworkRecyclerAdapter;

public class TeaFgModifyHomework extends BaseFragment {

    Spinner spinnerGid;
    Spinner spinnerPname;
    Button btnSearch;
    RecyclerView recyclerViewHomeworks;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.tea_fg_modify_homework, container, false);
        initView(v);
        initData();
        return v;
    }

    private void initView(View v) {
        spinnerGid = (Spinner) v.findViewById(R.id.spinner_gid);
        spinnerPname = (Spinner) v.findViewById(R.id.spinner_pname);
        btnSearch = (Button) v.findViewById(R.id.btn_search);
        recyclerViewHomeworks = (RecyclerView) v.findViewById(R.id.recyclerView_homeworks);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHomeworks();
            }
        });
    }

    private void initData() {
        Cursor bySQL = DataSupport.findBySQL("select distinct gid from teacher_gp where tid=" + TeaData.loginer.getTid());
        List<String> gidList = new ArrayList<>();
        while (bySQL.moveToNext()) {
            int gid = bySQL.getInt(bySQL.getColumnIndex("gid"));
            gidList.add(gid + "");
        }
        bySQL.close();
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, gidList);
        spinnerGid.setAdapter(arrayAdapter1);

        Cursor bySQL2 = DataSupport.findBySQL("select distinct pid from teacher_gp where tid=" + TeaData.loginer.getTid());
        List<String> pnameList = new ArrayList<>();
        while (bySQL2.moveToNext()) {
            int pid = bySQL2.getInt(bySQL2.getColumnIndex("pid"));
            String pname = DBTool.getInstance().getProfessionByPid(pid).getPname();
            pnameList.add(pid + "|" + pname);
        }
        bySQL2.close();
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, pnameList);
        spinnerPname.setAdapter(arrayAdapter2);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    private void showHomeworks() {
        int gid = Integer.parseInt(spinnerGid.getSelectedItem().toString());
        String ptext = spinnerPname.getSelectedItem().toString();
        int pid = Integer.parseInt(ptext.substring(0, ptext.indexOf("|")));

        List<Homework_do> students = DataSupport.where("pid=? and gid=? and state=0", pid + "", gid + "").find(Homework_do.class);

        HomeworkRecyclerAdapter homeworkRecyclerAdapter = new HomeworkRecyclerAdapter(students, getActivity());
        recyclerViewHomeworks.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewHomeworks.setAdapter(homeworkRecyclerAdapter);

        if (students.size() == 0) {
            Toast.makeText(getActivity(), "未查询到记录！", Toast.LENGTH_SHORT).show();
        }
    }
}
