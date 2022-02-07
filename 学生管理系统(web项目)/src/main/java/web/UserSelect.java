package web;

import dao.pojo.UserDemo;


/**
 * 验证用户注册
 */
public class UserSelect {
    public static String state;

    public static boolean Select(String user, String password) {
        boolean a = false;
        try {
            if (UserDemo.SelectUser(user, password) != null) {
                state = "该用户名已占用!";
            } else {
                try {
                    UserDemo.AddUser(user, password);
                    state = "注册成功，请登录！";
                    a = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }
}
