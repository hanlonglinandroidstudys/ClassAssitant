package hanlonglin.com.teacher_model.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;

import hanlonglin.com.common.database.model.Attend;
import hanlonglin.com.common.database.model.Student;
import hanlonglin.com.common.database.util.DBTool;
import hanlonglin.com.teacher_model.R;
import hanlonglin.com.teacher_model.adapter.AttendRecyclerAdapter;

public class TeaCheckAttendActivity extends AppCompatActivity {
    TextView txt_tname;
    CheckBox ck_attend;
    Button btn_submit;
    RecyclerView recyclerView_attend_record;

    int sid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tea_activity_check_attend);
        initView();
        initData();
    }

    private void initView() {
        txt_tname = (TextView) findViewById(R.id.txt_tname);
        ck_attend = (CheckBox) findViewById(R.id.ck_attend);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        recyclerView_attend_record = (RecyclerView) findViewById(R.id.recyclerView_attend_record);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit();
            }
        });
    }

    private void initData() {
        sid = getIntent().getIntExtra("sid", 0);
        Student student=DBTool.getInstance().getStuBySid(sid);
        txt_tname.setText(student.getSname());
        List<Attend> attendList = DataSupport.where("sid=?", sid + "").find(Attend.class);
        AttendRecyclerAdapter attendRecyclerAdapter = new AttendRecyclerAdapter(attendList, this);
        recyclerView_attend_record.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_attend_record.setAdapter(attendRecyclerAdapter);
    }

    private void onSubmit() {
        boolean isAttend = ck_attend.isChecked();
        if (isAttend) {
            Toast.makeText(this, "只记录未到学生的考勤！", Toast.LENGTH_SHORT).show();
            return;
        }
        Attend attend = new Attend();
        attend.setSid(sid);
        attend.setDate(new Date());
        attend.setAttend(0);
        attend.save();
        Toast.makeText(this, "记录缺勤成功！", Toast.LENGTH_SHORT).show();

        initData();
    }

    public static void actionStart(int sid, Context context) {
        Intent intent = new Intent(context, TeaCheckAttendActivity.class);
        intent.putExtra("sid", sid);
        context.startActivity(intent);
    }
}
