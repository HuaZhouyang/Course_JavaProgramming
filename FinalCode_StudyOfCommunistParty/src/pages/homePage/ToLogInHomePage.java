package pages.homePage;

import pages.Page;
import users.User;

/**
 * “待登录首页”的页面
 */
public class ToLogInHomePage extends Page implements Login {
    public ToLogInHomePage() {
        super(null);
        pages.put(PageType.toLogInHomePage, this);
    }
    //private LoggedInHomePage loggedInHomePage = null;

    /* 进入未登录界面,并实现其功能,退出时返回成功登录的界面 */
    @Override
    public Page execute() {
        showUI(); // 显示未登录的首页界面
        User user = null;
        // 用户选择选项
        while (user == null) { // 不断循环直到输入合法
            switch (getInput()) {
                case "0":  // 0.退出程序
                    System.exit(0);
                case "1":  // 1.用户登录
                    // 用户登录
                    if ((user = signIn()) == null) { // 若用户输入0,返回未登录界面（自己）
                        return this;
                    }
                    break;
                case "2":  // 2.新用户注册并登录
                    // 注册 && 登录
                    if (registerNewAccount() == null || (user = signIn()) == null) { // 若用户输入0,返回未登录界面（自己）
                        return this;
                    }
                    break;
                case "3":  // 3.游客登录
                    user = User.getDefaultUser(); // 游客登录成功，修改user值
                    break;
                default: // 非法输入
                    System.out.println("*****非法的输入！请重新输入！*****");
                    break;
            }
        }
        // 返回登录后界面
        if (pages.containsKey(PageType.loggedInHomePage)) {
            return pages.get(PageType.loggedInHomePage).setUser(user);
        } else {
            Page newPage = new LoggedInHomePage(user);
            pages.put(PageType.loggedInHomePage, newPage);
            return newPage;
        }
    }

    /* 显示未登录的的首页UI */
    @Override
    protected void showUI() {
        System.out.println("******************************");
        System.out.println("尊敬的用户，您尚未登录！");
        System.out.println("选项：");
        System.out.println("\t0.退出程序\t\t1.用户登录");
        System.out.println("\t2.注册账户并登录\t3.游客登录");
        System.out.println("******************************");
    }

    /* 登录功能，登录成功返回true，否则返回false */
    public User signIn() {
        System.out.println("\n----------登录中----------");
        while (true) { // 持续登录直到成功
            String username = getInput("用户名");
            String password = getInput("密码");
            User user = User.getUser(username); // 用户名不存在，返回null；否则返回对应User对象
            if (user != null && user.getPassword().equals(password)) { // 检验是否正确
                System.out.println("----------登录成功，欢迎使用！----------");
                return user; // 登录成功，返回user值
            } else {
                System.out.println("*****用户名或密码错误！*****");
                System.out.println("输入数字0返回首页...");
                System.out.println("输入其他键继续登录...");
                if (Integer.parseInt(getInput()) == 0) {
                    return null; // 退出登录程序
                }
            }
        }
    }

    /* 注册功能，注册新用户 */
    @Override
    public User registerNewAccount() {
        System.out.println("\n----------注册中----------");
        String username = getInput("用户名");
        while (User.containsUser(username)) {
            System.out.println("*****用户名重复！*****");
            System.out.println("输入数字0返回首页...");
            System.out.println("输入其他键继续注册...");
            if (Integer.parseInt(getInput()) == 0) {
                return null; // 退出登录程序
            }
            username = getInput("用户名");
        }
        String password = getInput("密码");
        String name = getInput("昵称");
        String genderSelect = getInput("性别", "1.男", "2.女", "3.保密"), gender = null;
        while (genderSelect != null) { // 不断循环直到输入合法
            switch (genderSelect) {
                case "1":
                    gender = "男";
                    genderSelect = null; // 输入合法,循环退出条件
                    break;
                case "2":
                    gender = "女";
                    genderSelect = null;
                    break;
                case "3":
                    genderSelect = null;
                    break;
                default: // 非法输入
                    System.out.println("*****非法的输入！请重新输入！*****");
            }
        }
        System.out.println("----------注册成功，欢迎使用！----------");
        return User.addUser(name, username, password, gender);
    }
}
