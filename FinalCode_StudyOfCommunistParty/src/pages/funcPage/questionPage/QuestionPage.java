package pages.funcPage.questionPage;

import beans.user.UserManager;
import pages.Page;

/**
 * “答题”界面
 */
public class QuestionPage extends Page {

    @Override
    public Page execute() {
        showUI();
        // 用户选择选项
        while (true) { // 不断循环直到输入合法
            switch (getInput()) {
                case "0": // 0.退出程序
                    System.exit(0);
                case "1": // 1.单选题
                    return nextPage(PageType.singleChoicePage);
                case "2": // 2.多选题
                    return nextPage(PageType.multipleChoicePage);
                case "3": // 3.判断题
                case "4": // 4.填空题
                    System.out.println("注销账户后，您的账户及其数据将被永久删除。");
                    System.out.println("您确定要注销吗？\t1.是\t2.否");
                    if (getInput().equals("1")) {
                        UserManager.deleteUser(user.getUsername());
                        waitSomeTime("注销", 1000, "首页");
                        UserManager.removeDefaultUser();
                        return pages.get(PageType.toLogInHomePage);
                    }
                    break;
                case "5": // 5.随机答题

                case "6": // 6.返回首页
                    waitSomeTime("退出", 1000, "首页");
                    UserManager.removeDefaultUser();
                    return pages.get(PageType.toLogInHomePage);
                default: // 非法输入
                    System.out.println("*****非法的输入！请重新输入！*****");
            }
        }
    }

    @Override
    protected void showUI() {
        System.out.println("******************************");
        System.out.println("尊敬的" + user.getName() + "，欢迎您进行党史答题！");
        System.out.println("选项：（0.退出程序）");
        System.out.println("\t1.单选题\t2.多选题");
        System.out.println("\t3.判断题\t4.填空题");
        System.out.println("\t5.随机答题\t6.返回首页");
        System.out.println("******************************");
    }
}
