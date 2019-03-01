package hanlonglin.com.common.database.util;

import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;

import hanlonglin.com.common.database.model.Homework_do;
import hanlonglin.com.common.database.model.Homework_publish;
import hanlonglin.com.common.database.model.Notice;
import hanlonglin.com.common.database.model.Profession;
import hanlonglin.com.common.database.model.Question;
import hanlonglin.com.common.database.model.Student;
import hanlonglin.com.common.database.model.Teacher;
import hanlonglin.com.common.database.model.Teacher_Gp;

public class DBTool {

    private DBTool() {
    }

    private static DBTool dbTool = new DBTool();

    public static DBTool getInstance() {
        return dbTool;
    }

    /**
     * 模拟创建数据
     */
    public void mockData() {

        mockStudent();

        mockTeacher();

        mockProfession();

        mockTeacherGp();

        mockHomeworkPublish();

        mockNotice();

        mockHomeworkDo();
    }


    private void mockStudent() {
        //Student
        //专业1插入10条数据
        for (int i = 0; i < 10; i++) {
            Student stu = new Student();
            stu.setPid(0);
            stu.setGid(1);
            stu.setSid(i);
            stu.setSname("mayun" + i);
            stu.setSpasswd("123456");
            stu.save();
        }
        //专业2插入10条数据
        for (int i = 10; i < 20; i++) {
            Student stu = new Student();
            stu.setPid(1);
            stu.setGid(1);
            stu.setSid(i);
            stu.setSname("mahuateng" + i);
            stu.setSpasswd("123456");
            stu.save();
        }
    }

    private void mockTeacher() {

        Teacher tea = new Teacher();
        tea.setTname("lance");
        tea.setTpasswd("123456");
        tea.setTid(0);
        tea.save();

        Teacher tea2 = new Teacher();
        tea2.setTname("alven");
        tea2.setTpasswd("123456");
        tea2.setTid(1);
        tea2.save();

        Teacher tea3 = new Teacher();
        tea3.setTname("zero");
        tea3.setTpasswd("123456");
        tea3.setTid(2);
        tea3.save();
    }

    private void mockProfession() {
        Profession pro = new Profession();
        pro.setPname("英语");
        pro.setPid(0);
        pro.save();

        Profession pro2 = new Profession();
        pro2.setPname("数学");
        pro2.setPid(1);
        pro2.save();

        Profession pro3 = new Profession();
        pro3.setPname("计算机");
        pro3.setPid(2);
        pro3.save();
    }

    private void mockTeacherGp() {
        Teacher_Gp teacher_gp = new Teacher_Gp();
        teacher_gp.setGid(1);
        teacher_gp.setPid(0);
        teacher_gp.setTid(0);
        teacher_gp.save();

        Teacher_Gp teacher_gp2 = new Teacher_Gp();
        teacher_gp2.setGid(1);
        teacher_gp2.setPid(1);
        teacher_gp2.setTid(1);
        teacher_gp2.save();

        Teacher_Gp teacher_gp3 = new Teacher_Gp();
        teacher_gp3.setGid(1);
        teacher_gp3.setPid(2);
        teacher_gp3.setTid(2);
        teacher_gp3.save();
    }

    private void mockHomeworkPublish(){
        Homework_publish homework_publish=new Homework_publish();
        homework_publish.setHid(0);
        homework_publish.setTid(0);
        homework_publish.setPid(0);
        homework_publish.setGid(1);
        homework_publish.setQuestion("在英语中apple如何发音");
        homework_publish.setDate(new Date());
        homework_publish.save();

        Homework_publish homework_publish2=new Homework_publish();
        homework_publish2.setHid(1);
        homework_publish2.setTid(0);
        homework_publish2.setPid(0);
        homework_publish2.setGid(1);
        homework_publish2.setQuestion("用英语翻译下面一句话：大江东去浪淘尽千古风流人物");
        homework_publish2.setDate(new Date());
        homework_publish2.save();

        Homework_publish homework_publish3=new Homework_publish();
        homework_publish3.setHid(2);
        homework_publish3.setTid(1);
        homework_publish3.setPid(1);
        homework_publish3.setGid(1);
        homework_publish3.setQuestion("1+1等于多少？");
        homework_publish3.setDate(new Date());
        homework_publish3.save();

        Homework_publish homework_publish4=new Homework_publish();
        homework_publish4.setHid(3);
        homework_publish4.setTid(2);
        homework_publish4.setPid(2);
        homework_publish4.setGid(1);
        homework_publish4.setQuestion("java的数据类型有几种，各占几个字节？");
        homework_publish4.setDate(new Date());
        homework_publish4.save();

    }

