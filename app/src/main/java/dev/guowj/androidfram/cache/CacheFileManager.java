package dev.guowj.androidfram.cache;

import android.text.TextUtils;

import java.io.File;

/**
 * Created by guowj on 2016/3/1.
 * Todo 缓存文件管理器，文件目录在/sdcard/Android/data/包名/cache/edooon/目录下
 */
public class CacheFileManager<T> implements IDataManager<T> {

    private File rootDir;

    public CacheFileManager() {
        rootDir = new File(DataUtils.getCachDirPath());
    }

    /**
     * @param key key是文件名
     * @param t   需要保存的对象
     */
    @Override
    public void put(String key, T t) {
        if (TextUtils.isEmpty(key)) return;
        DataUtils.ObjectOutputStream(t, rootDir.getAbsolutePath() + File.separator + key);
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
        return (T) DataUtils.ObjectInputStream(rootDir.getAbsolutePath() + File.separator + key);
    }

    @Override
    public T find(String key, Class<T> cls) {
        return null;
    }

    @Override
    public void clear() {
        DataUtils.deleteFile(rootDir);
    }
}
