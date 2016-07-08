package dev.guowj.androidfram.cache;

/**
 * Created by guowj on
 * Todo 本地数据管理接口
 */
public interface IDataManager<T> {

    /**
     * 存入数据
     * @param key
     * @param t
     */
    void put(String key, T t);

    /**
     * 删除某一条数据
     * @param key
     */
    void delete(String key);

    /**
     * 更新数据
     * @param key
     * @param t
     */
    void update(String key, T t);

    /**
     * 查找数据
     * @param key
     * @return
     */
    T find(String key);

    /**
     * 查找数据
     * @param key
     * @return
     */
    T find(String key, Class<T> cls);

    /**
     * 清空数据
     */
    void clear();
}
