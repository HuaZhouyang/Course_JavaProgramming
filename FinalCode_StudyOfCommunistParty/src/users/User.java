package users;

import java.util.Date;

public class User {
    private long id;
    private String name;
    private String username;
    private String password;
    private String gender;

    /**
     * 保护权限的构造方法，外部无法随意构建User，只能通过UserManager工具类构建User对象
     */
    protected User() {}
    protected User(String name, String username, String password, String gender) {
        this.id = new Date().getTime();
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
    }
    protected User(String id, String name, String username, String password, String gender) {
        this.id = Long.parseLong(id);
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
}