    private void mockHomeworkDo(){
        Homework_do homework_do=new Homework_do();
        homework_do.setGid(1);
        homework_do.setHid(0);
        homework_do.setSid(5);
        homework_do.setPid(0);
        homework_do.setState(0);
        homework_do.setAnswer("我不会");
        homework_do.setDate(new Date());
        homework_do.save();

        Homework_do homework_do2=new Homework_do();
        homework_do2.setGid(1);
        homework_do2.setHid(1);
        homework_do2.setSid(5);
        homework_do2.setPid(0);
        homework_do2.setState(0);
        homework_do2.setAnswer("qiankufengliurenwu");
        homework_do2.setDate(new Date());
        homework_do2.save();

        Homework_do homework_do3=new Homework_do();
        homework_do3.setGid(1);
        homework_do3.setHid(3);
        homework_do3.setSid(5);
        homework_do3.setPid(2);
        homework_do3.setState(0);
        homework_do3.setAnswer("int(4) char(4) long(8) float(4) double(8)");
        homework_do3.setDate(new Date());
        homework_do3.save();

        Homework_do homework_do4=new Homework_do();
        homework_do4.setGid(1);
        homework_do4.setHid(2);
        homework_do4.setSid(5);
        homework_do4.setPid(1);
        homework_do4.setState(0);
        homework_do4.setAnswer("dengyu 2");
        homework_do4.setDate(new Date());
        homework_do4.save();

    }

    private void mockNotice(){
        Notice notice=new Notice();
        notice.setNid(0);
        notice.setTid(0);
        notice.setContent("今天上课时间为下午10点");
        notice.setDate(new Date());
        notice.save();

        Notice notice2=new Notice();
        notice2.setNid(1);
        notice2.setTid(1);
        notice2.setContent("明天下午放假，请注意安全");
        notice2.setDate(new Date());
        notice2.save();

        Notice notice3=new Notice();
        notice3.setNid(2);
        notice3.setTid(2);
        notice3.setContent("考试作弊者直接开除!");
        notice3.setDate(new Date());
        notice3.save();

    }



    //search
    public Student getStuBySid(int sid){
        List<Student> students = DataSupport.where("sid=?", sid + "").find(Student.class);
        if(students.size()>0)
            return students.get(0);
        else
            return new Student();
    }

    public Teacher getTeaByTid(int tid){
        List<Teacher> teachers = DataSupport.where("tid=?", tid + "").find(Teacher.class);
        if(teachers.size()>0)
            return teachers.get(0);
        else
            return new Teacher();
    }
    public Profession getProfessionByPid(int pid){
        List<Profession> professions = DataSupport.where("pid=?", pid + "").find(Profession.class);
        if(professions.size()>0)
            return professions.get(0);
        else
            return new Profession();
    }

    public Homework_publish getHomeworkPublishByHid(long hid){
        List<Homework_publish> homework_publishes = DataSupport.where("hid=?", hid + "").find(Homework_publish.class);
        if(homework_publishes.size()>0)
            return homework_publishes.get(0);
        else
            return new Homework_publish();
    }

    public Homework_do getHomeworkdoByHidAndSid(long hid,int sid){
        List<Homework_do> Homework_dos = DataSupport.where("hid=? and sid=?", hid + "",sid+"").find(Homework_do.class);
        if(Homework_dos.size()>0)
            return Homework_dos.get(0);
        else
            return new Homework_do();
    }

    public Question getQuestionByQid(long qid){
        List<Question> questions = DataSupport.where("qid=?", qid+ "").find(Question.class);
        if(questions.size()>0)
            return questions.get(0);
        else
            return new Question();
    }

}
