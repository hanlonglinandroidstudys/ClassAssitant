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

import java.util.Date;

import hanlonglin.com.common.database.model.Question;
import hanlonglin.com.common.database.util.CodeUtil;
import hanlonglin.com.common.database.util.DBTool;
import hanlonglin.com.teacher_model.R;
import hanlonglin.com.teacher_model.fragments.TeaFgQuestions;

public class TeaAnswerActivity extends AppCompatActivity {
    TextView txt_question, txt_sname, txt_date;
    EditText ed_answer;
    Button btn_submit;

    long qid = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tea_activity_answer);
        initView();
        initData();
    }

    private void initView() {
        txt_question = (TextView) findViewById(R.id.txt_question);
        txt_sname = (TextView) findViewById(R.id.txt_sname);
        txt_date = (TextView) findViewById(R.id.txt_date);
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
        qid = getIntent().getLongExtra("qid", 0);
        Question question = DBTool.getInstance().getQuestionByQid(qid);
        txt_question.setText(question.getQuestion());
        txt_sname.setText(DBTool.getInstance().getStuBySid(question.getSid()).getSname());
        txt_date.setText(CodeUtil.getInstance().formatDate(question.getDate()));
        ed_answer.setText(question.getAnswer());
    }

    private void onSubmit() {
        String answer = ed_answer.getText().toString();
        if (answer.isEmpty()) {
            Toast.makeText(this, "回答不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Question question = DBTool.getInstance().getQuestionByQid(qid);
        question.setState(1);
        question.setAnswer(answer);
        question.setDate(new Date());
        question.save();
        Toast.makeText(this, "回答完成！", Toast.LENGTH_SHORT).show();
    }

    public static void actionStart(long qid, Context context) {
        Intent intent = new Intent(context, TeaAnswerActivity.class);
        intent.putExtra("qid", qid);
        context.startActivity(intent);
    }
}
