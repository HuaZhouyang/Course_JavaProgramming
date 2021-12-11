package beans;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class User {
    private static final Map<String, User> users = new HashMap<>();
    private static User defaultUser;

    public static boolean containsUser(String username) {
        return users.containsKey(username);
    }

    public static User getUser(String username) {
        return users.getOrDefault(username, null);
    }

    public static User getDefaultUser() {
        if (defaultUser == null) {
            return (defaultUser = addUser("游客", null, null, null));
        }
        return defaultUser;
    }

    /**
     * @param name     昵称
     * @param username 用户名
     * @param password 密码
     * @param gender   性别
     * @return 新建的User对象
     */
    public static User addUser(String name, String username, String password, String gender) {
        User newUser = new User(name, username, password, gender);
        users.put(username, newUser);
        return newUser;
    }

    public static void deleteUser(String username) {
        users.remove(username);
    }

    private long id;
    private String name;
    private String username;
    private String password;
    private String gender;

    public User() {
    }

    private User(String name, String username, String password, String gender) {
        this.id = new Date().getTime();
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
