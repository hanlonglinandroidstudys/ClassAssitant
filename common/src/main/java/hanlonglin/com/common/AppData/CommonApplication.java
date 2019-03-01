package hanlonglin.com.common.AppData;

import android.app.Application;
import android.arch.lifecycle.livedata.core.BuildConfig;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;

import org.litepal.LitePalApplication;

public class CommonApplication extends LitePalApplication {
    private static CommonApplication mApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication=this;
        initArouter(mApplication);
    }

    public static CommonApplication getInstance(){
        return  mApplication;
    }

    //初始化arounter
    public void initArouter(Application mApplication) {
        Log.e("TAG", "初始化ARouter");
        if (BuildConfig.DEBUG) {           // These two lines must be written before init, otherwise these configurations will be invalid in the init process
            ARouter.openLog();     // Print log
            ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
            Log.e("TAG", "debug模式启动ARounter...");
        }
        ARouter.init(mApplication); // As early as possible, it is recommended to initialize in the Application
    }
}
