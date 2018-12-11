package com.tms.geq.chattingrobot.vo;

public class MsgItem {
    public static int ME = 0;
    public static int HE = 1;

   public String info;
   public int type;

    public MsgItem(String info, int type) {
        this.info = info;
        this.type = type;
    }
}
