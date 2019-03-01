package hanlonglin.com.teacher_model.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.List;

import hanlonglin.com.common.AppData.StuData;
import hanlonglin.com.common.database.model.Homework_do;
import hanlonglin.com.common.database.util.DBTool;
import hanlonglin.com.teacher_model.R;
import hanlonglin.com.teacher_model.adapter.StuHomeworkdoAdapter;

public class TeaHomeworkScoreActivity extends AppCompatActivity {
    TextView txt_tname;
    RecyclerView recyclerView_score;

    int sid=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tea_activity_homework_score);
        initView();
        initData();
    }

    private void initView() {
        txt_tname=(TextView)findViewById(R.id.txt_tname);
        recyclerView_score=(RecyclerView)findViewById(R.id.recyclerView_score);
    }

    private void initData() {
        int sid=getIntent().getIntExtra("sid",0);
        txt_tname.setText(DBTool.getInstance().getStuBySid(sid).getSname());
        List<Homework_do> homework_doList=DataSupport.where("sid=? and state=1",sid+"").find(Homework_do.class);
        recyclerView_score.setLayoutManager(new LinearLayoutManager(this));
        StuHomeworkdoAdapter stuHomeworkdoAdapter=new StuHomeworkdoAdapter(homework_doList,this);
        recyclerView_score.setAdapter(stuHomeworkdoAdapter);
    }

    public static void actionStart(int sid,Context context){
        Intent intent=new Intent(context,TeaHomeworkScoreActivity.class);
        intent.putExtra("sid",sid);
        context.startActivity(intent);
    }
}
