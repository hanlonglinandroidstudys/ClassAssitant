package hanlonglin.com.teacher_model.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import hanlonglin.com.common.AppData.TeaData;
import hanlonglin.com.common.database.model.Homework_publish;
import hanlonglin.com.common.database.util.DBTool;
import hanlonglin.com.teacher_model.R;

public class TeaFgPublishHomework extends BaseFragment {

    Spinner spinnerGid;
    Spinner spinnerPname;
    EditText edContent;
    Button btnSubmit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.tea_fg_publish_homework, container, false);
        initView(v);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initView(View v) {
        spinnerPname = (Spinner) v.findViewById(R.id.spinner_pname);
        spinnerGid = (Spinner) v.findViewById(R.id.spinner_gid);
        edContent = (EditText) v.findViewById(R.id.ed_content);
        btnSubmit = (Button) v.findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit();
            }
        });
    }

    private void initData() {
        Cursor bySQL = DataSupport.findBySQL("select distinct gid from teacher_gp where tid="+TeaData.loginer.getTid());
        List<String> gidList = new ArrayList<>();
        while (bySQL.moveToNext()) {
            int gid = bySQL.getInt(bySQL.getColumnIndex("gid"));
            gidList.add(gid + "");
        }
        bySQL.close();
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, gidList);
        spinnerGid.setAdapter(arrayAdapter1);

        Cursor bySQL2 = DataSupport.findBySQL("select distinct pid from teacher_gp where tid="+TeaData.loginer.getTid());
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

    public void onSubmit() {
        String gid = spinnerGid.getSelectedItem().toString();
        String ptext = spinnerPname.getSelectedItem().toString();
        int pid = Integer.parseInt(ptext.substring(0, ptext.indexOf("|")));
        String question = edContent.getText().toString();

        if (gid.isEmpty() || ptext.isEmpty() || question.isEmpty()) {
            Toast.makeText(getActivity(), "缺少信息！", Toast.LENGTH_SHORT).show();
            return;
        }

        //保存
        Homework_publish homework_publish = new Homework_publish();
        homework_publish.setGid(Integer.parseInt(gid));
        homework_publish.setPid(pid);
        homework_publish.setHid(System.currentTimeMillis());
        homework_publish.setDate(new Date());
        homework_publish.setQuestion(question);
        homework_publish.setTid(TeaData.loginer.getTid());
        homework_publish.save();

        //
        Toast.makeText(getActivity(), "发布成功！", Toast.LENGTH_SHORT).show();
    }
}
