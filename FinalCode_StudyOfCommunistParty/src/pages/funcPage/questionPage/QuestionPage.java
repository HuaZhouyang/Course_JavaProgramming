package pages.funcPage.questionPage;

import beans.question.Question;
import beans.question.QuestionManager;
import beans.question.choiceQuestion.MultipleChoiceQuestion;
import beans.question.choiceQuestion.SingleChoiceQuestion;
import beans.question.normalQuestion.FillInBlanksQuestion;
import beans.question.normalQuestion.TrueOrFalseQuestion;
import pages.Page;
import pages.homePage.LoggedInHomePage;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * “答题”界面
 */
public class QuestionPage extends Page {

    @Override
    public Page execute() {
        showUI();
        // 用户选择选项
        List<? extends Question> allQuestions = null;
        boolean flag = true;
        while (flag) { // 不断循环直到输入合法
            switch (getInput()) {
                case "0": // 0.退出程序
                    System.exit(0);
                case "1": // 1.单选题
                    allQuestions = new ArrayList<>(QuestionManager.getQuestionsList(inputSetNum(), SingleChoiceQuestion.class));
                    flag = false;
                    break;
                case "2": // 2.多选题
                    allQuestions = new ArrayList<>(QuestionManager.getQuestionsList(inputSetNum(), MultipleChoiceQuestion.class));
                    flag = false;
                    break;
                case "3": // 3.判断题
                    allQuestions = new ArrayList<>(QuestionManager.getQuestionsList(inputSetNum(), TrueOrFalseQuestion.class));
                    flag = false;
                    break;
                case "4": // 4.填空题
                    allQuestions = new ArrayList<>(QuestionManager.getQuestionsList(inputSetNum(), FillInBlanksQuestion.class));
                    flag = false;
                    break;
                case "5": // 5.随机答题
                    allQuestions = new ArrayList<>(QuestionManager.getAllQuestions(inputSetNum()));
                    flag = false;
                    break;
                case "6": // 6.返回首页
                    return getPage(LoggedInHomePage.class);
                default: // 非法输入
                    System.out.println("*****非法的输入！请重新输入！*****");
            }
        }
        return toAnsweringPage(allQuestions);
    }

    /**
     * 让用户输入，要作答的题集编号
     * @return 要作答的题集编号
     */
    private int inputSetNum() {
        while (true) { // 不断循环直到输入合法
            try {
                int setNum = Integer.parseInt(getInput("您要作答第几套题", "1 <= 题集编号 <= " + QuestionManager.getTotalQuestionSetNum()));
                if (QuestionManager.hasSuchQuestionSet(setNum)) {
                    return setNum;
                } else {
                    System.out.println("*****没有该题集！请重新输入！*****");
                }
            } catch (NumberFormatException e) { // 非法输入
                System.out.println("*****非法的输入！请重新输入！*****");
            }
        }
    }

    @Override
    protected void showUI() {
        System.out.println("\r\n***********答题界面************");
        System.out.println("尊敬的" + user.getName() + "，欢迎您进行党史答题！");
        System.out.println("选项：（0.退出程序）");
        System.out.println("\t1.单选题\t\t2.多选题");
        System.out.println("\t3.判断题\t\t4.填空题");
        System.out.println("\t5.随机答题\t6.返回首页");
        System.out.println("******************************");
    }


    protected Page toAnsweringPage(List<? extends Question> arg) {
        Page page = null;
        try {
            if (pages.containsKey(AnsweringPage.class)) {
                page = pages.get(AnsweringPage.class);
                Method setInitArgs = AnsweringPage.class.getMethod("setAllQuestions", List.class);
                setInitArgs.invoke(page, arg);
            } else {
                page = AnsweringPage.class.getConstructor(List.class).newInstance(arg);
                pages.put(AnsweringPage.class, page);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return page;
    }

}
