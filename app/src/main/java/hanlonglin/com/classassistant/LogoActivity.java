package hanlonglin.com.classassistant;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogoActivity extends AppCompatActivity {
    @BindView(R.id.txt_seconds)
    TextView txtSeconds;

    private int seconds = 3;
    private final static int WHAT_SEND = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_logo);
        ButterKnife.bind(this);
        handler.sendEmptyMessageDelayed(WHAT_SEND, 1000);

        txtSeconds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeMessages(WHAT_SEND);
                gotoLogin();
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WHAT_SEND:
                    if (seconds >= 0) {
                        txtSeconds.setText("跳过"+seconds+"s");
                        seconds--;
                        handler.sendEmptyMessageDelayed(WHAT_SEND, 1000);
                    }else{
                        gotoLogin();
                    }
                    break;
            }

        }
    };

    private void gotoLogin() {
        startActivity(new Intent(LogoActivity.this, LoginActivity.class));
        finish();
    }
}
