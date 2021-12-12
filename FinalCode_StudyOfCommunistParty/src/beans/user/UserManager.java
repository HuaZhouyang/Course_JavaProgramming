package beans.user;

import java.io.*;
import java.util.Properties;

public class UserManager {
    private static User touristUser = null;
    private static final String srcUsers = "FinalCode_StudyOfCommunistParty\\data" + "\\users";

    /**
     * 获取“游客”对象
     *
     * @return “游客”对象
     */
    public static User getTouristUser() {
        if (touristUser == null) {
            touristUser = new User("游客", null, null, null);
        }
        return touristUser;
    }

    /**
     * 获取默认用户，如果不为null则自动登录
     *
     * @return 默认对象
     */
    public static User getDefaultUser() {
        String defaultUsername = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(srcUsers + "\\defaultUser.txt"))) {
            defaultUsername = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defaultUsername == null ? null : getUser(defaultUsername);
    }

    /**
     * 设置默认用户
     */
    public static void setDefaultUser(String username) {
        try (FileWriter writer = new FileWriter(srcUsers + "\\defaultUser.txt")) {
            writer.write(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeDefaultUser() {
        setDefaultUser("");
    }

    /**
     * 检查“数据库”中是否存在指定用户
     *
     * @param username 用户名
     * @return 是否存在指定用户
     */
    public static boolean containsUser(String username) {
        return new File(srcUsers, username + ".properties").exists();
    }

    /**
     * 从“数据库”中获取指定用户的User对象。如果用户名不存在，返回null
     *
     * @param username 用户名
     * @param password 密码
     * @return 指定用户的User对象
     */
    public static User getUser(String username, String password) {
        File userFile = new File(srcUsers, username + ".properties");
        if (!userFile.exists()) return null; // 如果用户名不存在，返回null
        // 从“数据库”中获取数据
        Properties prop = new Properties();
        try (FileReader fr = new FileReader(userFile)) {
            prop.load(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 返回指定用户的User对象
        if (password.equals(prop.getProperty("password"))) {
            return new User(prop.getProperty("id"), prop.getProperty("name"), prop.getProperty("username"),
                    password, prop.getProperty("gender"));
        } else {
            return null;
        }
    }

    private static User getUser(String username) {
        File userFile = new File(srcUsers, username + ".properties");
        if (!userFile.exists()) return null; // 如果用户名不存在，返回null
        // 从“数据库”中获取数据
        Properties prop = new Properties();
        try (FileReader fr = new FileReader(userFile)) {
            prop.load(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 返回指定用户的User对象
        return new User(prop.getProperty("id"), prop.getProperty("name"), prop.getProperty("username"),
                prop.getProperty("password"), prop.getProperty("gender"));
    }

    /**
     * 向“数据库”中添加指定用户，并返回新建的User对象
     *
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
        prop.setProperty("id", String.valueOf(newUser.getId()));
        prop.setProperty("name", newUser.getName());
        prop.setProperty("username", newUser.getUsername());
        prop.setProperty("password", newUser.getPassword());
        prop.setProperty("gender", newUser.getGender());
        // 向“数据库”中存储数据
        try (FileWriter fw = new FileWriter(srcUsers + "\\" + username + ".properties")) {
            prop.store(fw, "Information of User");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 返回新建的User对象
        return newUser;
    }

    /**
     * 从“数据库”中删除指定用户。如果删除失败（如用户名不存在），返回false，否则返回true
     *
     * @param username 用户名
     * @return 指定用户是否存在
     */
    public static boolean deleteUser(String username) {
        return new File(srcUsers, username + ".properties").delete();
    }
}
