package pages.homePage;

import beans.user.User;
import beans.user.UserManager;
import pages.Page;

/**
 * “未登录界面”的页面
 */
public class ToLogInHomePage extends Page implements Login {
    public ToLogInHomePage() {
        pages.put(getClass(), this);
    }

    /**
     * 进入未登录界面,并实现其功能,退出时返回成功登录的界面首页
     * @return 已登录首页
     */
    @Override
    public Page execute() {
        user = UserManager.getDefaultUser(); // 如果没有默认用户，则不自动登录，user==null
        if (user == null) {
            showUI(); // 显示未登录的首页界面
            // 用户选择选项
            while (user == null) { // 不断循环直到输入合法
                switch (getInput()) {
                    case "0":  // 0.退出程序
                        System.exit(0);
                    case "1":  // 1.用户登录
                        // 用户登录
                        if ((user = signIn()) == null) // 若用户输入0,返回未登录界面（自己）
                            return this;
                        break;
                    case "2":  // 2.新用户注册并登录
                        // 注册 && 登录
                        if (registerNewAccount() == null || (user = signIn()) == null) // 若用户输入0,返回未登录界面（自己）
                            return this;
                        break;
                    case "3":  // 3.游客登录
                        user = UserManager.getTouristUser(); // 游客登录成功，修改user值
                        break;
                    default: // 非法输入
                        System.out.println("*****非法的输入！请重新输入！*****");
                        break;
                }
            }
        }
        // 返回登录后界面
        return nextPage(LoggedInHomePage.class);
    }

    /**
     * 显示未登录首页的UI
     */
    @Override
    protected void showUI() {
        System.out.println("**********未登录界面************");
        System.out.println("尊敬的用户，您尚未登录！");
        System.out.println("选项：（0.退出程序）");
        System.out.println("\t1.用户登录\t2.注册账户并登录");
        System.out.println("\t3.游客登录");
        System.out.println("******************************");
    }

    private void showErrorMessage(String errorMessage) {

        System.out.println("*****"+errorMessage+"！*****");
        System.out.println("选项：");
        System.out.println("\t0.退出程序\t1.返回首页...");
        System.out.println("\t输入其他键继续注册...");
    }

    /**
     * 登录功能，登录成功返回user值，否则返回null
     * @return user值
     */
    public User signIn() {
        System.out.println("\n----------登录中----------");
        while (true) { // 持续登录直到成功
            String username = getInput("用户名");
            String password = getInput("密码");
            // 获取失败，返回null；否则返回对应User对象
            if ((user = UserManager.getUser(username, password)) != null) { // 检验是否正确
                System.out.println("----------登录成功，欢迎使用！----------");
                if (getInput("下次是否自动登录", "1.是", "2.否").equals("1")) {
                    UserManager.setDefaultUser(username);
                }
                return user; // 登录成功，返回user值
            } else {
                showErrorMessage("用户名或密码错误");
                switch (getInput()) {
                    case "0":
                        System.exit(0); // 退出程序
                    case "1":
                        return null; // 退出登录界面，返回首页
                }
            }
        }
    }

    /**
     * 注册功能，注册新用户
     *
     * @return 新用户的User对象
     */
    @Override
    public User registerNewAccount() {
        System.out.println("\n----------注册中----------");
        String username = getInput("用户名");
        while (UserManager.containsUser(username)) { // 循环输入直到用户名不重复
            showErrorMessage("用户名重复");
            switch (getInput()) {
                case "0":
                    System.exit(0); // 退出程序
                case "1":
                    return null; // 退出注册界面，返回首页
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
        return UserManager.addUser(name, username, password, gender);
    }
}
