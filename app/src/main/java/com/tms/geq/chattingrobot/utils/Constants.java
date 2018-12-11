package com.tms.geq.chattingrobot.utils;

import android.content.Context;

public class Constants {
    private static String ipAddress="";

   // public static final String HTTP = "http://"+ipAddress+"/transportservice/type/jason/action/";
    public static final String HTTPGETBUSSTATIONINFO = "GetBusStationInfo.do";
    public static final String HTTPGETALLSENSE = "GetAllSense.do";//传感器
    public static final String HTTPGETLIGHTSENSEVALVE = "GetLightSenseValve.do";
    public static final String HTTPGETCARSPEED = "GetCarSpeed.do";
    public static final String HTTPSETCARMOVE = "SetCarMove.do";
    public static final String HTTPGETCARACCOUNTBALANCE = "GetCarAccountBalance.do";
    public static final String HTTPSETCARACCOUNTRECHARGE = "SetCarAccountRecharge.do";
    public static final String HTTPGETTRAFFICLIGHTCONFIGACTION = "GetTrafficLightConfigAction.do";
    public static final String HTTPGETROADSTATUS = "GetRoadStatus.do";
    public static final String HTTPSETPARKRATE = "SetParkRate.do";
    public static final String HTTPGETPARKRATE = "GetParkRate.do";
    public static final String HTTPGETPARKFREE = "GetParkFree.do";

    //ip
    public static final String IP = "ip";

    public static String  getIP(Context context){
        ipAddress =SharedPreferencesUtil.getString(context,Constants.IP,"");
        return "http://"+ipAddress+":8080/transportservice/type/jason/action/";
    }
}
