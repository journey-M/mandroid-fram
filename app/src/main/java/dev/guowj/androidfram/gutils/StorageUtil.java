package dev.guowj.androidfram.gutils;

import android.os.Environment;

/**
 * Created by guowj on 2016/6/30.
 */
public class StorageUtil {

    /**
     * 判断手机是否有SD卡。
     *
     * @return 有SD卡返回true，没有返回false。
     */
    public static boolean hasSDCard() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 返回sd卡目录
     *
     * @return
     */
    public static String getSDPath() {
        if (StorageUtil.hasSDCard()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            return null;
        }
    }


}



