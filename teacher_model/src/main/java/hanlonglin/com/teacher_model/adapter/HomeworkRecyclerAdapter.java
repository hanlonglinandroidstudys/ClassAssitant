package hanlonglin.com.teacher_model.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.List;

import hanlonglin.com.common.database.model.Homework_do;
import hanlonglin.com.common.database.model.Student;
import hanlonglin.com.common.database.util.CodeUtil;
import hanlonglin.com.common.database.util.DBTool;
import hanlonglin.com.teacher_model.R;
import hanlonglin.com.teacher_model.activitys.TeaHomeworkModifyActivity;

public class HomeworkRecyclerAdapter extends RecyclerView.Adapter {
    List<Homework_do> homework_doList;
    Context context;

    public HomeworkRecyclerAdapter(List<Homework_do> list, Context context) {
        this.homework_doList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tea_item_homework, null, false);
        HomeworkViewHolder homeworkViewHolder = new HomeworkViewHolder(v);
        return homeworkViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((HomeworkViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return homework_doList.size();
    }

    public class HomeworkViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name, txt_date, txt_question, txt_answer;
        Button btn_auth;
        View view;

        public HomeworkViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            txt_name = (TextView) itemView.findViewById(R.id.txt_name);
            txt_date = (TextView) itemView.findViewById(R.id.txt_date);
            txt_answer = (TextView) itemView.findViewById(R.id.txt_answer);
            txt_question = (TextView) itemView.findViewById(R.id.txt_question);
            btn_auth=(Button)itemView.findViewById(R.id.btn_auth);
        }

        public void bindView(final int position) {
            txt_name.setText(DBTool.getInstance().getStuBySid(homework_doList.get(position).getSid()).getSname());
            txt_question.setText(DBTool.getInstance().getHomeworkPublishByHid(homework_doList.get(position).getHid()).getQuestion());
            txt_answer.setText(homework_doList.get(position).getAnswer());
            txt_date.setText(CodeUtil.getInstance().formatDate(homework_doList.get(position).getDate()));

            btn_auth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //进入批改作业
                    TeaHomeworkModifyActivity.actionStart(homework_doList.get(position).getHid(),homework_doList.get(position).getSid(),context);
                }
            });
        }
    }
}
