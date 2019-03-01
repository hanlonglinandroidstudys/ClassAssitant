package hanlonglin.com.teacher_model.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import hanlonglin.com.common.database.model.Student;
import hanlonglin.com.teacher_model.R;

public class StudentRecyclerAdapter extends RecyclerView.Adapter {
    List<Student> studentList;
    Context context;

    private OnChooseStuListener onChooseStuListener=null;

    public StudentRecyclerAdapter(List<Student> list, Context context) {
        this.studentList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tea_item_student, null, false);
        StudentHolder studentHolder = new StudentHolder(v);
        return studentHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((StudentHolder)holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class StudentHolder extends RecyclerView.ViewHolder {

        TextView txt_name;
        View v;

        public StudentHolder(View itemView) {
            super(itemView);
            v=itemView;
            txt_name = itemView.findViewById(R.id.txt_name);
        }

        public void bindView(final int position) {
            txt_name.setText(studentList.get(position).getSname());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onChooseStuListener!=null){
                        onChooseStuListener.onItemClick(studentList.get(position));
                    }
                }
            });
        }
    }

    public void setOnChooseStuListener(OnChooseStuListener onChooseStuListener) {
        this.onChooseStuListener = onChooseStuListener;
    }

    public interface OnChooseStuListener{
        public void onItemClick(Student stu);
    }
}
