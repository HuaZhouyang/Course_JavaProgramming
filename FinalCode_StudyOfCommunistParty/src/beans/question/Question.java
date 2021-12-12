package beans.question;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class Question {
    protected String id, description;
    protected List<String> answer = new LinkedList<>();

    protected Question() {
    }
    public Question(String id, String description, String... answer) {
        this.id = id;
        this.description = description;
        this.answer.addAll(List.of(answer));
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

    /**
     * @return 所有答案组成的数组
     */
    public String[] getAnswer() {
        return answer.toArray(new String[0]);
    }
    public void setAnswer(String... answer) {
        this.answer.clear();
        this.answer.addAll(List.of(answer));
    }

    /**
     * 检查输入的答案是否正确
     * @param AnswersToBeChecked 待检验答案
     * @return 代表答案是否正确的布尔值
     */
    public boolean checkAnswer(String AnswersToBeChecked) {
        return getAnswer()[0].equals(AnswersToBeChecked.toUpperCase());
    }

    public void printQuestion(int serialNum) {
        System.out.println(serialNum + "." + getDescription());
    }

    public void printAnswer() {
        System.out.println("----------答案：" + Arrays.toString(getAnswer()) + "----------");
    }

    public abstract String QuestionType();
}
