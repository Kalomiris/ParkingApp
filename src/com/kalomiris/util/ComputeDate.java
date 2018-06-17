package com.kalomiris.util;

import java.util.Calendar;

public class ComputeDate {

    public static long Duration(long time){
        Calendar now = Calendar.getInstance();
        long duration = now.getTimeInMillis() - time;
        return duration;
    }

    public static boolean timeForShift(int time){
        Calendar now = Calendar.getInstance();

        //First shift
        if (now.after(time)){
            return true;

            //Second shift
        }else {
            return false;
        }
    }
}
