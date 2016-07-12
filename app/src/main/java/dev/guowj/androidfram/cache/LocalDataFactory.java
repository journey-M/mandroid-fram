package dev.guowj.androidfram.cache;

/**
 * Created by guowj on 2016/3/1.
 * Todo 本地数据管理工厂
 */
public class LocalDataFactory {

    public enum LocalDataType {
        CACHE,
        SP
    }

    public static <T> IDataManager<T> getManager(LocalDataType type) throws IllegalArgumentException {
        IDataManager<T> dataManager = null;
        switch (type) {
            case CACHE:
                dataManager = new ObjectFileManager<>();
                break;
            case SP:
                dataManager = new SharePreferManager<>();
                break;
        }
        return dataManager;
    }
}
