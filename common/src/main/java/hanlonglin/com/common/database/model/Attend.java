package hanlonglin.com.common.database.model;

import org.litepal.crud.DataSupport;

import java.util.Date;

public class Attend extends DataSupport {
    int sid;
    Date date;
    int attend = 0;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAttend() {
        return attend;
    }

    public void setAttend(int attend) {
        this.attend = attend;
    }
}

