package dev.guowj.androidfram.cache;

import com.snappydb.DB;
import com.snappydb.SnappyDB;
import com.snappydb.SnappydbException;

import java.util.Arrays;
import java.util.List;

import dev.guowj.androidfram.App;

/**
 * Created by guowj on 2016/7/12.
 */
public class ObjectDbManager<T> implements IDataManager<T> {

    private DB snappyDb = null;

    public ObjectDbManager() {
        try {
            snappyDb = new SnappyDB.Builder(App.getInstance()).directory(StorageUtil.getExternalFileDir().getAbsolutePath())
                    .name("msnappydb").build();
        } catch (SnappydbException e) {
            throw new IllegalStateException("" + e.toString());
        }
    }

    @Override
    public void put(String key, T t) {
        update(key, t);
    }

    @Override
    public void delete(String key) {
        try {
            if (snappyDb.exists(key)) {
                snappyDb.del(key);
            }
            snappyDb.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
            throw new IllegalStateException("" + e.toString());
        }
    }

    @Override
    public void update(String key, T t) {
        try {
            if (snappyDb.exists(key)) {
                snappyDb.del(key);
            }
            snappyDb.put(key, t);
            snappyDb.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
            throw new IllegalStateException("" + e.toString());
        }
    }

    @Override
    @Deprecated
    public T find(String key) {
        throw new IllegalArgumentException("请使用其他方法");
    }

    public T findObject(String key, Class<T> cls) {
        T t = null;
        try {
            if (snappyDb.exists(key)) {
                t = snappyDb.getObject(key, cls);
                snappyDb.close();
            }
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return t;
    }


    public List<T> findObjectArray(String key, Class<T> cls) {
        T[] array = null;
        try {
            if (snappyDb.exists(key)) {
                array = snappyDb.getObjectArray(key, cls);
            }
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return Arrays.asList(array);
    }


    @Override
    public void clear() {
        try {
            if (snappyDb != null) {
                snappyDb.close();
            }
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }
}
