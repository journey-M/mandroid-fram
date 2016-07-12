package dev.guowj.androidfram.cache;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

import dev.guowj.androidfram.App;

/**
 * Created by guowj on 2016/6/30.
 */
public class StorageUtil {


    /**
     * ************* 系统内置的路径，不会随着Sd卡的移除丢失数据***************************
     * path =  /data/data/<application package>/
     */

    /**
     * 获取内部存储缓存的路径，适合存放临时的数据
     *
     * @return /data/data/<application package>/cache
     */
    public static File getInternalCacheDir() {
        return App.getInstance().getCacheDir();
    }

    /**
     * 获取内部存储缓存的路径，适合存放保存时间长较隐秘的的数据
     *
     * @return /data/data/<application package>/files
     */
    public static File getInternalFileDir() {
        return App.getInstance().getFilesDir();
    }


    /**
     * *************  外部的SDCard存储方法, 部分手机会随着手机没有挂载Sdcard的时候文件丢失 **************************
     * 存储的数据会随着程序的卸载而删除
     * /storage/emulated/0/Android/data/<application package>/cache (小米)
     * <p/>
     */

    public static File getExternalCacheDir() {
        return App.getInstance().getExternalCacheDir();
    }

    public static File getExternalFileDir() {
        return App.getInstance().getExternalFilesDir(null);
    }


    /**
     * *************  自己定义SDCard存储方法, 部分手机会随着手机没有挂载Sdcard的时候文件丢失 **************************
     * <p/>
     * 数据不会随着程序的卸载而丢失
     */


    public static final String ROOT_DIR_NAME = "ShiZhuang";
    public static final String DIR_PICTURE = "mpicture";
    public static final String DIR_CACHE_FILE = "filesCache";

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

    /**
     * 获取磁盘文件夹
     *
     * @return
     */
    public static File getSDCardDiskDir() {
        String sdcard = getSDPath();
        if (!TextUtils.isEmpty(sdcard)) {
            String path = sdcard + File.separator + ROOT_DIR_NAME;
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            return file;
        }
        return null;
    }

    public static File getSDCardFileDir() {
        String diskPath = getSDCardDiskDir().getAbsolutePath();
        if (!TextUtils.isEmpty(diskPath)) {
            String path = diskPath + File.separator + DIR_CACHE_FILE;
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            return file;
        }
        return null;
    }

    /**
     * 获取磁盘图片路径
     *
     * @return
     */
    public static File getSDCardPicDir() {
        String path = getSDCardDiskDir().getAbsolutePath() + File.separator + DIR_PICTURE;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }


}



