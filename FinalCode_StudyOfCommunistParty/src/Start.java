import pages.Page;
import pages.homePage.ToLogInHomePage;

public class Start {
    public static void main(String[] args) {
        Page curPage = new ToLogInHomePage(), tmp;
        while (curPage != null) {
            tmp = curPage;
            curPage = curPage.execute(); // execute()返回下一个执行界面，通过循环切换页面
            Page.lastPage = tmp; // 保存上一页，以便用户执行“返回上一页”选项
        }
    }
}
