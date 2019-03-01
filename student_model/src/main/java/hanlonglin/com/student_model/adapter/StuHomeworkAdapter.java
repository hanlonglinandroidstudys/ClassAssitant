package hanlonglin.com.student_model.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import hanlonglin.com.common.database.model.Homework_publish;
import hanlonglin.com.common.database.util.CodeUtil;
import hanlonglin.com.common.database.util.DBTool;
import hanlonglin.com.student_model.R;
import hanlonglin.com.student_model.activitys.SubmitHomeworkActivity;

public class StuHomeworkAdapter extends RecyclerView.Adapter {
    List<Homework_publish> homework_publishList;
    Context context;

    public StuHomeworkAdapter(List<Homework_publish> list, Context context) {
        this.context = context;
        this.homework_publishList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.stu_item_homework, null, false);
        StuHomeworkViewHolder homeworkViewHolder = new StuHomeworkViewHolder(v);
        return homeworkViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((StuHomeworkViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return homework_publishList.size();
    }

    public class StuHomeworkViewHolder extends RecyclerView.ViewHolder {
        TextView txt_date;
        TextView txt_tname;
        TextView txt_question;

        View v;
        public StuHomeworkViewHolder(View itemView) {
            super(itemView);
            txt_date = (TextView) itemView.findViewById(R.id.txt_date);
            txt_tname = (TextView) itemView.findViewById(R.id.txt_tname);
            txt_question = (TextView) itemView.findViewById(R.id.txt_question);
            v=itemView;
        }

        public void bindView(final int position) {
            txt_question.setText(homework_publishList.get(position).getQuestion());
            txt_tname.setText(DBTool.getInstance().getTeaByTid(homework_publishList.get(position).getTid()).getTname());
            txt_date.setText(CodeUtil.getInstance().formatDate(homework_publishList.get(position).getDate()));

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SubmitHomeworkActivity.actionStart(homework_publishList.get(position).getHid(),context);
                }
            });
        }
    }
}
