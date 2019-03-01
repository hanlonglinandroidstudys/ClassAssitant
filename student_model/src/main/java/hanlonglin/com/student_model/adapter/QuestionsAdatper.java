package hanlonglin.com.student_model.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import hanlonglin.com.common.database.model.Question;
import hanlonglin.com.common.database.util.CodeUtil;
import hanlonglin.com.common.database.util.DBTool;
import hanlonglin.com.student_model.R;

public class QuestionsAdatper extends RecyclerView.Adapter {
    List<Question> questionList;
    Context context;

    public QuestionsAdatper(List<Question> list, Context context) {
        this.questionList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.stu_item_question, null, false);
        QuestionViewHolder questionViewHolder = new QuestionViewHolder(v);
        return questionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((QuestionViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder {

        TextView txt_date;
        TextView txt_answer;
        TextView txt_question;
        TextView txt_tname;
        TextView txt_state;

        public QuestionViewHolder(View itemView) {
            super(itemView);
            txt_date = (TextView) itemView.findViewById(R.id.txt_date);
            txt_answer = (TextView) itemView.findViewById(R.id.txt_answer);
            txt_question = (TextView) itemView.findViewById(R.id.txt_question);
            txt_tname = (TextView) itemView.findViewById(R.id.txt_tname);
            txt_state = (TextView) itemView.findViewById(R.id.txt_state);
        }

        public void bindView(int position) {
            txt_date.setText(CodeUtil.getInstance().formatDate(questionList.get(position).getDate()));
            txt_tname.setText(DBTool.getInstance().getTeaByTid(questionList.get(position).getTid()).getTname());
            txt_answer.setText(questionList.get(position).getAnswer());
            txt_question.setText(questionList.get(position).getQuestion());
            if (questionList.get(position).getState() == 0) {
                txt_state.setText("未审核");
            } else {
                txt_state.setText("已审核");
            }
        }
    }
}
