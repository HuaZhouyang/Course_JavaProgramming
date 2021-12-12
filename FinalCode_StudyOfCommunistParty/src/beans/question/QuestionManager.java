package beans.question;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
    public static int getTotalQuestionSetNum() {
        return new File(srcQuestions).list().length;
    }
    public static int getQuestionSetNum() {
        return setNum;
    }
    public static void setQuestionSetNum(int setNum) {
        QuestionManager.setNum = setNum;
    }
    public static boolean nextQuestionSet() {
        if (++setNum <= getTotalQuestionSetNum()) {
            return true;
        } else {
            setNum = 1;
            return false;
        }
    }

    /**
     * 检查输入的答案是否正确
     * @param question 问题
     * @param AnswersToBeChecked 答案
     * @return 代表答案是否正确的布尔值
     */
    public static boolean checkAnswer(Question question, String AnswersToBeChecked) {
        return question.checkAnswer(AnswersToBeChecked);
    }

    /**
     * 获取所有题目，并以List的形式返回
     * @return 包含所有题目的List集合
     */
    public static List<Question> getAllQuestions() {
        List<Question> res = new LinkedList<>();
        // TODO:获取所有题目，并以List的形式返回
        return res;
    }

    /**
     * 获取所有单选题，并以Map的形式返回，key为id
     * @return 包含所有单选题的map集合
     */
    public static Map<String, ChoiceQuestion> getAllSingleChoiceQuestionsMapper() {
        return getAllChoiceQuestionsMapper(SingleChoiceQuestion.class);
    }
    /**
     * 获取指定id值的单选题
     * @param id 题目序号
     * @return 指定id值的单选题
     */
    public static ChoiceQuestion getSingleChoiceQuestionById(String id) {
        return getAllSingleChoiceQuestionsMapper().get(id);
    }

    /**
     * 获取所有多选题，并以Map的形式返回，key为id
     * @return 包含所有多选题的map集合
     */
    public static Map<String, ChoiceQuestion> getAllMultipleChoiceQuestionsMapper() {
        return getAllChoiceQuestionsMapper(MultipleChoiceQuestion.class);
    }
    /**
     * 获取指定id值的单选题
     * @param id 题目序号
     * @return 指定id值的单选题
     */
    public static ChoiceQuestion getMultipleChoiceQuestionById(String id) {
        return getAllSingleChoiceQuestionsMapper().get(id);
    }

    private static Map<String, ChoiceQuestion> getAllChoiceQuestionsMapper(Class<? extends ChoiceQuestion> questionClass) {
        Elements questionElements = getQuestionElements(questionClass.getSimpleName());
        Map<String, ChoiceQuestion> res = new TreeMap<>();
        for (Element questionElement : questionElements) { //获取并封装数据
            ChoiceQuestion question = null;
            try {
                question = questionClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            // 封装题目id
            question.setId(questionElement.attr("id"));
            // 封装题目描述description字段
            question.setDescription(questionElement.getElementsByTag("description").get(0).text());
            // 封装答案集合answer字段
            Elements answerElements = questionElement.getElementsByTag("answer");
            int size = answerElements.size();
            String[] answers = new String[size];
            for (int i = 0; i < size; i++) {
                answers[i] = answerElements.get(i).text();
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
    private static Map<String, Question> getAllNormalQuestionsMapper(Class<? extends Question> questionClass) {
        Elements questionElements = getQuestionElements(questionClass.getSimpleName());
        Map<String, Question> res = new TreeMap<>();
        for (Element questionElement : questionElements) { //获取并封装数据
            Question question = null;
            try {
                question = questionClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
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

    public static Map<String, Question> getAllTrueOrFalseQuestionsMapper() {
        return getAllNormalQuestionsMapper(TrueOrFalseQuestion.class);
    }
    public static Question getTrueOrFalseQuestionById(String id) {
        return getAllTrueOrFalseQuestionsMapper().get(id);
    }
    public static Map<String, Question> getAllFillInBlanksQuestionsMapper() {
        return getAllNormalQuestionsMapper(FillInBlanksQuestion.class);
    }
    public static Question getFillInBlanksQuestionById(String id) {
        return getAllFillInBlanksQuestionsMapper().get(id);
    }
    /**
     * 根据指定xml文件名，返回文件中所有question元素对象的集合
     * @param xmlName 指定的xml文件名
     * @return 文件中所有question元素对象的集合
     */
    private static Elements getQuestionElements(String xmlName) {
        //2.1获取xml文档的path
        String path = srcQuestions + "\\" + getSetTitle() + "\\" + xmlName + ".xml";
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
