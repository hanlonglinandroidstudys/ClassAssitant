package hanlonglin.com.classassistant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
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
    @BindView(R.id.image_login)
    CircleImageView imageLogin;
    @BindView(R.id.li_content)
    CardView liContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
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
                        imageLogin.setImageResource(R.drawable.student);
                        break;
                    case R.id.ra_teacher:
                        REGISTER_TYPE = TYPE_TEACHER;
                        imageLogin.setImageResource(R.drawable.teacher);
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
            if (checkStuHasRegister(Integer.parseInt(id))) {
                Toast.makeText(this, "该学生id已经注册！请更换id", Toast.LENGTH_SHORT).show();
                return;
            }
            Student student = new Student();
            student.setSid(Integer.parseInt(id));
            student.setSpasswd(passwd);
            student.setSname(name);
            student.setGid(Integer.parseInt(gid));
            student.setPid(Integer.parseInt(pid));
            student.save();
            //Toast.makeText(this, "恭喜你注册成功,请前往登陆！", Toast.LENGTH_SHORT).show();
            showSuccessDialog();
        } else if (REGISTER_TYPE == TYPE_TEACHER) {
            if (checkTeaHasRegister(Integer.parseInt(id))) {
                Toast.makeText(this, "该教师id已经注册！请更换id", Toast.LENGTH_SHORT).show();
                return;
            }
            Teacher teacher = new Teacher();
            teacher.setTid(Integer.parseInt(id));
            teacher.setTpasswd(passwd);
            teacher.setTname(name);
            teacher.save();
            //Toast.makeText(this, "恭喜你注册成功，请前往登陆！", Toast.LENGTH_SHORT).show();
            showSuccessDialog();
        }
    }

    private boolean checkStuHasRegister(int sid) {
        List<Student> list = DataSupport.where("sid=?", sid + "").find(Student.class);
        if (list.size() > 0)
            return true;
        else
            return false;
    }

    private boolean checkTeaHasRegister(int tid) {
        List<Teacher> list = DataSupport.where("tid=?", tid + "").find(Teacher.class);
        if (list.size() > 0)
            return true;
        else
            return false;
    }

    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("成功");
        builder.setMessage("恭喜你，注册成功，快去登录吧！");
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
        builder.show();
    }
}
