package hanlonglin.com.student_model.fragment;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.litepal.crud.DataSupport;

import java.util.List;

import hanlonglin.com.common.AppData.StuData;
import hanlonglin.com.common.database.model.Notice;
import hanlonglin.com.common.database.model.Teacher;
import hanlonglin.com.common.database.model.Teacher_Gp;
import hanlonglin.com.student_model.R;
import hanlonglin.com.student_model.adapter.StuNoticeAdapter;

public class StuFgNotice extends BaseFragment {
    RecyclerView recyclerView_notice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.stu_fg_notice, container, false);
        initView(v);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initView(View v) {
        recyclerView_notice = (RecyclerView) v.findViewById(R.id.recyclerView_notice);
    }

    private void initData() {
        List<Teacher_Gp> tgpList=DataSupport.where("gid=? and pid=?",StuData.loginer.getGid()+"",StuData.loginer.getPid()+"").find(Teacher_Gp.class);
        String tids="(";
        for(Teacher_Gp tgp:tgpList){
            tids+=tgp.getTid()+",";
        }
        tids=tids.substring(0,tids.length()-1);
        tids+=")";
        List<Notice> notices=DataSupport.where("tid in "+tids).find(Notice.class);
        if(notices.size()>0){
            recyclerView_notice.setLayoutManager(new LinearLayoutManager(getActivity()));
            StuNoticeAdapter stuNoticeAdapter=new StuNoticeAdapter(notices,getActivity());
            recyclerView_notice.setAdapter(stuNoticeAdapter);
        }
    }

}
