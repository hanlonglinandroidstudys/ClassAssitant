package hanlonglin.com.common.database.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PerferenceUtil {

    private static String IS_FIRST="isfirst";

    public static boolean checkIsFirst(Context context){
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean isFirst = defaultSharedPreferences.getBoolean(IS_FIRST, true);
        if(isFirst){
            SharedPreferences.Editor editor=defaultSharedPreferences.edit();
            editor.putBoolean(IS_FIRST,false);
            editor.commit();
            return true;
        }else{
            return false;
        }
    }
}
