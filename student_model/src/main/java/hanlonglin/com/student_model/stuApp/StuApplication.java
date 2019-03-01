package hanlonglin.com.student_model.stuApp;

import android.util.Log;

import org.litepal.LitePalApplication;
import org.litepal.crud.DataSupport;

import java.util.List;

import hanlonglin.com.common.AppData.CommonApplication;
import hanlonglin.com.common.AppData.StuData;
import hanlonglin.com.common.database.model.Student;
import hanlonglin.com.common.database.util.DBTool;
import hanlonglin.com.common.database.util.PerferenceUtil;

public class StuApplication extends CommonApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("StuApplication", "StuApplication初始化");

        if (PerferenceUtil.checkIsFirst(this))
            DBTool.getInstance().mockData();

        //模拟数据
        int sid = 5;
        List<Student> stuList = DataSupport.where("sid=?", sid + "").find(Student.class);
        if (stuList.size() > 0)
            StuData.loginer = stuList.get(0);
    }
}
