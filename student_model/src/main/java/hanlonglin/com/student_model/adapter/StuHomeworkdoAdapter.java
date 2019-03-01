package hanlonglin.com.student_model.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import hanlonglin.com.common.database.model.Homework_do;
import hanlonglin.com.common.database.model.Homework_publish;
import hanlonglin.com.common.database.util.CodeUtil;
import hanlonglin.com.common.database.util.DBTool;
import hanlonglin.com.student_model.R;

public class StuHomeworkdoAdapter extends RecyclerView.Adapter {
    List<Homework_do> homework_doList;
    Context context;

    public StuHomeworkdoAdapter(List<Homework_do> list, Context context) {
        this.context = context;
        this.homework_doList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.stu_item_homeworkdo, null, false);
        StuHomeworkdoViewHolder stuHomeworkdoViewHolder=new StuHomeworkdoViewHolder(v);
        return stuHomeworkdoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((StuHomeworkdoViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return homework_doList.size();
    }

    public class StuHomeworkdoViewHolder extends RecyclerView.ViewHolder {
        TextView txt_date;
        TextView txt_answer;
        TextView txt_question;
        TextView txt_score;
        TextView txt_state;

        public StuHomeworkdoViewHolder(View itemView) {
            super(itemView);
            txt_date = (TextView) itemView.findViewById(R.id.txt_date);
            txt_answer = (TextView) itemView.findViewById(R.id.txt_answer);
            txt_question = (TextView) itemView.findViewById(R.id.txt_question);
            txt_score=(TextView)itemView.findViewById(R.id.txt_score);
            txt_state=(TextView)itemView.findViewById(R.id.txt_state);
        }

        public void bindView(int position) {
            txt_date.setText(CodeUtil.getInstance().formatDate(homework_doList.get(position).getDate()));
            txt_score.setText(homework_doList.get(position).getScore()+"");
            txt_answer.setText(homework_doList.get(position).getAnswer());
            txt_question.setText(DBTool.getInstance().getHomeworkPublishByHid(homework_doList.get(position).getHid()).getQuestion());
            if(homework_doList.get(position).getState()==0){
                txt_state.setText("未审核");
            }else{
                txt_state.setText("已审核");
            }
        }
    }
}
