package dev.guowj.androidfram.cache;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import dev.guowj.androidfram.App;
import dev.guowj.androidfram.BuildConfig;
import dev.guowj.androidfram.gutils.StorageUtil;

/**
 * Created by guowj on 2016/3/3.
 * Todo 文件工具
 */
public class DataUtils {

    public static final String ROOT_DIR_NAME = "" + BuildConfig.APPLICATION_ID;

    public static final String CACHE_DIR_NAME = "mcache";



    /**
     * 获取私有的缓存文件夹路径
     *
     * @return
     */
    public static String getCachDirPath() {
        String path = App.getInstance().getExternalCacheDir().getAbsolutePath() + File.separator + CACHE_DIR_NAME;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    /**
     * 获取私有的磁盘文件路径
     *
     * @return
     */
    public static File getFileDirPath() {
        String path = App.getInstance().getExternalFilesDir(null).getAbsolutePath() + File.separator + ROOT_DIR_NAME;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }


    /**
     * 获取磁盘文件夹
     *
     * @return
     */
    public static File getDiskDir() {
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

    /**
     * 获取磁盘图片路径
     *
     * @return
     */
    public static File getDiskPicDir() {
        String path = getDiskDir().getAbsolutePath() + File.separator + DIR_PICTURE;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * 将SD卡文件删除
     *
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            }
            // 如果它是一个目录
            else if (file.isDirectory()) {
                // 声明目录下所有的文件 files[];
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
                }
            }
            file.delete();
        }
    }

    public static void saveBitmap(Bitmap bmp, String path) {
        try {
            FileOutputStream fout = new FileOutputStream(path);
            BufferedOutputStream bos = new BufferedOutputStream(fout);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
            bmp.recycle();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 序列化对象
    public static void ObjectOutputStream(Object object, String savePath) {
        java.io.ObjectOutputStream out = null;
        try {
            File file = new File(savePath);
            File parentFIle = file.getParentFile();
            if (!parentFIle.exists()) {
                parentFIle.mkdirs();
            }
            if (!file.isFile()) {
                file.createNewFile();
            }
            out = new java.io.ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(object);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                    out = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 反列化对象
    public static Object ObjectInputStream(String savePath) {
        Object object = null;
        java.io.ObjectInputStream in = null;
        try {
            in = new java.io.ObjectInputStream(new FileInputStream(savePath));
            object = in.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return object;
    }


}
