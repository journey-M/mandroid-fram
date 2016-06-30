/* 
 * @(#)DialUtils.java    Created on 2013-2-6
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package dev.guowj.androidfram.commonutil;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * 拨打电话工具类
 *
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2013-2-6 下午3:00:48 $
 */
public abstract class DialAndSmsUtils {

    /**
     * 根据手机好拨打电话
     *
     * @param context
     * @param phone
     */
    public static void callByPhone(Context context, String phone) {
        if (Validators.isEmpty(phone)) {
            return;
        }

        Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + phone));
        context.startActivity(intent);
    }


    /**
     * 根据手机号发送短信
     *
     * @param context
     * @param phone
     */
    public static void sendSmsByPhone(Context context, String phone) {
        if (Validators.isEmpty(phone)) {
            return;
        }
        Uri uri = Uri.parse("smsto:" + phone);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        // intent.putExtra("sms_body", "");
        context.startActivity(intent);
    }

    /**
     * 根据内容调用手机通讯录
     *
     * @param context
     * @param content
     */
    public static void sendSmsByContent(Context context, String content) {
        Uri uri = Uri.parse("smsto:");
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", content);
        context.startActivity(intent);
    }


}
