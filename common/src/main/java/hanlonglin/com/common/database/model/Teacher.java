package hanlonglin.com.common.database.model;

import org.litepal.crud.DataSupport;

public class Teacher extends DataSupport {
    int tid;
    String tpasswd;
    String tname;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTpasswd() {
        return tpasswd;
    }

    public void setTpasswd(String tpasswd) {
        this.tpasswd = tpasswd;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String toString(){
        return getTid()+"|"+tname;
    }
}
