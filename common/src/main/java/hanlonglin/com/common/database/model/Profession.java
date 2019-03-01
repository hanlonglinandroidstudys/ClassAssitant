package hanlonglin.com.common.database.model;

import org.litepal.crud.DataSupport;

public class Profession extends DataSupport {
    int pid;
    String pname;

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getPid() {
        return pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
}
