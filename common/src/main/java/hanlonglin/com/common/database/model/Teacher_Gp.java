package hanlonglin.com.common.database.model;

import org.litepal.crud.DataSupport;

public class Teacher_Gp extends DataSupport {
    int tid;
    int gid;
    int pid;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
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
