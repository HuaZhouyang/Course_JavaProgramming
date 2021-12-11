package pages.funcPage.msgPage;

import beans.User;
import pages.Page;

/**
 * “我的信息”页面
 */
public class MessagePage extends Page {

    public MessagePage(User user) {
        super(user);
    }

    @Override
    public Page execute() {
        showUI();
        // 用户选择选项
        while (true) { // 不断循环直到输入合法
            switch (getInput()) {
                case "0": // 0.退出程序
                    System.exit(0);
                case "1": // 1.返回首页:返回已登录首页
                    return pages.get(PageType.loggedInHomePage);
                case "2": // 2.答题历史:进入（返回）答题历史页面
                    PageType resPage = PageType.historyPage;
                    if (pages.containsKey(resPage)) {
                        return pages.get(resPage).setUser(user);
                    } else {
                        Page newPage = new HistoryPage(user);
                        pages.put(resPage, newPage);
                        return newPage;
                    }
                case "3": // 3.退出登录:返回未登录首页
                    return pages.get(PageType.toLogInHomePage);
                case "4": // 4.注销账户:删除账户,并返回未登录首页
                    System.out.println("注销账户后，您的账户及其数据将被永久删除。");
                    System.out.println("您确定要注销吗？\t1.是\t2.否");
                    if (getInput().equals("1")) {
                        User.deleteUser(user.getUsername());
                        return pages.get(PageType.toLogInHomePage);
                    }
                    break;
                default: // 非法输入
                    System.out.println("*****非法的输入！请重新输入！*****");
            }
        }
    }

    @Override
    protected void showUI() {
        System.out.println("*************************");
        System.out.println("我的信息：");
        System.out.println("\tid：" + user.getId());
        System.out.println("\t昵称：" + user.getName());
        System.out.println("\t用户名：" + (user.getUsername() == null ? "无" : user.getUsername()));
        System.out.println("\t性别：" + (user.getGender() == null ? "保密" : user.getGender()));
        System.out.println("选项：");
        System.out.println("\t0.退出程序\t1.返回首页");
        System.out.println("\t2.答题历史\t3.退出登录");
        System.out.println("\t4.注销账户");
        System.out.println("*************************");
    }
}
