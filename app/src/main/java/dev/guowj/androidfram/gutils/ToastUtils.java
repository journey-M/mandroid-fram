/* 
 * @(#)ToastUtils.java    Created on 2011-5-31
 * Copyright (c) 2011 ZDSoft Networks, Inc. All rights reserved.
 * $Id: ToastUtils.java 31799 2012-10-25 04:59:34Z xuan $
 */
package dev.guowj.androidfram.gutils;

import android.os.Handler;
import android.widget.Toast;

import dev.guowj.androidfram.App;

/**
 * 吐司信息工具类
 *
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2013-3-25 下午7:40:05 $
 */
public class ToastUtils {
    /**
     * 显示吐司信息（较长时间）
     *
     * @param text
     */
    public static void displayTextLong(String text) {
        Toast.makeText(App.getInstance(), text, Toast.LENGTH_LONG).show();
    }

    /**
     * 显示吐司信息（较短时间）
     *
     * @param text
     */
    public static void displayTextShort(String text) {
        Toast.makeText(App.getInstance(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示吐司信息交给handler处理（较长时间）
     *
     * @param text
     * @param handler
     */
    public static void displayTextLong2Handler(final String text, Handler handler) {

        handler.post(new Runnable() {
            @Override
            public void run() {
                ToastUtils.displayTextLong(text);
            }
        });
    }

    /**
     * 显示吐司信息交给handler处理（较短时间）
     *
     * @param text
     * @param handler
     */
    public static void displayTextShort2Handler(final String text, Handler handler) {

        handler.post(new Runnable() {

            @Override
            public void run() {
                ToastUtils.displayTextShort(text);
            }
        });
    }
}
