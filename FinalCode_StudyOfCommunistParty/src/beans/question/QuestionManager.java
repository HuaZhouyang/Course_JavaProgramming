package beans.question;

import beans.question.choiceQuestion.ChoiceQuestion;
import beans.question.choiceQuestion.MultipleChoiceQuestion;
import beans.question.choiceQuestion.SingleChoiceQuestion;
import beans.question.normalQuestion.FillInBlanksQuestion;
import beans.question.normalQuestion.NormalQuestion;
import beans.question.normalQuestion.TrueOrFalseQuestion;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class QuestionManager {
    private static final Map<Class<? extends Question>, Map<String, ? extends Question>> questionMappers = new HashMap<>();
    private static final String srcQuestions = "FinalCode_StudyOfCommunistParty\\data\\questions";

    /**
     * 根据所选题集，返回题集目录名
     * @param setNum 所选题集数
     * @return 题集目录名
     */
    private static String getSetTitle(int setNum) {
        return "set-" + (setNum < 10 ? "0" + setNum : setNum);
    }

    /**
     * 获取总题集数
     * @return 总题集数
     */
    public static int getTotalQuestionSetNum() {
        return new File(srcQuestions).list().length;
    }

    /**
     * 根据输入题集数，判断这个题集是否存在
     * @param setNum 所选题集数
     * @return 代表这个题集是否存在的布尔值
     */
    public static boolean hasQuestionSet(int setNum) {
        return setNum <= getTotalQuestionSetNum();
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
     * 获取所选题集的所有题目（全部题型），并以List的形式返回
     * @param setNum 所选题集数
     * @return 包含所选题集的所有题目（全部题型）的List集合
     */
    public static List<Question> getAllQuestions(int setNum) {
        List<Question> res = new LinkedList<>();
        res.addAll(getAllSingleChoiceQuestionsMapper(setNum).values());
        res.addAll(getAllMultipleChoiceQuestionsMapper(setNum).values());
        res.addAll(getAllTrueOrFalseQuestionsMapper(setNum).values());
        res.addAll(getAllFillInBlanksQuestionsMapper(setNum).values());
        return res;
    }

    /**
     * 根据题集数、题目类型和id获取指定Question对象
     * @param setNum 指定题集数
     * @param questionClass 题目类型
     * @param id id值
     * @return 指定Question对象
     */
    public static Question getQuestionById(int setNum, Class<? extends Question> questionClass, String id) {
        if (ChoiceQuestion.class.isAssignableFrom(questionClass)) { // 传入的是选择题类
            return getAllChoiceQuestionsMapper(setNum, (Class<? extends ChoiceQuestion>) questionClass).get(id);
        } else if (NormalQuestion.class.isAssignableFrom(questionClass)) { // 传入的是标准题
            return getAllNormalQuestionsMapper(setNum, (Class<? extends NormalQuestion>) questionClass).get(id);
        }
        return null;
    }

    /**
     * 获取所选题集的所有单选题，并以Map的形式返回，key为id
     * @param setNum 所选题集数
     * @return 包含所选题集的所有单选题的map集合
     */
    public static Map<String, ChoiceQuestion> getAllSingleChoiceQuestionsMapper(int setNum) {
        return getAllChoiceQuestionsMapper(setNum, SingleChoiceQuestion.class);
    }
    /**
     * 获取所选题集的指定id值的单选题
     * @param setNum 所选题集数
     * @param id 题目id
     * @return 所选题集的指定id值的单选题
     */
    public static ChoiceQuestion getSingleChoiceQuestionById(int setNum, String id) {
        return getAllSingleChoiceQuestionsMapper(setNum).get(id);
    }

    /**
     * 获取所选题集的所有多选题，并以Map的形式返回，key为id
     * @param setNum 所选题集数
     * @return 包含所选题集的所有多选题的map集合
     */
    public static Map<String, ChoiceQuestion> getAllMultipleChoiceQuestionsMapper(int setNum) {
        return getAllChoiceQuestionsMapper(setNum, MultipleChoiceQuestion.class);
    }
    
    /**
     * 获取所选题集的指定id值的单选题
     * @param setNum 所选题集数
     * @param id 题目id
     * @return 所选题集的指定id值的单选题
     */
    public static ChoiceQuestion getMultipleChoiceQuestionById(int setNum, String id) {
        return getAllSingleChoiceQuestionsMapper(setNum).get(id);
    }

    /**
     * 获取所选题集的所有指定类型的选择题（单选or多选），并以Map的形式返回，key为id
     * @param setNum 所选题集数
     * @param questionClass 选择题类型
     * @return 包含所选题集的所有指定类型的选择题（单选or多选）的map集合
     */
    private static Map<String, ChoiceQuestion> getAllChoiceQuestionsMapper(int setNum, Class<? extends ChoiceQuestion> questionClass) {
        if (questionMappers.containsKey(questionClass)) { // 如果内存中已存在对应mapper
            return (Map<String, ChoiceQuestion>) questionMappers.get(questionClass);
        }
        // 内存中不存在，获取并存入集合
        Elements questionElements = getQuestionElements(setNum, questionClass.getSimpleName());
        Map<String, ChoiceQuestion> res = new TreeMap<>();
        for (Element questionElement : questionElements) { //获取并封装数据
            ChoiceQuestion question = null;
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
                answers[i] = answerElements.get(i).text();
            }
            question.setAnswers(answers);
            // 封装A,B,C,D选项字段
            question.setA(questionElement.getElementsByTag("A").get(0).text());
            question.setB(questionElement.getElementsByTag("B").get(0).text());
            question.setC(questionElement.getElementsByTag("C").get(0).text());
            question.setD(questionElement.getElementsByTag("D").get(0).text());
            // 将question添加入集合
            res.put(question.getId(), question);
        }
        questionMappers.put(questionClass, res);
        return res;
    }

    /**
     * 获取所选题集的所有判断题，并以Map的形式返回，key为id
     * @param setNum 所选题集数
     * @return 包含所选题集的所有判断题的map集合
     */
    public static Map<String, NormalQuestion> getAllTrueOrFalseQuestionsMapper(int setNum) {
        return getAllNormalQuestionsMapper(setNum, TrueOrFalseQuestion.class);
    }

    /**
     * 获取所选题集的指定id值的判断题
     * @param setNum 所选题集数
     * @param id 题目id
     * @return 所选题集的指定id值的判断题
     */
    public static Question getTrueOrFalseQuestionById(int setNum, String id) {
        return getAllTrueOrFalseQuestionsMapper(setNum).get(id);
    }

    /**
     * 获取所选题集的所有填空题，并以Map的形式返回，key为id
     * @param setNum 所选题集数
     * @return 包含所选题集的所有填空题的map集合
     */
    public static Map<String, NormalQuestion> getAllFillInBlanksQuestionsMapper(int setNum) {
        return getAllNormalQuestionsMapper(setNum, FillInBlanksQuestion.class);
    }
    /**
     * 获取所选题集的指定id值的填空题
     * @param setNum 所选题集数
     * @param id 题目id
     * @return 所选题集的指定id值的填空题
     */
    public static Question getFillInBlanksQuestionById(int setNum, String id) {
        return getAllFillInBlanksQuestionsMapper(setNum).get(id);
    }

    /**
     * 获取所选题集的所有指定类型的标准题（判断or填空），并以Map的形式返回，key为id
     * @param setNum 所选题集数
     * @param questionClass 标准题类型
     * @return 包含所选题集的所有指定类型的标准题（判断or填空）的map集合
     */
    private static Map<String, NormalQuestion> getAllNormalQuestionsMapper(int setNum, Class<? extends NormalQuestion> questionClass) {
        if (questionMappers.containsKey(questionClass)) { // 如果内存中已存在对应mapper
            return (Map<String, NormalQuestion>) questionMappers.get(questionClass);
        }
        // 内存中不存在，获取并存入集合
        Elements questionElements = getQuestionElements(setNum, questionClass.getSimpleName());
        Map<String, NormalQuestion> res = new TreeMap<>();
        for (Element questionElement : questionElements) { //获取并封装数据
            NormalQuestion question = null;
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
            // 将question添加入集合
            res.put(question.getId(), question);
        }
        return res;
    }

    /**
     * 根据指定xml文件名，返回文件中所有question元素对象的集合
     * @param xmlName 指定的xml文件名
     * @return 文件中所有question元素对象的集合
     */
    private static Elements getQuestionElements(int setNum, String xmlName) {
        //2.1获取xml文档的path
        String path = srcQuestions + "\\" + getSetTitle(setNum) + "\\" + xmlName + ".xml";
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
