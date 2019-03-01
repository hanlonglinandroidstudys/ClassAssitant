package hanlonglin.com.classassistant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.prefs.PreferencesFactory;

import hanlonglin.com.common.database.util.DBTool;
import hanlonglin.com.common.database.util.PerferenceUtil;

public class LogoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        handler.sendEmptyMessageDelayed(0,3000);
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            //检查是否第一次进入app
//            SharedPreferences defaultSharedPreferences =PreferenceManager.getDefaultSharedPreferences(LogoActivity.this);
//            boolean isfirst = defaultSharedPreferences.getBoolean("isfirst", true);
//            if(isfirst) {
//                //第一次 模拟数据
//                DBTool.getInstance().mockData();
//                SharedPreferences.Editor editor = defaultSharedPreferences.edit();
//                editor.putBoolean("isfirst", false);
//                editor.commit();
//            }
            if(PerferenceUtil.checkIsFirst(getApplicationContext()))
                DBTool.getInstance().mockData();

            startActivity(new Intent(LogoActivity.this,LoginActivity.class));
            finish();
        }
    };
}
