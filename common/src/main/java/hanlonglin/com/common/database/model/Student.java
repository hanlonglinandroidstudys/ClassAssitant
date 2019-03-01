package hanlonglin.com.common.database.model;

import org.litepal.crud.DataSupport;

public class Student extends DataSupport {
    int sid;
    String spasswd;
    String sname;
    int gid;
    int pid;
    int score;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getSpasswd() {
        return spasswd;
    }

    public void setSpasswd(String spasswd) {
        this.spasswd = spasswd;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
