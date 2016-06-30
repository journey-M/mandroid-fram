package dev.guowj.androidfram.commonutil;

import android.os.Environment;

/**
 * Created by guowj on 2016/6/30.
 */
public class StorageUtil {

    /**
     * SD卡是否可用
     *
     * @return
     */
    public static boolean hasSdCard() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            // sdcard 不可用
            return false;
        }
        return true;
    }

}



