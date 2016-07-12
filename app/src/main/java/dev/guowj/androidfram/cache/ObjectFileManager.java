package dev.guowj.androidfram.cache;

import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by guowj on 2016/3/1.
 * Todo 缓存文件管理器，文件目录在/sdcard/Android/data/包名/cache/目录下
 */
public class ObjectFileManager<T> implements IDataManager<T> {

    public static final int CACHE_INTERNAL = 0x11;
    public static final int CACHE_EXTERNAL = 0x12;
    public static final int CACHE_EXTERNAL_LONG = 0x13;

    private File rootDir;
    private int saveType = CACHE_EXTERNAL;

    public ObjectFileManager() {
        setSaveType();
    }

    public void setSaveType() {
        switch (saveType) {
            case CACHE_INTERNAL:
                rootDir = StorageUtil.getInternalFileDir();
                break;
            case CACHE_EXTERNAL:
                rootDir = StorageUtil.getExternalFileDir();
                break;
            case CACHE_EXTERNAL_LONG:
                rootDir = StorageUtil.getSDCardFileDir();
                break;
        }
    }


    /**
     * @param key key是文件名
     * @param t   需要保存的对象
     */
    @Override
    public void put(String key, T t) {
        if (TextUtils.isEmpty(key)) return;
        ObjectOutputStream(t, rootDir.getAbsolutePath() + File.separator + key);
    }

    @Override
    public void delete(String key) {
        if (TextUtils.isEmpty(key)) return;
        File file = new File(rootDir.getAbsolutePath() + File.separator + key);
        if (!file.exists()) return;
        file.delete();
    }

    @Override
    public void update(String key, T t) {
        delete(key);
        put(key, t);
    }

    @Override
    public T find(String key) {
        return (T) ObjectInputStream(rootDir.getAbsolutePath() + File.separator + key);
    }

    @Override
    public void clear() {
        deleteFile(rootDir);
    }


    /**
     * 序列化对象
     */
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

    /**
     * 反列化对象
     */
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


}
