package hanlonglin.com.student_model.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import hanlonglin.com.common.database.model.Notice;
import hanlonglin.com.common.database.util.CodeUtil;
import hanlonglin.com.common.database.util.DBTool;
import hanlonglin.com.student_model.R;

public class StuNoticeAdapter extends RecyclerView.Adapter {
    List<Notice> noticeList;
    Context context;

    public StuNoticeAdapter(List<Notice> list, Context context) {
        this.noticeList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.stu_item_notice, null, false);
        NoticeViewHolder noticeViewHolder = new NoticeViewHolder(v);
        return noticeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((NoticeViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    public class NoticeViewHolder extends RecyclerView.ViewHolder {

        TextView txt_date;
        TextView txt_tname;
        TextView txt_content;

        public NoticeViewHolder(View itemView) {
            super(itemView);
            txt_content = (TextView) itemView.findViewById(R.id.txt_content);
            txt_date = (TextView) itemView.findViewById(R.id.txt_date);
            txt_tname = (TextView) itemView.findViewById(R.id.txt_tname);
        }

        public void bindView(int position) {
            txt_tname.setText(DBTool.getInstance().getTeaByTid(noticeList.get(position).getTid()).getTname());
            txt_date.setText(CodeUtil.getInstance().formatDate(noticeList.get(position).getDate()));
            txt_content.setText(noticeList.get(position).getContent());
        }
    }
}
