package pages.funcPage.msgPage;

import beans.question.Question;
import beans.question.QuestionManager;
import pages.Page;
import pages.funcPage.questionPage.QuestionPage;
import pages.homePage.LoggedInHomePage;

import java.io.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * “答题历史”界面
 */
public class HistoryPage extends QuestionPage {
    private final String historiesSrc = "FinalCode_StudyOfCommunistParty\\data\\histories";

    /**
     * 记录答题历史
     * @param questionToBeRecorded 待记录的题目
     * @param hasFalse 是否回答错误
     */
    public void recordQuestionHistory(Question questionToBeRecorded, boolean hasFalse) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(historiesSrc + user.getUsername() + ".properties", true))) {
            writer.write(new Date().getTime() + ":" + questionToBeRecorded.getSetNum() + ":" + questionToBeRecorded.getClass().getName() + ":" + questionToBeRecorded.getId() + ":" + (hasFalse ? "错误" : "正确"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印答题历史，从第begin条记录开始打印limit条，并返回本页所有Question对象组成的List集合
     *
     * @param begin 从第几个历史记录开始，索引从1开始
     * @param limit 打印几条历史记录
     * @return 本页所有Question对象组成的List集合
     */
    public List<Question> printQuestionHistory(int begin, int limit) {
        List<Question> res = new LinkedList<>();
        String historyRaw;
        Date createdTime = new Date();
        DateFormat formatter = DateFormat.getDateTimeInstance(); // 日期格式化
        int n = 1; // 代表这是第几条历史记录
        int cnt = 0; // 代表打印了几条历史记录
        try (BufferedReader reader = new BufferedReader(new FileReader(historiesSrc + user.getUsername() + ".properties"))) {
            System.out.println("\r\n***********答题记录************");
            while ((historyRaw = reader.readLine()) != null) {
                if (n++ >= begin && cnt++ < limit) { // 找到begin开始的limit条记录
                    // 打印每一条记录
                    System.out.println("----------记录" + cnt + "----------");
                    String[] info = historyRaw.split(":");
                    // 答题时间
                    createdTime.setTime(Long.parseLong(info[0]));
                    System.out.println("答题时间：" + formatter.format(createdTime));
                    // 答题信息
                    int setNum = Integer.parseInt(info[1]);
                    String questionClassName = info[2];
                    Class<? extends Question> questionClass = (Class<? extends Question>) Class.forName(questionClassName);
                    String id = info[3];
                    Question question = QuestionManager.getQuestionById(setNum, questionClass, id);
                    String hasFalse = info[4];
                    System.out.println("答题信息：第" + setNum + "套-" + question.QuestionType() + "第" + id + "题-回答" + hasFalse);
                    // 题目描述
                    System.out.print("题目描述：");
                    question.printQuestion(Integer.parseInt(id));
                    // 把题目加入List
                    res.add(question);
                }
            }
            System.out.println("******************************");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public Page execute() {
        int begin = 1, limit = 5;
        List<Question> questionList;
        while (true) {
            questionList = printQuestionHistory(begin, limit);
            showUI();
            // 用户选择选项
            boolean flag = true;
            while (flag) { // 不断循环直到输入合法
                switch (getInput()) {
                    case "0":  // 0.退出程序
                        System.exit(0);
                    case "1":  // 1.下一页
                        begin += limit;
                        flag = false;
                        break;
                    case "2":  // 2.修改每页条数
                        // 用户输入每页条数
                        while (true) { // 不断循环直到输入合法
                            try {
                                limit = Integer.parseInt(getInput("每页条数"));
                                break;
                            } catch (NumberFormatException e) { // 非法输入
                                System.out.println("*****非法的输入！请重新输入！*****");
                            }
                        }
                        flag = false;
                        break;
                    case "3":  // 3.作答本页题目
                        return toAnsweringPage(questionList);
                    case "4": // 4.返回上一页
                        return Page.lastPage;
                    case "5": // 5.返回首页
                        return getPage(LoggedInHomePage.class);
                    default: // 非法输入
                        System.out.println("*****非法的输入！请重新输入！*****");
                }
            }
        }
    }

    @Override
    protected void showUI() {
        System.out.println("选项：（0.退出程序）");
        System.out.println("\t1.下一页\t\t\t2.修改每页条数");
        System.out.println("\t3.作答本页题目\t4.返回上一页");
        System.out.println("\t5.返回首页");
    }
}
