package pages.funcPage.questionPage;

import beans.question.Question;
import beans.question.QuestionManager;
import pages.Page;
import pages.homePage.LoggedInHomePage;

import java.util.Collections;
import java.util.List;

/**
 * ”答题中“界面
 */
public class AnsweringPage extends Page {
    List<? extends Question> allQuestions;

    public AnsweringPage(List<? extends Question> allQuestions) {
        this.allQuestions = allQuestions;
    }

    public void setAllQuestions(List<? extends Question> allQuestions) {
        this.allQuestions = allQuestions;
    }

    @Override
    public Page execute() {
        while (true) {
            Collections.shuffle(allQuestions);

            int crrSerialNum = 1;
            for (Question crrQuestion : allQuestions) {
                System.out.println("************"+crrQuestion.QuestionType()+"*************");
                crrQuestion.printQuestion(crrSerialNum++);
                System.out.println("******************************");
                String answer;
                boolean isCorrect = false;
                while (true) {
                    answer = getInput("答案", "0.退出程序", "1.显示答案");
                    if (crrQuestion.checkAnswer(answer)) {
                        isCorrect = true;
                        break;
                    } else {
                        if (answer.equals("0")) { // 0.退出程序
                            System.exit(0);
                        } else if (answer.equals("1")) { // 1.显示答案,并跳过此题
                            crrQuestion.printAnswer();
                            break;
                        } // else // 无非法输入
                    }
                    System.out.println("---------- ╳回答错误！请重新答题！----------");
                }
                if (isCorrect)
                    System.out.println("---------- √恭喜你！回答正确！----------");
                showUI();
                boolean flag = true;
                while (flag) {
                    switch (getInput()) {
                        case "0":
                            System.exit(0);
                        case "1":
                            flag = false;
                            break;
                        case "2":
                            return nextPage(LoggedInHomePage.class);
                        default: // 非法输入
                            System.out.println("*****非法的输入！请重新输入！*****");
                    }
                }
            }
            if (QuestionManager.nextQuestionSet()) {
                System.out.println("----------恭喜你！第" + (QuestionManager.getQuestionSetNum() - 1) + "套题答题完毕！----------");
                showUI();
                boolean flag = true;
                while (flag) {
                    switch (getInput()) {
                        case "0":
                            System.exit(0);
                        case "1":
                            flag = false;
                            break;
                        case "2":
                            return nextPage(LoggedInHomePage.class);
                        default: // 非法输入
                            System.out.println("*****非法的输入！请重新输入！*****");
                    }
                }
            } else {
                waitOneSecond("恭喜你！已答完所有"+allQuestions.get(0).QuestionType()+"！", "首页");
                return nextPage(LoggedInHomePage.class);
            }
        }
    }

    @Override
    protected void showUI() {
        System.out.println("选项：（0.退出程序）");
        System.out.println("\t1.继续答题\t2.返回首页");
    }

}
