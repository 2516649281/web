package Listener;

import dao.pojo.LoginUser;
import dao.pojo.UserMon;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 监视用户登录与下线
 */
public class LoginListener implements HttpSessionListener {

    /**
     * 用户名
     */
    public static String username;
    /**
     * 用户登录时间
     */
    public static String LoginTime;

    /**
     * 退出时间
     */
    public static String EndTime;

    /**
     * 日期类
     */
    public static Date date;

    /**
     * 设置时间格式
     */
    public static SimpleDateFormat df;

    /**
     * 用户类
     */
    public static LoginUser user;

    /**
     * 第一个session创建时触发
     *
     * @param httpSessionEvent
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = new Date();
        LoginTime = df.format(date);
    }

    /**
     * session销毁时执行
     *
     * @param httpSessionEvent
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        //获取退出时的信息
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = new Date();
        //如果用户点击退出，则直接改变结束时间
        EndTime = df.format(date);
        try {
            UserMon.AddLogin(username, LoginTime, EndTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
