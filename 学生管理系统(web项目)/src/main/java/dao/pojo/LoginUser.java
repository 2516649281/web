package dao.pojo;

/**
 * 用户监视类
 */
public class LoginUser {
    /**
     * 编号
     */
    private int id;
    /**
     * 用户名
     */
    private String UserName;
    /**
     * 登录时间
     */
    private String LoginTime;
    /**
     * 结束时间
     */
    private String EndTime;

    public LoginUser(String userName, String loginTime, String endTime) {
        UserName = userName;
        LoginTime = loginTime;
        EndTime = endTime;
    }

    public LoginUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getLoginTime() {
        return LoginTime;
    }

    public void setLoginTime(String loginTime) {
        LoginTime = loginTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }
}
