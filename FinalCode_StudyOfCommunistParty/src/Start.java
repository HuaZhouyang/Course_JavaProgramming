import pages.Page;
import pages.homePage.ToLogInHomePage;

public class Start {
    public static void main(String[] args) {
        Page curPage = new ToLogInHomePage();
        while (curPage != null) {
            curPage = curPage.execute();
        }
    }
}
