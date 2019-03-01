package hanlonglin.com.teacher_model.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hanlonglin.com.common.AppData.TeaData;
import hanlonglin.com.common.database.model.Notice;
import hanlonglin.com.teacher_model.R;

public class TeaFgPublishNotice extends BaseFragment {
    EditText edContent;
    Button btnSubmit;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.tea_fg_publish_notice, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        edContent = (EditText) v.findViewById(R.id.ed_content);
        btnSubmit = (Button) v.findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onSubmit() {
        String text = edContent.getText().toString();
        if (text.isEmpty()) {
            Toast.makeText(getActivity(), "公告不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }

        Notice notice = new Notice();
        notice.setContent(text);
        notice.setTid(TeaData.loginer.getTid());
        notice.setNid(System.currentTimeMillis());
        notice.setDate(new Date());
        notice.save();

        Toast.makeText(getActivity(), "发布公告成功！", Toast.LENGTH_SHORT).show();
    }
}
