package users;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class User {
    private static final Map<String, User> users = new HashMap<>();
    private static User defaultUser;

    /**
     * 获取“游客”对象
     * @return “游客”对象
     */
    public static User getDefaultUser() {
        if (defaultUser == null) {
            return (defaultUser = new User("游客", null, null, null));
        }
        return defaultUser;
    }

    /**
     * 检查“数据库”中是否存在指定用户
     * @param username 用户名
     * @return 是否存在指定用户
     */
    public static boolean containsUser(String username) {
        return new File("FinalCode_StudyOfCommunistParty\\data\\users", username + ".properties").exists();
    }
    /*public static boolean containsUser(String username) {
        return users.containsKey(username);
    }*/

    /**
     * 从“数据库”中获取指定用户的User对象。如果用户名不存在，返回null
     * @param username 用户名
     * @return 指定用户的User对象
     */
    public static User getUser(String username) {
        File userFile = new File("FinalCode_StudyOfCommunistParty\\data\\users", username + ".properties");
        if (!userFile.exists()) return null; // 如果用户名不存在，返回null
        // 从“数据库”中获取数据
        Properties prop = new Properties();
        try (FileReader fr = new FileReader(userFile)){
            prop.load(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 返回指定用户的User对象
        return new User(prop.getProperty("id"), prop.getProperty("name"), prop.getProperty("username"),
                prop.getProperty("password"), prop.getProperty("gender"));
    }
    /*public static User getUser(String username) {
        return users.getOrDefault(username, null);
    }*/


    /**
     * 向“数据库”中添加指定用户，并返回新建的User对象
     * @param name     昵称
     * @param username 用户名
     * @param password 密码
     * @param gender   性别
     * @return 新建的User对象
     */
    public static User addUser(String name, String username, String password, String gender) {
        User newUser = new User(name, username, password, gender);
        Properties prop = new Properties();
        // 将新建的User对象的数据存储到IO流中
        prop.setProperty("id", String.valueOf(newUser.id));
        prop.setProperty("name", newUser.name);
        prop.setProperty("username", newUser.username);
        prop.setProperty("password", newUser.password);
        prop.setProperty("gender", newUser.gender);
        // 向“数据库”中存储数据
        try (FileWriter fw = new FileWriter("FinalCode_StudyOfCommunistParty\\data\\users\\" + username + ".properties")){
            prop.store(fw, "Information of User");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 返回新建的User对象
        return newUser;
    }
    /*public static User addUser(String name, String username, String password, String gender) {
        User newUser = new User(name, username, password, gender);
        users.put(username, newUser);
        return newUser;
    }*/

    /**
     * 从“数据库”中删除指定用户。如果删除失败（如用户名不存在），返回false，否则返回true
     * @param username 用户名
     * @return 指定用户是否存在
     */
    public static boolean deleteUser(String username) {
        return new File("FinalCode_StudyOfCommunistParty\\data\\users", username + ".properties").delete();
    }
    /*public static void deleteUser(String username) {
        users.remove(username);
    }*/

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
    private User(String id, String name, String username, String password, String gender) {
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
