package pages;

import beans.user.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 页面抽象类
 */
public abstract class Page {
    protected static final Map<Class<? extends Page>, Page> pages = new HashMap<>();
    private static final Scanner sc = new Scanner(System.in);

    protected static User user; // 当前页面的用户

    /**
     * 执行当前首页功能，返回下一个执行界面
     *
     * @return 下一个执行界面
     */
    public abstract Page execute();

    /**
     * 将该界面的UI打印在控制台
     */
    protected abstract void showUI();

    protected String getInput() {
        return getInput("选择");
    }

    protected String getInput(String item, String... message) {
        System.out.print("◉请输入" + item + "：");
        if (message.length > 0)
            System.out.println("（" + String.join("\t", message) + "）");
        return sc.nextLine();
    }

    //protected abstract Page handleInput();

    /**
     * 执行某动作后，使程序等待1秒，然后转调至未登录界面
     *
     * @param message 跳转前执行的动作
     */
    protected void waitOneSecond(String message, String destination) {
        System.out.println("----------" + message + "1s后跳转至" + destination + "----------");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected Page nextPage(Class<? extends Page> pageClass) {
        if (pages.containsKey(pageClass)) {
            return pages.get(pageClass);
        } else {
            Page newPage = null;
            try {
                newPage = pageClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            pages.put(pageClass, newPage);
            return newPage;
        }
    }

}
