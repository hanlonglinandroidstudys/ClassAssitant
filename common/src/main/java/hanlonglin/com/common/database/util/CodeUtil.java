package hanlonglin.com.common.database.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CodeUtil {
    private CodeUtil(){}

    private static CodeUtil codeUtil=new CodeUtil();

    public static CodeUtil getInstance(){
        return codeUtil;
    }

    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY/MM/dd hh:mm:ss");

    public String formatDate(Date date){
       return simpleDateFormat.format(date);
    }
}
