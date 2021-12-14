package beans.question;

import beans.question.choiceQuestion.ChoiceQuestion;
import beans.question.choiceQuestion.MultipleChoiceQuestion;
import beans.question.choiceQuestion.SingleChoiceQuestion;
import beans.question.normalQuestion.FillInBlanksQuestion;
import beans.question.normalQuestion.TrueOrFalseQuestion;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class QuestionManager {
    private static Document document = null;
    /**
     * 数组索引代表题集编号，Map索引代表题型，内层数组存储Question
     */
    private static final Map<Class<? extends Question>, Question[]>[] questionMappers = new HashMap[getTotalQuestionSetNum()];
    private static final String questionsPath = "FinalCode_StudyOfCommunistParty\\data\\questions.xml";

    /**
     * 获取总题集编号
     * @return 总题集编号
     */
    public static int getTotalQuestionSetNum() {
        return queryElements("set").size();
    }

    /**
     * 根据输入题集编号，判断这个题集是否存在
     * @param setNum 所选题集编号
     * @return 代表这个题集是否存在的布尔值
     */
    public static boolean hasSuchQuestionSet(int setNum) {
        return setNum <= getTotalQuestionSetNum();
    }

    /**
     * 获取所选题集的所有题目（全部题型），并以List的形式返回
     * @param setNum 所选题集编号
     * @return 包含所选题集的所有题目（全部题型）的List集合
     */
    public static List<Question> getAllQuestions(int setNum) {
        List<Question> res = new LinkedList<>();
        res.addAll(getQuestionsList(setNum, SingleChoiceQuestion.class));
        res.addAll(getQuestionsList(setNum, MultipleChoiceQuestion.class));
        res.addAll(getQuestionsList(setNum, TrueOrFalseQuestion.class));
        res.addAll(getQuestionsList(setNum, FillInBlanksQuestion.class));
        return res;
    }

    /**
     * 根据题集编号、题目类型和id获取指定Question对象
     * @param setNum 指定题集编号
     * @param questionClass 题目类型
     * @param id id值
     * @return 指定Question对象
     */
    public static Question getQuestionById(int setNum, Class<? extends Question> questionClass, String id) {
        String cssQuery = "set[id=" + setNum + "]>" + questionClass.getSimpleName() + ">question[id=" + id + "]";
        return encapsulateFromElement(setNum, queryElements(cssQuery).get(0), questionClass);
    }

    /**
     * 获取所选题集的所有指定类型的Question题目，并以List的形式返回
     * @param setNum 所选题集编号
     * @param questionClass 标准题类型
     * @return 包含所选题集的所有指定类型的Question题目的List集合
     */
    public static List<Question> getQuestionsList(int setNum, Class<? extends Question> questionClass) {
        if (questionMappers[setNum - 1] != null && questionMappers[setNum - 1].containsKey(questionClass)) { // 如果内存中已存在对应mapper
            return List.of(questionMappers[setNum - 1].get(questionClass));
        }
        // 内存中不存在，获取并存入集合
        Elements questionElements = getQuestionElements(setNum, questionClass);
        List<Question> res = new ArrayList<>();
        for (Element questionElement : questionElements) { //封装数据
            // 封装数据
            Question question = encapsulateFromElement(setNum, questionElement, questionClass);
            // 将question添加入List集合
            res.add(question);
        }
        if (questionMappers[setNum - 1] == null) {
            questionMappers[setNum - 1] = new HashMap<>();
        }
        // 将mapper加入内存
        questionMappers[setNum - 1].put(questionClass, res.toArray(new Question[0]));
        return res;
    }

    private static Question encapsulateFromElement(int setNum, Element questionElement, Class<? extends Question> questionClass) {
        Question question = null;
        try {
            question = questionClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        // 封装题目是第几套题
        question.setSetNum(setNum);
        // 封装题目id
        question.setId(questionElement.attr("id"));
        // 封装题目描述description字段
        question.setDescription(questionElement.getElementsByTag("description").get(0).text());
        // 封装答案集合answer字段
        Elements answerElements = questionElement.getElementsByTag("answer");
        int size = answerElements.size();
        String[] answers = new String[size];
        for (int i = 0; i < size; i++) {
            answers[i] = answerElements.get(0).text();
        }
        question.setAnswers(answers);
        if (ChoiceQuestion.class.isAssignableFrom(questionClass)) { // 传入的是选择题类
            // 封装A,B,C,D选项字段
            ((ChoiceQuestion) question).setA(questionElement.getElementsByTag("A").get(0).text());
            ((ChoiceQuestion) question).setB(questionElement.getElementsByTag("B").get(0).text());
            ((ChoiceQuestion) question).setC(questionElement.getElementsByTag("C").get(0).text());
            ((ChoiceQuestion) question).setD(questionElement.getElementsByTag("D").get(0).text());
        }
        return question;
    }

    /**
     * 根据指定的指定的Question类型类型，返回文件中所有question元素对象的集合
     * @param questionClass 指定的Question类型
     * @return 文件中所有question元素对象的集合
     */
    private static Elements getQuestionElements(int setNum, Class<? extends Question> questionClass) {
        /* 定义css语句 */
        // 获取id = setNum的set标签下的，指定的Question类型标签下的，question元素
        String cssQuery = "set[id=" + setNum + "]>" + questionClass.getSimpleName() + ">question";
        /* 获取元素对象集合 Elements */
        return queryElements(cssQuery);
    }
    private static Elements queryElements(String cssQuery) {
        /* 解析xml文档，加载文档进内存，获取dom树--->Document */
        if (document == null) {
            try {
                document = Jsoup.parse(new File(questionsPath), "utf-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /* 获取元素对象集合 Elements */
        // 获取id = setNum的set标签下的，指定的Question类型标签下的，question元素
        return document.select(cssQuery);
    }
}
