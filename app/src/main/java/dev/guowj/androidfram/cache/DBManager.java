package dev.guowj.androidfram.cache;//package com.jufenqi.jianli.cache;
//
//import com.edooon.IApplication;
//import com.edooon.utils.Constant;
//import com.jufenqi.jianli.business.App;
//import com.snappydb.DB;
//import com.snappydb.DBFactory;
//import com.snappydb.SnappydbException;
//
///**
// * Created by guowj on 2016/3/1.
// * Todo 数据库管理器，使用的是NoSQL
// */
//public class DBManager<T> implements IDataManager<T> {
//
//    private DB snappyDb;
//
//    public DBManager() {
//        try {
//            this.snappyDb = DBFactory.open(App.getInstance().getApplicationContext(), Constant.DB_NAME);
//        } catch (SnappydbException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void put(String key, T t) {
//        if (snappyDb != null) {
//            try {
//                if (snappyDb.exists(key)) {
//                    update(key, t);
//                } else {
//                    snappyDb.put(key, t);
//                }
//                snappyDb.close();
//            } catch (SnappydbException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
//
//    @Override
//    public void delete(String key) {
//        if (snappyDb != null) {
//            try {
//                snappyDb.del(key);
//                snappyDb.close();
//            } catch (SnappydbException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public void update(String key, T t) {
//        if (snappyDb != null) {
//            try {
//                snappyDb.del(key);
//                snappyDb.put(key, t);
//                snappyDb.close();
//            } catch (SnappydbException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public T find(String key) {
//        throw new IllegalArgumentException("please use find(String,Class)");
//    }
//
//    /**
//     * 数据库查找比较特殊
//     *
//     * @param key
//     * @param cls
//     * @return
//     */
//
//    public T find(String key,Class<T> cls) {
//        try {
//            if (snappyDb != null && snappyDb.exists(key)) {
//                T o = snappyDb.getObject(key, cls);
//                snappyDb.close();
//                return o;
//            }
//        } catch (SnappydbException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public void clear() {
//        if (snappyDb != null) {
//            try {
//                snappyDb.destroy();
//                snappyDb.close();
//            } catch (SnappydbException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
