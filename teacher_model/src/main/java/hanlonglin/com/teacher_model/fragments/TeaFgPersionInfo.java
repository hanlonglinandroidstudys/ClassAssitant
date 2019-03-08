package hanlonglin.com.teacher_model.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hanlonglin.com.common.AppData.StuData;
import hanlonglin.com.common.AppData.TeaData;
import hanlonglin.com.common.database.model.Student;
import hanlonglin.com.common.database.model.Teacher;
import hanlonglin.com.teacher_model.R;

public class TeaFgPersionInfo extends BaseFragment {

    TextView txtTid;
    TextView txtTname;

    CheckBox ck_change_passwd;
    LinearLayout li_change_passwd;
    EditText ed_passwd_old,ed_passwd_new;
    Button btn_change;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.tea_fg_personinfo, container, false);
        initView(v);
        initData();
        return v;
    }

    private void initView(View v) {
        txtTid=(TextView)v.findViewById(R.id.txt_tid);
        txtTname=(TextView)v.findViewById(R.id.txt_tname);

        ck_change_passwd=(CheckBox)v.findViewById(R.id.ck_change_passwd);
        ed_passwd_old=(EditText)v.findViewById(R.id.ed_passwd_old);
        ed_passwd_new=(EditText)v.findViewById(R.id.ed_passwd_new);
        li_change_passwd=(LinearLayout)v.findViewById(R.id.li_change_passwd);
        btn_change=(Button)v.findViewById(R.id.btn_change);

        ck_change_passwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ck_change_passwd.setAlpha(1f);
                    li_change_passwd.setVisibility(View.VISIBLE);
                }else{
                    ck_change_passwd.setAlpha(0.3f);
                    li_change_passwd.setVisibility(View.GONE);
                }
            }
        });

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChangePasswd();
            }
        });
    }

    private void initData() {
        txtTid.setText(TeaData.loginer.getTid() + "");
        txtTname.setText(TeaData.loginer.getTname());
    }

    private void onChangePasswd(){
        String passwd_old=ed_passwd_old.getText().toString();
        String passwd_new=ed_passwd_new.getText().toString();
        if(passwd_new.isEmpty()||passwd_old.isEmpty()){
            Toast.makeText(getActivity(), "密码不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }

        List<Teacher> teaList = DataSupport.where("tid=?", TeaData.loginer.getTid()+ "").find(Teacher.class);
        if (teaList.size() > 0) {
            Teacher tea = teaList.get(0);
            if (tea.getTpasswd().equals(passwd_old)) {
                 //验证成功 更改
                tea.setTpasswd(passwd_new);
                tea.save();
                Toast.makeText(getActivity(), "密码修改成功！", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "原密码验证失败！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
