package dev.guowj.androidfram;

import android.app.Application;
import android.content.Context;

/**
 * Created by guowj on 2016/6/30.
 */
public class App extends Application {

    /**
     * 全局唯一的Context变量
     */
    public static App instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getInstance() {
        return instance;
    }
}
