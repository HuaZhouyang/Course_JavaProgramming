package pages.funcPage.questionPage;

import beans.question.Question;
import pages.Page;
import pages.funcPage.msgPage.HistoryPage;
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
        Collections.shuffle(allQuestions); // 乱序输出题目

        int crrSerialNum = 1; // 题目序号
        for (Question crrQuestion : allQuestions) {
            /* 展示题目 */
            System.out.println("************" + crrQuestion.QuestionType() + "*************");
            crrQuestion.printQuestion(crrSerialNum++);
            System.out.println("******************************");

            /* 用户输入选项或答案 */
            String answer;
            int isCorrect = 0; // 1为回答正确，0为回答错误，-1为跳过此题
            answer = getInput("答案", "0.退出程序", "1.显示答案", "2.返回上一页", "3.返回首页");
            // 处理输入
            if (crrQuestion.checkAnswer(answer)) {
                isCorrect = 1;
            } else {
                switch (answer) {
                    case "0": // 0.退出程序
                        System.exit(0);
                    case "1": // 1.显示答案,并跳过此题
                        crrQuestion.printAnswer();
                        isCorrect = -1;
                        break;
                    case "2": // 2.返回
                        return Page.lastPage;
                    case "3": // 3.返回首页
                        return getPage(LoggedInHomePage.class);
                    // default: // 无非法输入
                }
            }
            // 处理回答错误情况
            boolean hasFalse = false;
            while (isCorrect == 0) {
                hasFalse = true;
                System.out.println("---------- ╳回答错误！请重新答题！----------");
                answer = getInput("答案", "0.退出程序", "1.显示答案", "2.返回上一页", "3.返回首页");
                // 处理输入
                if (crrQuestion.checkAnswer(answer)) {
                    isCorrect = 1;
                } else {
                    switch (answer) {
                        case "0": // 0.退出程序
                            System.exit(0);
                        case "1": // 1.显示答案,并跳过此题
                            crrQuestion.printAnswer();
                            isCorrect = -1;
                            break;
                        case "2": // 2.返回
                            return Page.lastPage;
                        case "3": // 3.返回首页
                            return getPage(LoggedInHomePage.class);
                        // default: // 无非法输入
                    }
                }
            }
            if (isCorrect == 1) System.out.println("---------- √恭喜你！回答正确！----------");

            /* 记录答题历史 */
            ((HistoryPage) getPage(HistoryPage.class)).recordQuestionHistory(crrQuestion, hasFalse);

            /* 该题结束，让用户选择下一步选项 */
            showUI();
            // 处理输入
            boolean flag = true;
            while (flag) {
                switch (getInput()) {
                    case "0": // 0.退出程序
                        System.exit(0);
                    case "1": // 1.继续答题
                        flag = false;
                        break;
                    case "2": // 2.返回上一页
                        return Page.lastPage;
                    case "3": // 3.返回首页
                        return getPage(LoggedInHomePage.class);
                    default: // 非法输入
                        System.out.println("*****非法的输入！请重新输入！*****");
                }
            }
        }
        System.out.println("----------恭喜你！第" + allQuestions.get(0).getSetNum() + "套题答题完毕！----------");
        waitOneSecond("答题完毕！", "答题界面");
        return getPage(QuestionPage.class);
    }

    @Override
    protected void showUI() {
        System.out.println("选项：（0.退出程序）");
        System.out.println("\t1.继续答题\t2.返回上一页");
        System.out.println("\t3.返回首页");
    }

}
