package dev.guowj.androidfram;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePalApplication;

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
        initOthers();
    }

    public static Context getInstance() {
        return instance;
    }

    private void initOthers() {
        LitePalApplication.initialize(this);
    }

}
