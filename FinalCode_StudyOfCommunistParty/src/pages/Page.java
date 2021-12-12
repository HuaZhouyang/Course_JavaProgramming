package pages;

import beans.user.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 页面抽象类
 */
public abstract class Page {
    protected enum PageType {
        toLogInHomePage, // “未登录首页”的页面
        loggedInHomePage, // “已登录首页”页面
        studyPage, // “学习”界面
        questionPage, // “答题”界面
        singleChoicePage, // “单选题”界面
        multipleChoicePage, // “多选题”界面
        trueOrFalsePage, // “判断题”界面
        fillInBlanksPage, // “填空题”界面
        randomQuestionPage, // “随机答题”界面
        messagePage, // “我的信息”页面
        historyPage // “答题历史”界面
    }
    protected static final Map<PageType, Page> pages = new HashMap<>();
    private static final Scanner sc = new Scanner(System.in);

    protected static User user; // 当前页面的用户

    /**
     * 执行当前首页功能，返回下一个执行界面
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
        return sc.next();
    }

    /**
     * 执行某动作后，使程序等待millis毫秒，然后转调至目的地
     * @param act 跳转前执行的动作
     * @param millis 等待的毫秒数
     * @param dest 目的地
     */
    protected void waitSomeTime(String act, long millis, String dest) {
        System.out.println("----------" + act + "成功！" + (millis / 1000) + "s后跳转"+dest+"----------");
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*protected Page nextPage(Class<Page> pageClass) {
        if (pageType == PageType.toLogInHomePage || pages.containsKey(pageType)) {
            return pages.get(pageType);
        } else {
            Page newPage = new HistoryPage();
            pages.put(pageType, newPage);
            return newPage;
        }
    }*/
}
