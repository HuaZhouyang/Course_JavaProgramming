package pages.homePage;

import pages.Page;
import pages.funcPage.StudyPage;
import pages.funcPage.msgPage.MessagePage;
import pages.funcPage.questionPage.QuestionPage;

/**
 * “已登录首页”页面
 */
public class LoggedInHomePage extends Page {

    /* 进入已登录界面,并实现其功能,退出时返回用户选择的界面 */
    @Override
    public Page execute() {
        showUI();
        PageType resPage;
        // 用户选择选项
        while (true) { // 不断循环直到输入合法
            switch (getInput()) {
                case "0": // 0.退出程序
                    System.exit(0);
                case "1": // 1.学习
                    // 返回用户选择的功能界面
                    resPage = PageType.studyPage;
                    if (pages.containsKey(resPage)) {
                        return pages.get(resPage);
                    } else {
                        Page newPage = new StudyPage();
                        pages.put(resPage, newPage);
                        return newPage;
                    }
                case "2": // 2.答题
                    // 返回用户选择的功能界面
                    resPage = PageType.questionPage;
                    if (pages.containsKey(resPage)) {
                        return pages.get(resPage);
                    } else {
                        Page newPage = new QuestionPage();
                        pages.put(resPage, newPage);
                        return newPage;
                    }
                case "3": // 3.我的信息
                    // 返回用户选择的功能界面
                    resPage = PageType.messagePage;
                    if (pages.containsKey(resPage)) {
                        return pages.get(resPage);
                    } else {
                        Page newPage = new MessagePage();
                        pages.put(resPage, newPage);
                        return newPage;
                    }
                default: // 非法输入
                    System.out.println("*****非法的输入！请重新输入！*****");
            }
        }
    }

    /* 显示登录成功后的首页UI */
    @Override
    protected void showUI() {
        System.out.println("******************************");
        System.out.println("尊敬的" + user.getName() + "，您好！");
        System.out.println("选项：（0.退出程序）");
        System.out.println("\t1.学习\t2.答题");
        System.out.println("\t3.我的信息");
        System.out.println("******************************");
    }

}
