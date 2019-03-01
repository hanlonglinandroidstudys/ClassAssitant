package hanlonglin.com.teacher_model.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import hanlonglin.com.common.AppData.TeaData;
import hanlonglin.com.common.database.model.Student;
import hanlonglin.com.common.database.util.DBTool;
import hanlonglin.com.teacher_model.R;
import hanlonglin.com.teacher_model.activitys.TeaHomeworkScoreActivity;
import hanlonglin.com.teacher_model.adapter.StudentRecyclerAdapter;

public class TeaFgScore extends BaseFragment {

    Spinner spinnerGid;
    Spinner spinnerPname;
    RecyclerView recyclerViewStuds;
    Button btnSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.tea_fg_score, container, false);
        initView(v);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initView(View v) {
        spinnerGid = (Spinner) v.findViewById(R.id.spinner_gid);
        spinnerPname = (Spinner) v.findViewById(R.id.spinner_pname);
        recyclerViewStuds = (RecyclerView) v.findViewById(R.id.recyclerView_studs);
        btnSearch = (Button) v.findViewById(R.id.btn_search);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStudents();
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
            Log.e("TAG", "获取pid:" + pid);
            String pname = DBTool.getInstance().getProfessionByPid(pid).getPname();
            pnameList.add(pid + "|" + pname);
        }
        bySQL2.close();
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, pnameList);
        spinnerPname.setAdapter(arrayAdapter2);
    }

    private void showStudents() {
        int gid = Integer.parseInt(spinnerGid.getSelectedItem().toString());
        String ptext = spinnerPname.getSelectedItem().toString();
        int pid = Integer.parseInt(ptext.substring(0, ptext.indexOf("|")));

        List<Student> students = DataSupport.where("pid=? and gid=?", pid + "", gid + "").find(Student.class);

        if(students.size()==0)
        {
            Toast.makeText(getActivity(), "为查找到学生！", Toast.LENGTH_SHORT).show();
            return;
        }

        StudentRecyclerAdapter studentRecyclerAdapter = new StudentRecyclerAdapter(students, getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        recyclerViewStuds.setLayoutManager(gridLayoutManager);
        recyclerViewStuds.setAdapter(studentRecyclerAdapter);

        studentRecyclerAdapter.setOnChooseStuListener(new StudentRecyclerAdapter.OnChooseStuListener() {
            @Override
            public void onItemClick(Student stu) {
                //进入查看成绩页面
                TeaHomeworkScoreActivity.actionStart(stu.getSid(),getActivity());
            }
        });
    }
}
