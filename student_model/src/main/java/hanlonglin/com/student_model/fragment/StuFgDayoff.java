package hanlonglin.com.student_model.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hanlonglin.com.common.AppData.StuData;
import hanlonglin.com.common.database.model.Dayoff;
import hanlonglin.com.common.database.model.Teacher;
import hanlonglin.com.common.database.model.Teacher_Gp;
import hanlonglin.com.student_model.R;

public class StuFgDayoff extends BaseFragment {

    Spinner spinnerTeachers;
    EditText edReason;
    Button btnSubmit;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.stu_fg_dayoff, container, false);
        initView(v);
        initData();
        return v;
    }

    private void initView(View v) {
        edReason=(EditText)v.findViewById(R.id.ed_reason);
        btnSubmit=(Button)v.findViewById(R.id.btn_submit);
        spinnerTeachers=(Spinner)v.findViewById(R.id.spinner_teachers);

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
        String reason=edReason.getText().toString();
        if(reason.isEmpty())
        {
            Toast.makeText(getActivity(), "请写明请假原因和日期！", Toast.LENGTH_SHORT).show();
            return;
        }
        String teaText=spinnerTeachers.getSelectedItem().toString();

        //保存
        Dayoff dayoff=new Dayoff();
        dayoff.setTid(Integer.parseInt(teaText.substring(0, teaText.indexOf("|"))));
        dayoff.setSid(StuData.loginer.getSid());
        dayoff.setReason(reason);
        dayoff.setDate(new Date());
        dayoff.setDid(System.currentTimeMillis());
        dayoff.setState(0);
        dayoff.save();

        Toast.makeText(getActivity(), "申请请假成功！", Toast.LENGTH_SHORT).show();
    }
}
