package pages;

import beans.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 页面抽象类
 */
public abstract class Page {
    protected enum PageType {
        toLogInHomePage, // “待登录首页”的页面
        loggedInHomePage, // “已登录的首页”页面
        studyPage, // “学习”界面
        questionPage, // “答题”界面
        messagePage, // “我的信息”页面
        historyPage // “答题历史”界面
    }
    protected static final Map<PageType, Page> pages = new HashMap<>();

    private static final Scanner sc = new Scanner(System.in);
    protected User user;

    public Page(User user) {
        this.user = user;
    }

    public Page setUser(User user) {
        this.user = user;
        return this;
    }

    public abstract Page execute(); // 执行当前首页功能，返回下一个执行界面
    protected abstract void showUI();
    protected String getInput() {
        return getInput("选择");
    }
    protected String getInput(String item, String... message) {
        System.out.println("◉请输入您的" + item + "：");
        if (message.length > 0)
            System.out.println("（" + String.join("\t", message) + "）");
        return sc.next();
    }
}
