package beans.question;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class QuestionManager {
    /*private static class QuestionType {
        public static final QuestionType trueOrFalseQuestion = new QuestionType();
        public static final QuestionType fillInBlanksQuestion = new QuestionType();
        //singleChoiceQuestion, multipleChoiceQuestion, allChoiceQuestion, trueOrFalseQuestion, fillInBlanksQuestion
    }
    private static class ChoiceQuestionType extends QuestionType {
        public static final ChoiceQuestionType singleChoiceQuestion = new ChoiceQuestionType();
        public static final ChoiceQuestionType multipleChoiceQuestion = new ChoiceQuestionType();
        public static final ChoiceQuestionType allChoiceQuestion = new ChoiceQuestionType();
    }
    public static final ChoiceQuestionType singleChoiceQuestion = ChoiceQuestionType.singleChoiceQuestion;
    public static final ChoiceQuestionType multipleChoiceQuestion = ChoiceQuestionType.multipleChoiceQuestion;
    public static final ChoiceQuestionType allChoiceQuestion = ChoiceQuestionType.allChoiceQuestion;
    public static final QuestionType trueOrFalseQuestion = QuestionType.trueOrFalseQuestion;
    public static final QuestionType fillInBlanksQuestion = QuestionType.fillInBlanksQuestion;
    */
    private static final String srcQuestions = "FinalCode_StudyOfCommunistParty\\data\\questions";
    private static int setNum = 1;

    private static String getSetTitle() {
        return "set-" + (setNum < 10 ? "0" + setNum : setNum);
    }
    public static int getSetNum() {
        return setNum;
    }
    public static void setSetNum(int setNum) {
        QuestionManager.setNum = setNum;
    }

    /**
     * 检查输入的答案是否正确
     * @param question 问题
     * @param answer 答案
     * @return 代表答案是否正确的布尔值
     */
    public boolean checkAnswer(Question question, String... answer) {
        return question.checkAnswer(answer);
    }

    /**
     * 获取所有单选题，并以Map的形式返回，key为id
     * @return 包含所有单选题的map集合
     */
    public static Map<String, ChoiceQuestion> getAllSingleChoiceQuestions() {
        return getAllChoiceQuestions("single-choice.xml");
    }
    /**
     * 获取指定id值的单选题
     * @param id 题目序号
     * @return 指定id值的单选题
     */
    public static ChoiceQuestion getSingleChoiceQuestionById(String id) {
        return getAllSingleChoiceQuestions().get(id);
    }

    /**
     * 获取所有多选题，并以Map的形式返回，key为id
     * @return 包含所有多选题的map集合
     */
    public static Map<String, ChoiceQuestion> getAllMultipleChoiceQuestions() {
        return getAllChoiceQuestions("multiple-choice.xml");
    }
    /**
     * 获取指定id值的单选题
     * @param id 题目序号
     * @return 指定id值的单选题
     */
    public static ChoiceQuestion getMultipleChoiceQuestionById(String id) {
        return getAllSingleChoiceQuestions().get(id);
    }

    private static Map<String, ChoiceQuestion> getAllChoiceQuestions(String xmlName) {
        Elements questionElements = getQuestionElements(xmlName);
        Map<String, ChoiceQuestion> res = new HashMap<>();
        for (Element questionElement : questionElements) { //获取并封装数据
            ChoiceQuestion question = new ChoiceQuestion();
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
            question.setAnswer(answers);
            // 封装A,B,C,D选项字段
            question.setA(questionElement.getElementsByTag("A").get(0).text());
            question.setB(questionElement.getElementsByTag("B").get(0).text());
            question.setC(questionElement.getElementsByTag("C").get(0).text());
            question.setD(questionElement.getElementsByTag("D").get(0).text());
            // 将question添加入集合
            res.put(question.getId(), question);
        }
        return res;
    }
    private static Map<String, Question> getAllQuestions(String xmlName) {
        Elements questionElements = getQuestionElements(xmlName);
        Map<String, Question> res = new HashMap<>();
        for (Element questionElement : questionElements) { //获取并封装数据
            Question question = new Question();
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
            question.setAnswer(answers);
            // 将question添加入集合
            res.put(question.getId(), question);
        }
        return res;
    }

    public Map<String, Question> getAllTrueOrFalseQuestions() {
        return getAllQuestions("true-false.xml");
    }
    public Question getTrueOrFalseQuestionById(String id) {
        return getAllTrueOrFalseQuestions().get(id);
    }
    public Map<String, Question> getAllFillInBlanksQuestions() {
        return getAllQuestions("fill-in-blanks.xml");
    }
    public Question getFillInBlanksQuestionById(String id) {
        return getAllFillInBlanksQuestions().get(id);
    }
    /**
     * 根据指定xml文件名，返回文件中所有question元素对象的集合
     * @param xmlName 指定的xml文件名
     * @return 文件中所有question元素对象的集合
     */
    private static Elements getQuestionElements(String xmlName) {
        //2.1获取xml文档的path
        String path = srcQuestions + "\\" + getSetTitle() + "\\" + xmlName;
        //2.2解析xml文档，加载文档进内存，获取dom树--->Document
        Document document = null;
        try {
            document = Jsoup.parse(new File(path), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //3.获取元素对象集合 Elements
        return document.getElementsByTag("question");
    }
}
