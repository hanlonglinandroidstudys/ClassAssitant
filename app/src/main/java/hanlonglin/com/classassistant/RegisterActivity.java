package hanlonglin.com.classassistant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hanlonglin.com.common.database.model.Student;
import hanlonglin.com.common.database.model.Teacher;
import hanlonglin.com.common.database.util.ARouterMap;

@Route(path = ARouterMap.AC_REGISTER)
public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.ra_student)
    RadioButton raStudent;
    @BindView(R.id.ra_teacher)
    RadioButton raTeacher;
    @BindView(R.id.raGroup)
    RadioGroup raGroup;
    @BindView(R.id.tet_id)
    TextInputEditText tetId;
    @BindView(R.id.tlayout_id)
    TextInputLayout tlayoutId;
    @BindView(R.id.tet_password)
    TextInputEditText tetPassword;
    @BindView(R.id.tlayout_password)
    TextInputLayout tlayoutPassword;
    @BindView(R.id.tet_password_confirm)
    TextInputEditText tetPasswordConfirm;
    @BindView(R.id.tlayout_password_confirm)
    TextInputLayout tlayoutPasswordConfirm;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.tet_gid)
    TextInputEditText tetGid;
    @BindView(R.id.tlayout_gid)
    TextInputLayout tlayoutGid;
    @BindView(R.id.tet_pid)
    TextInputEditText tetPid;
    @BindView(R.id.tlayout_pid)
    TextInputLayout tlayoutPid;
    @BindView(R.id.tet_name)
    TextInputEditText tetName;
    @BindView(R.id.tlayout_name)
    TextInputLayout tlayoutName;

    private final static int TYPE_STUDENT = 0;
    private final static int TYPE_TEACHER = 1;
    int REGISTER_TYPE = TYPE_STUDENT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        setRadioGroup();
    }

    private void setRadioGroup() {
        raGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.ra_student:
                        REGISTER_TYPE = TYPE_STUDENT;
                        break;
                    case R.id.ra_teacher:
                        REGISTER_TYPE = TYPE_TEACHER;
                        break;
                }
            }
        });
    }

    @OnClick(R.id.btn_register)
    public void onViewClicked() {

        String gid = tetGid.getText().toString();
        String pid = tetPid.getText().toString();
        String name = tetName.getText().toString();
        String id = tetId.getText().toString();
        String passwd = tetPassword.getText().toString();
        String passwd_confirm = tetPasswordConfirm.getText().toString();

        if (TextUtils.isEmpty(name)) {
            tetName.setError("姓名不能为空！");
            return;
        }
        if (TextUtils.isEmpty(id)) {
            tetId.setError("id不能为空！");
            return;
        }
        if (TextUtils.isEmpty(passwd)) {
            tetPassword.setError("密码不能为空！");
            return;
        }
        if (TextUtils.isEmpty(passwd_confirm)) {
            tetPasswordConfirm.setError("密码不能为空！");
            return;
        }
        if (!TextUtils.equals(passwd, passwd_confirm)) {
            Toast.makeText(this, "两次输入的密码不一样", Toast.LENGTH_SHORT).show();
            return;
        }

        doRegister(gid, pid, name, id, passwd);
    }

    private void doRegister(String gid, String pid, String name, String id, String passwd) {
        if (REGISTER_TYPE == TYPE_STUDENT) {
            if (gid.equals("") || pid.equals("")) {
                Toast.makeText(this, "年级和专业id不能为空！", Toast.LENGTH_SHORT).show();
                return;
            }
            if(checkStuHasRegister(Integer.parseInt(id))){
                Toast.makeText(this, "该学生id已经注册！请更换id", Toast.LENGTH_SHORT).show();
                return ;
            }
            Student student = new Student();
            student.setSid(Integer.parseInt(id));
            student.setSpasswd(passwd);
            student.setSname(name);
            student.setGid(Integer.parseInt(gid));
            student.setPid(Integer.parseInt(pid));
            student.save();
            Toast.makeText(this, "恭喜你注册成功,请前往登陆！", Toast.LENGTH_SHORT).show();
        } else if (REGISTER_TYPE == TYPE_TEACHER) {
            if(checkTeaHasRegister(Integer.parseInt(id))){
                Toast.makeText(this, "该教师id已经注册！请更换id", Toast.LENGTH_SHORT).show();
                return ;
            }
            Teacher teacher=new Teacher();
            teacher.setTid(Integer.parseInt(id));
            teacher.setTpasswd(passwd);
            teacher.setTname(name);
            teacher.save();
            Toast.makeText(this, "恭喜你注册成功，请前往登陆！", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkStuHasRegister(int sid){
        List<Student> list=DataSupport.where("sid=?",sid+"").find(Student.class);
        if(list.size()>0)
            return true;
        else
            return false;
    }
    private boolean checkTeaHasRegister(int tid){
        List<Teacher> list=DataSupport.where("tid=?",tid+"").find(Teacher.class);
        if(list.size()>0)
            return true;
        else
            return false;
    }
}
