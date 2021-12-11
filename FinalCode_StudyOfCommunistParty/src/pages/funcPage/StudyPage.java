package pages.funcPage;

import beans.User;
import pages.Page;

/**
 * “学习”界面
 */
public class StudyPage extends Page {
    public StudyPage(User user) {
        super(user);
    }

    @Override
    public Page execute() {
        return null;
    }

    @Override
    protected void showUI() {

    }
}
