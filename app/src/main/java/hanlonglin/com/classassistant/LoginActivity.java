package hanlonglin.com.classassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hanlonglin.com.common.AppData.StuData;
import hanlonglin.com.common.AppData.TeaData;
import hanlonglin.com.common.database.model.Student;
import hanlonglin.com.common.database.model.Teacher;
import hanlonglin.com.common.database.util.ARouterMap;


@Route(path = ARouterMap.AC_LOGIN)
public class LoginActivity extends AppCompatActivity {


    private final static int TYPE_STUDENT = 0;
    private final static int TYPE_TEACHER = 1;
    int LOGIN_TYPE = TYPE_STUDENT;
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
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.txt_goto_register)
    TextView txtGotoRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        setRadioGroup();

        txtGotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void setRadioGroup() {
        raGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.ra_student:
                        LOGIN_TYPE = TYPE_STUDENT;
                        break;
                    case R.id.ra_teacher:
                        LOGIN_TYPE = TYPE_TEACHER;
                        break;
                }
            }
        });
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        String id = tetId.getText().toString();
        String passwd = tetPassword.getText().toString();
        if (TextUtils.isEmpty(id)) {
            tetId.setError("id不能为空！");
            return;
        }
        if (TextUtils.isEmpty(passwd)) {
            tetPassword.setError("密码不能为空！");
            return;
        }
        checkLogin(LOGIN_TYPE, id, passwd);
    }

    private void checkLogin(int login_type, String id, String passwd) {
        if (login_type == TYPE_STUDENT) {
            List<Student> studentList = DataSupport.where("sid=?", id + "").find(Student.class);
            if (studentList.size() > 0) {
                Student stu = studentList.get(0);
                if (stu.getSpasswd().equals(passwd)) {
                    StuData.loginer = studentList.get(0);
                    gotoStudentMain();
                    Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "登陆失败！账号或密码错误！", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "该用户未注册！", Toast.LENGTH_SHORT).show();
            }
        } else if (login_type == TYPE_TEACHER) {
            List<Teacher> teaList = DataSupport.where("tid=?", id + "").find(Teacher.class);
            if (teaList.size() > 0) {
                Teacher teacher = teaList.get(0);
                if (teacher.getTpasswd().equals(passwd)) {
                    TeaData.loginer = teaList.get(0);
                    gotoTeacherMain();
                    Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "登陆失败！账号或密码错误！", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "该用户未注册！", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "未选择类型", Toast.LENGTH_SHORT).show();
        }
    }

    private void gotoStudentMain() {
//        try {
//            Intent intent = new Intent("student_main");
//            startActivity(intent);
//            finish();
//        } catch (Exception e) {
//            e.printStackTrace();
//            Toast.makeText(this, "登陆失败！错误：" + e.getMessage(), Toast.LENGTH_SHORT).show();
//        }

        ARouter.getInstance().build(ARouterMap.AC_STUDENT_MAIN).navigation();
        finish();
    }

    private void gotoTeacherMain() {
//        try {
//            Intent intent = new Intent("teacher_main");
//            startActivity(intent);
//            finish();
//        } catch (Exception e) {
//            e.printStackTrace();
//            Toast.makeText(this, "登陆失败！错误：" + e.getMessage(), Toast.LENGTH_SHORT).show();
//        }

        ARouter.getInstance().build(ARouterMap.AC_TEACHER_MAIN).navigation();
        finish();
    }

}
