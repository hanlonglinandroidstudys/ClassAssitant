package hanlonglin.com.teacher_model;

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

import com.alibaba.android.arouter.facade.annotation.Route;

import hanlonglin.com.common.AppData.TeaData;
import hanlonglin.com.common.database.util.ARouterMap;
import hanlonglin.com.teacher_model.fragments.BaseFragment;
import hanlonglin.com.teacher_model.fragments.TeaFgCallStudents;
import hanlonglin.com.teacher_model.fragments.TeaFgDayoffs;
import hanlonglin.com.teacher_model.fragments.TeaFgModifyHomework;
import hanlonglin.com.teacher_model.fragments.TeaFgPersionInfo;
import hanlonglin.com.teacher_model.fragments.TeaFgPublishHomework;
import hanlonglin.com.teacher_model.fragments.TeaFgPublishNotice;
import hanlonglin.com.teacher_model.fragments.TeaFgQuestions;
import hanlonglin.com.teacher_model.fragments.TeaFgScore;

@Route(path = ARouterMap.AC_TEACHER_MAIN)
public class TeacherMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fgManager = null;
    BaseFragment fg_callStu, fg_modify_homework, fg_persioninfo, fg_publish_homework,
            fg_publish_notice, fg_score, fg_questions, fg_dayoffs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tea_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView txt_tname = navigationView.getHeaderView(0).findViewById(R.id.txt_tname);
        txt_tname.setText(TeaData.loginer.getTname());

        TextView txt_tid = navigationView.getHeaderView(0).findViewById(R.id.txt_tid);
        txt_tid.setText("教师id:" + TeaData.loginer.getTid() + "");

        fgManager = getSupportFragmentManager();

        //初始显示个人信息
        replaceFragment(R.id.nav_tea_info);
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
            Intent intent = new Intent("login");
            startActivity(intent);
            finish();
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
        if (id == R.id.nav_tea_call) {
            Log.e("TAG", "替换fragment:homework");
            if (fg_callStu == null) {
                fg_callStu = new TeaFgCallStudents();
                fgTrasaction.add(R.id.tea_container, fg_callStu);
                Log.e("TAG", "创建添加fragment:homework");
            }
            fgTrasaction.show(fg_callStu);
        } else if (id == R.id.nav_tea_info) {
            if (fg_persioninfo == null) {
                fg_persioninfo = new TeaFgPersionInfo();
                fgTrasaction.add(R.id.tea_container, fg_persioninfo);
            }
            fgTrasaction.show(fg_persioninfo);
        } else if (id == R.id.nav_tea_modify_homework) {
            if (fg_modify_homework == null) {
                fg_modify_homework = new TeaFgModifyHomework();
                fgTrasaction.add(R.id.tea_container, fg_modify_homework);
            }
            fgTrasaction.show(fg_modify_homework);
        } else if (id == R.id.nav_tea_publish_homework) {
            if (fg_publish_homework == null) {
                fg_publish_homework = new TeaFgPublishHomework();
                fgTrasaction.add(R.id.tea_container, fg_publish_homework);
            }
            fgTrasaction.show(fg_publish_homework);
        } else if (id == R.id.nav_tea_publish_notice) {
            if (fg_publish_notice == null) {
                fg_publish_notice = new TeaFgPublishNotice();
                fgTrasaction.add(R.id.tea_container, fg_publish_notice);
            }
            fgTrasaction.show(fg_publish_notice);
        } else if (id == R.id.nav_tea_score) {
            if (fg_score == null) {
                fg_score = new TeaFgScore();
                fgTrasaction.add(R.id.tea_container, fg_score);
            }
            fgTrasaction.show(fg_score);
        } else if (id == R.id.nav_tea_questions) {
            if (fg_questions == null) {
                fg_questions = new TeaFgQuestions();
                fgTrasaction.add(R.id.tea_container, fg_questions);
            }
            fgTrasaction.show(fg_questions);
        } else if (id == R.id.nav_tea_dayoff_requre) {
            if (fg_dayoffs == null) {
                fg_dayoffs = new TeaFgDayoffs();
                fgTrasaction.add(R.id.tea_container, fg_dayoffs);
            }
            fgTrasaction.show(fg_dayoffs);
        }
        fgTrasaction.commit();
    }

    private void hideAllFg(FragmentTransaction fgTrasaction) {
        if (fg_score != null) fgTrasaction.hide(fg_score);
        if (fg_persioninfo != null) fgTrasaction.hide(fg_persioninfo);
        if (fg_publish_notice != null) fgTrasaction.hide(fg_publish_notice);
        if (fg_callStu != null) fgTrasaction.hide(fg_callStu);
        if (fg_publish_homework != null) fgTrasaction.hide(fg_publish_homework);
        if (fg_modify_homework != null) fgTrasaction.hide(fg_modify_homework);
        if (fg_questions != null) fgTrasaction.hide(fg_questions);
        if (fg_dayoffs != null) fgTrasaction.hide(fg_dayoffs);
    }
}
