package com.tms.geq.chattingrobot.utils;

import android.util.Log;

public class LogUtils {

    private static String LOG="MYLOG";
    private static boolean  flag = true;

    public static void loge(String value){
        if (flag){
            Log.e(LOG, "loge: "+value );
        }
    }

    public static void logi(String value){
        if (flag){
            Log.i(LOG, "loge: "+value );
        }
    }


    public static void logd(String value){
        if (flag){
            Log.d(LOG, "loge: "+value );
        }
    }

    public static void logw(String value){
        if (flag){
            Log.w(LOG, "loge: "+value );
        }
    }






}
