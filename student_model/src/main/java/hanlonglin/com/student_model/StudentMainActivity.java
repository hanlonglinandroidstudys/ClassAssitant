package hanlonglin.com.student_model;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import hanlonglin.com.common.AppData.StuData;
import hanlonglin.com.common.database.util.ARouterMap;
import hanlonglin.com.common.database.util.DBTool;
import hanlonglin.com.student_model.fragment.BaseFragment;
import hanlonglin.com.student_model.fragment.StuFgAsk;
import hanlonglin.com.student_model.fragment.StuFgDayoff;
import hanlonglin.com.student_model.fragment.StuFgDayoffRecord;
import hanlonglin.com.student_model.fragment.StuFgHomework;
import hanlonglin.com.student_model.fragment.StuFgNotice;
import hanlonglin.com.student_model.fragment.StuFgPersionInfo;
import hanlonglin.com.student_model.fragment.StuFgQuestions;
import hanlonglin.com.student_model.fragment.StuFgScore;

@Route(path = ARouterMap.AC_STUDENT_MAIN)
public class StudentMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fgManager = null;
    BaseFragment fg_homework, fg_ask, fg_notice, fg_score, fg_persioninfo,
            fg_dayoff, fg_questions,fg_dayoff_records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //设置信息显示
        TextView txt_name = navigationView.getHeaderView(0).findViewById(R.id.txt_name);
        txt_name.setText(StuData.loginer.getSname());

        TextView txt_pname = navigationView.getHeaderView(0).findViewById(R.id.txt_pname);
        txt_pname.setText("专业：" + DBTool.getInstance().getProfessionByPid(StuData.loginer.getPid()).getPname());

        TextView txt_gid = navigationView.getHeaderView(0).findViewById(R.id.txt_gid);
        txt_gid.setText("年级：" + StuData.loginer.getGid() + "");

        fgManager = getSupportFragmentManager();


        //初始显示个人信息
        replaceFragment(R.id.nav_stu_info);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {
            try {
                Intent intent = new Intent("login");
                startActivity(intent);
                finish();
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        replaceFragment(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void replaceFragment(int id) {
        Log.e("TAG", "开始替换fragment:");
        FragmentTransaction fgTrasaction = fgManager.beginTransaction();
        hideAllFg(fgTrasaction);
        if (id == R.id.nav_stu_homework) {
            Log.e("TAG", "替换fragment:homework");
            if (fg_homework == null) {
                fg_homework = new StuFgHomework();
                fgTrasaction.add(R.id.stu_container, fg_homework);
                Log.e("TAG", "创建添加fragment:homework");
            }
            fgTrasaction.show(fg_homework);
        } else if (id == R.id.nav_stu_ask) {
            if (fg_ask == null) {
                fg_ask = new StuFgAsk();
                fgTrasaction.add(R.id.stu_container, fg_ask);
            }
            fgTrasaction.show(fg_ask);
        } else if (id == R.id.nav_stu_score) {
            if (fg_score == null) {
                fg_score = new StuFgScore();
                fgTrasaction.add(R.id.stu_container, fg_score);
            }
            fgTrasaction.show(fg_score);
        } else if (id == R.id.nav_stu_notice) {
            if (fg_notice == null) {
                fg_notice = new StuFgNotice();
                fgTrasaction.add(R.id.stu_container, fg_notice);
            }
            fgTrasaction.show(fg_notice);
        } else if (id == R.id.nav_stu_dayoff) {
            if (fg_dayoff == null) {
                fg_dayoff = new StuFgDayoff();
                fgTrasaction.add(R.id.stu_container, fg_dayoff);
            }
            fgTrasaction.show(fg_dayoff);
        } else if (id == R.id.nav_stu_info) {
            if (fg_persioninfo == null) {
                fg_persioninfo = new StuFgPersionInfo();
                fgTrasaction.add(R.id.stu_container, fg_persioninfo);
            }
            fgTrasaction.show(fg_persioninfo);
        } else if (id == R.id.nav_stu_questions) {
            if (fg_questions == null) {
                fg_questions = new StuFgQuestions();
                fgTrasaction.add(R.id.stu_container, fg_questions);
            }
            fgTrasaction.show(fg_questions);
        }else if(id==R.id.nav_stu_dayoff_record){
            if(fg_dayoff_records==null){
                fg_dayoff_records=new StuFgDayoffRecord();
                fgTrasaction.add(R.id.stu_container,fg_dayoff_records);
            }
            fgTrasaction.show(fg_dayoff_records);
        }
        fgTrasaction.commit();
    }

    private void hideAllFg(FragmentTransaction fgTrasaction) {
        if (fg_notice != null) fgTrasaction.hide(fg_notice);
        if (fg_persioninfo != null) fgTrasaction.hide(fg_persioninfo);
        if (fg_dayoff != null) fgTrasaction.hide(fg_dayoff);
        if (fg_score != null) fgTrasaction.hide(fg_score);
        if (fg_ask != null) fgTrasaction.hide(fg_ask);
        if (fg_homework != null) fgTrasaction.hide(fg_homework);
        if (fg_questions != null) fgTrasaction.hide(fg_questions);
        if (fg_dayoff_records != null) fgTrasaction.hide(fg_dayoff_records);
    }
}
