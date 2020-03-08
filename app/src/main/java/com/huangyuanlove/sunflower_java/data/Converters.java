package com.huangyuanlove.sunflower_java.data;

import java.util.Calendar;

public class Converters {

    public long calendarToDatestamp(Calendar calendar){
        return calendar.getTimeInMillis();
    }

    public Calendar datestampToCalendar(long value){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(value);
        return calendar;
    }

}
