package hanlonglin.com.student_model.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;

import hanlonglin.com.common.AppData.StuData;
import hanlonglin.com.common.database.model.Homework_do;
import hanlonglin.com.common.database.model.Homework_publish;
import hanlonglin.com.common.database.util.DBTool;
import hanlonglin.com.student_model.R;

public class SubmitHomeworkActivity extends AppCompatActivity {
    TextView txt_question;
    EditText ed_answer;
    Button btn_submit;

    long hid = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_activity_submit_homework);
        initView();
        initData();
    }

    private void initView() {
        txt_question = (TextView) findViewById(R.id.txt_question);
        ed_answer = (EditText) findViewById(R.id.ed_answer);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit();
            }
        });
    }

    private void initData() {
        hid = getIntent().getLongExtra("hid", 0);
        Homework_publish homework_publish=DBTool.getInstance().getHomeworkPublishByHid(hid);
        txt_question.setText(homework_publish.getQuestion());
    }


    private void onSubmit() {
        if (isHaveSubmit()) {
            Toast.makeText(this, "已经提交过，不能重复提交！", Toast.LENGTH_SHORT).show();
            return;
        }

        String answer = ed_answer.getText().toString();
        if (answer.isEmpty()) {
            Toast.makeText(this, "答案不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }

        Homework_do homework_do = new Homework_do();
        homework_do.setHid(hid);
        homework_do.setPid(StuData.loginer.getPid());
        homework_do.setGid(StuData.loginer.getGid());
        homework_do.setAnswer(answer);
        homework_do.setState(0);
        homework_do.setSid(StuData.loginer.getSid());
        homework_do.setDate(new Date());
        homework_do.save();

        Toast.makeText(this, "作业提交成功！", Toast.LENGTH_SHORT).show();
    }

    private boolean isHaveSubmit() {
        List<Homework_do> homework_dos = DataSupport.where("hid=? and sid=?", hid + "", StuData.loginer.getSid() + "").find(Homework_do.class);
        if (homework_dos.size() > 0)
            return true;
        else
            return false;
    }


    public static void actionStart(long hid, Context context) {
        Intent intent = new Intent(context, SubmitHomeworkActivity.class);
        intent.putExtra("hid", hid);
        context.startActivity(intent);
    }
}
