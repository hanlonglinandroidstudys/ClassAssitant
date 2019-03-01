package hanlonglin.com.teacher_model.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import hanlonglin.com.common.database.model.Attend;
import hanlonglin.com.common.database.util.CodeUtil;
import hanlonglin.com.teacher_model.R;

public class AttendRecyclerAdapter extends RecyclerView.Adapter {
    List<Attend> attendList;
    Context context;

    public AttendRecyclerAdapter(List<Attend> list, Context context) {
        this.attendList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tea_item_attend, null, false);
        AttendViewHolder attendViewHolder = new AttendViewHolder(v);
        return attendViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((AttendViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return attendList.size();
    }

    public class AttendViewHolder extends RecyclerView.ViewHolder {
        TextView txt_date;

        public AttendViewHolder(View itemView) {
            super(itemView);
            txt_date = (TextView) itemView.findViewById(R.id.txt_date);
        }

        public void bindView(int pos) {
            txt_date.setText(CodeUtil.getInstance().formatDate(attendList.get(pos).getDate()));
        }
    }
}
