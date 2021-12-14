package beans.question;

import java.util.*;

public abstract class Question {
    int setNum;
    protected String id, description, analysis;
    protected List<String> answer = new LinkedList<>();

    protected Question() {
    }
    public Question(int setNum, String id, String description, String... answer) {
        this.setNum = setNum;
        this.id = id;
        this.description = description;
        this.answer.addAll(List.of(answer));
    }

    public int getSetNum() {
        return setNum;
    }
    public void setSetNum(int setNum) {
        this.setNum = setNum;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnalysis() {
        return analysis;
    }
    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    /**
     * @return 所有答案组成的数组
     */
    public String[] getAnswers() {
        return answer.toArray(new String[0]);
    }
    public Set<String> getAnswerSet() {
        return new TreeSet<>(answer);
    }
    public void setAnswers(String... answer) {
        this.answer.clear();
        this.answer.addAll(List.of(answer));
    }

    /**
     * 检查输入的答案是否正确
     * @param AnswersToBeChecked 待检验答案
     * @return 代表答案是否正确的布尔值
     */
    public boolean checkAnswer(String AnswersToBeChecked) {
        return getAnswers()[0].equalsIgnoreCase(AnswersToBeChecked);
    }

    public void printQuestion(int serialNum) {
        System.out.println(serialNum + "." + getDescription());
    }

    public void printAnswer() {
        System.out.println("----------答案：" + Arrays.toString(getAnswers()) + "----------");
        if (getAnalysis() != null)
            System.out.println("----------解析：" + getAnalysis() + "----------");

    }

    public abstract String QuestionType();
}
