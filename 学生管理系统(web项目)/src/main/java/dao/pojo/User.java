package dao.pojo;

/**
 * 用户类
 */
public class User {
    /**
     * 用户编号
     */
    private int id;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户密码
     */
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User() {
    }


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
