package hanlonglin.com.teacher_model.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import hanlonglin.com.common.database.model.Dayoff;
import hanlonglin.com.common.database.util.CodeUtil;
import hanlonglin.com.common.database.util.DBTool;
import hanlonglin.com.teacher_model.R;

public class DayoffAdapter extends RecyclerView.Adapter {
    List<Dayoff> dayoffList;
    Context context;

    private OnAuthedListener onAuthedListener = null;

    public DayoffAdapter(List<Dayoff> list, Context context) {
        this.dayoffList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tea_item_dayoff_requre, null, false);
        DayoffViewHolder dayoffViewHolder=new DayoffViewHolder(v);
        return dayoffViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((DayoffViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return dayoffList.size();
    }

    public class DayoffViewHolder extends RecyclerView.ViewHolder {

        TextView txt_date;
        TextView txt_reason;
        TextView txt_sname;
        TextView txt_state;
        Button btn_ok;

        View v;

        public DayoffViewHolder(View itemView) {
            super(itemView);
            v = itemView;
            txt_date = (TextView) itemView.findViewById(R.id.txt_date);
            txt_reason = (TextView) itemView.findViewById(R.id.txt_reason);
            txt_sname = (TextView) itemView.findViewById(R.id.txt_sname);
            txt_state = (TextView) itemView.findViewById(R.id.txt_state);
            btn_ok = (Button) itemView.findViewById(R.id.btn_ok);
        }

        public void bindView(final int position) {
            txt_date.setText(CodeUtil.getInstance().formatDate(dayoffList.get(position).getDate()));
            txt_sname.setText(DBTool.getInstance().getStuBySid(dayoffList.get(position).getSid()).getSname());
            txt_reason.setText(dayoffList.get(position).getReason());
            if (dayoffList.get(position).getState() == 0) {
                txt_state.setText("未审核");
                btn_ok.setVisibility(View.VISIBLE);
            } else {
                txt_state.setText("已审核");
                btn_ok.setVisibility(View.GONE);
            }
            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onAuthedListener != null)
                        onAuthedListener.onAuthed(dayoffList.get(position));
                }
            });

        }
    }

    public void setOnAuthedListener(OnAuthedListener onAuthedListener) {
        this.onAuthedListener = onAuthedListener;
    }

    public interface OnAuthedListener {
        public void onAuthed(Dayoff dayoff);
    }

}
