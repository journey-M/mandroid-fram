package dev.guowj.androidfram.gutils;

import android.util.Log;

import dev.guowj.androidfram.BuildConfig;

/**
 * Created by guowj on 2016/7/8.
 */
public class LogsUtil {

    public static boolean isShow = BuildConfig.DEBUG;

    private static String getTag() {
        StackTraceElement[] trace = new Throwable().fillInStackTrace()
                .getStackTrace();
        String callingClass = "";
        for (int i = 2; i < trace.length; i++) {
            Class clazz = trace[i].getClass();
            if (!clazz.equals(LogsUtil.class)) {
                callingClass = trace[i].getClassName();
                callingClass = callingClass.substring(callingClass
                        .lastIndexOf('.') + 1);
                break;
            }
        }
        return callingClass;
    }

    public static void v(String mess) {
        if (isShow) {
            Log.v(getTag(), mess);
        }
    }

    public static void d(String mess) {
        if (isShow) {
            Log.d(getTag(), mess);
        }
    }

    public static void i(String mess) {
        if (isShow) {
            Log.i(getTag(), mess);
        }
    }

    public static void w(String mess) {
        if (isShow) {
            Log.w(getTag(), mess);
        }
    }

    public static void e(String mess) {
        if (isShow) {
            Log.e(getTag(), mess);
        }
    }


}
