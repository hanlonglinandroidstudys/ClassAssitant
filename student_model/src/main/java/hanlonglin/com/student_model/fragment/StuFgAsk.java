package hanlonglin.com.student_model.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Unbinder;
import hanlonglin.com.common.AppData.StuData;
import hanlonglin.com.common.database.model.Question;
import hanlonglin.com.common.database.model.Teacher;
import hanlonglin.com.common.database.model.Teacher_Gp;
import hanlonglin.com.student_model.R;


public class StuFgAsk extends BaseFragment {

    Spinner spinnerTeachers;

    Button btnSubmit;

    EditText edQuestion;


    String teaText = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.stu_fg_ask, container, false);
        initView(v);

        spinnerTeachers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                teaText = (String) parent.getAdapter().getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initView(View v) {
        spinnerTeachers=(Spinner)v.findViewById(R.id.spinner_teachers);
        btnSubmit=(Button)v.findViewById(R.id.btn_submit);
        edQuestion=(EditText)v.findViewById(R.id.ed_question);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit();
            }
        });
    }

    private void initData() {
        List<Teacher_Gp> teaGpList = DataSupport
                .where("pid=? and gid=?", StuData.loginer.getPid() + "", StuData.loginer.getGid() + "").find(Teacher_Gp.class);
        if (teaGpList.size() > 0) {
            String tids="(";
            for (int i = 0; i < teaGpList.size(); i++) {
                tids+=teaGpList.get(i).getTid()+",";
            }
            tids=tids.substring(0,tids.length()-1);
            tids+=")";
            Log.e("TAG", "tids=" + tids);
            List<Teacher> teaList = DataSupport.where("tid in "+tids).find(Teacher.class);
            List<String> teaNameList = new ArrayList<>();
            for (int i = 0; i < teaList.size(); i++) {
                if (teaList.get(i).toString() != null)
                    teaNameList.add(teaList.get(i).toString());
            }
            Log.e("TAG", "teaNameList.size=" + teaNameList.size() );

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, teaNameList);
            spinnerTeachers.setAdapter(arrayAdapter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onSubmit() {
        String questionText = edQuestion.getText().toString();

        if (questionText.equals("")) {
            Toast.makeText(getActivity(), "问题不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (teaText.equals("")) {
            Toast.makeText(getActivity(), "请选择老师！", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.e("TAG","teaText="+teaText+",index:="+teaText.indexOf("|"));
        //保存提问
        Question question = new Question();
        question.setQid(System.currentTimeMillis());
        question.setSid(StuData.loginer.getSid());
        question.setTid(Integer.parseInt(teaText.substring(0, teaText.indexOf("|"))));
        question.setQuestion(questionText);
        question.setDate(new Date());
        question.setState(0);
        question.save();
        Toast.makeText(getActivity(), "提交问题成功！", Toast.LENGTH_SHORT).show();
    }
}
