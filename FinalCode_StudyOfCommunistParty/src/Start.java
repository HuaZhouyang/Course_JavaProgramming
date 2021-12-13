import pages.Page;
import pages.homePage.ToLogInHomePage;

public class Start {
    public static void main(String[] args) {
        Page curPage = new ToLogInHomePage(), tmp;
        while (curPage != null) {
            tmp = curPage;
            curPage = curPage.execute();
            Page.lastPage = tmp;
        }
    }
}
