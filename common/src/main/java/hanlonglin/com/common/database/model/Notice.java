package hanlonglin.com.common.database.model;

import org.litepal.crud.DataSupport;

import java.util.Date;

public class Notice extends DataSupport {
    long nid;
    int tid;
    String content;
    Date date;

    public void setNid(long nid) {
        this.nid = nid;
    }

    public long getNid() {
        return nid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
