package dao.pojo;

/**
 * 学生类
 */
public class Student {
    /**
     * 编号
     */
    private int id;
    /**
     * 学生姓名
     */
    private String name;
    /**
     * 学生年龄
     */
    private int age;
    /**
     * 学生性别
     */
    private String sex;
    /**
     * 家庭住址
     */
    private String address;

    public Student(int id, String name, int age, String sex, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.address = address;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
