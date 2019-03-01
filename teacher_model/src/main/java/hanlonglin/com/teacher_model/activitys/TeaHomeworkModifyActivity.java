package hanlonglin.com.teacher_model.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hanlonglin.com.common.database.model.Homework_do;
import hanlonglin.com.common.database.model.Homework_publish;
import hanlonglin.com.common.database.util.DBTool;
import hanlonglin.com.teacher_model.R;

public class TeaHomeworkModifyActivity extends AppCompatActivity {
    TextView txt_question, txt_answer, txt_sname, txt_date;
    EditText ed_score, ed_remark;
    Button btn_submit;

    long hid = 0;
    int sid=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tea_activity_modify_homework);
        initView();
        initData();
    }

    private void initView() {
        txt_question = (TextView) findViewById(R.id.txt_question);
        txt_answer = (TextView) findViewById(R.id.txt_answer);
        txt_date = (TextView) findViewById(R.id.txt_date);
        txt_sname = (TextView) findViewById(R.id.txt_sname);
        ed_remark = (EditText) findViewById(R.id.ed_remark);
        ed_score = (EditText) findViewById(R.id.ed_score);
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
        sid=getIntent().getIntExtra("sid",0);
        Homework_publish homeworkPublish= DBTool.getInstance().getHomeworkPublishByHid(hid);
        Homework_do homeworkdo = DBTool.getInstance().getHomeworkdoByHidAndSid(hid, sid);
        txt_question.setText(homeworkPublish.getQuestion());
        txt_sname.setText(DBTool.getInstance().getStuBySid(sid).getSname());
        txt_answer.setText(homeworkdo.getAnswer());
        txt_date.setText(homeworkdo.getDate().toString());
    }

    private void onSubmit() {
        String score=ed_score.getText().toString();
        String remark=ed_remark.getText().toString();
        if(score.isEmpty()||remark.isEmpty()){
            Toast.makeText(this, "分数和备注不能为空！", Toast.LENGTH_SHORT).show();
            return ;
        }
        Homework_do homeworkdo = DBTool.getInstance().getHomeworkdoByHidAndSid(hid, sid);
        homeworkdo.setScore(Integer.parseInt(score));
        homeworkdo.setRemark(remark);
        homeworkdo.setState(1);
        homeworkdo.save();

        Toast.makeText(this, "批改成功", Toast.LENGTH_SHORT).show();
    }


    public static void actionStart(long hid,int sid, Context context) {
        Intent intent = new Intent(context, TeaHomeworkModifyActivity.class);
        intent.putExtra("hid", hid);
        intent.putExtra("sid", sid);
        context.startActivity(intent);
    }
}
