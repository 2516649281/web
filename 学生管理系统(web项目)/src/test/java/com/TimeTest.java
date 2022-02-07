package com;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间测试类
 */
public class TimeTest {
    public String LoginTime;

    public String EndTime;

    @Test
    public void Test() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        LoginTime = df.format(date);
        System.out.println(LoginTime);
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.format(date);
        Calendar c = new GregorianCalendar();
        c.add(Calendar.SECOND, +(60 * 30));
        EndTime = df.format(c.getTime());
        System.out.println(EndTime);
    }
}
