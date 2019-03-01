package hanlonglin.com.teacher_model.teaApp;

import android.app.Application;
import android.util.Log;

import org.litepal.LitePalApplication;
import org.litepal.crud.DataSupport;

import java.util.List;

import hanlonglin.com.common.AppData.CommonApplication;
import hanlonglin.com.common.AppData.TeaData;
import hanlonglin.com.common.database.model.Teacher;
import hanlonglin.com.common.database.util.DBTool;
import hanlonglin.com.common.database.util.PerferenceUtil;

public class TeaApplication extends CommonApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "加载Teacher Application...");
        if (PerferenceUtil.checkIsFirst(this))
            DBTool.getInstance().mockData();

        //模拟数据
        int tid = 1;
        List<Teacher> teaList = DataSupport.where("tid=?", tid + "").find(Teacher.class);
        if (teaList.size() > 0)
            TeaData.loginer = teaList.get(0);
    }
}
