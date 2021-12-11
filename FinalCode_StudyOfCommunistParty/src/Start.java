import pages.Page;
import pages.homePage.ToLogInHomePage;

public class Start {
    public static void main(String[] args) {
        Page curHome = new ToLogInHomePage();
        while (curHome != null) {
            curHome = curHome.execute();
        }
    }
}
