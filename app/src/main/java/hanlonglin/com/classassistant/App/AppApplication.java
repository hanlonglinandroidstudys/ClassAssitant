package hanlonglin.com.classassistant.App;

import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;

import org.litepal.LitePalApplication;
import org.litepal.util.LogUtil;

import hanlonglin.com.classassistant.BuildConfig;
import hanlonglin.com.common.AppData.CommonApplication;
import hanlonglin.com.common.database.util.DBTool;
import hanlonglin.com.common.database.util.PerferenceUtil;

public class AppApplication extends CommonApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("AppApplication", "启动主Application...");

        if(PerferenceUtil.checkIsFirst(getApplicationContext()))
            DBTool.getInstance().mockData();
    }

}
