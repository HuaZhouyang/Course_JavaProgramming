package beans.question;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Question {
    protected String id, description;
    protected Set<String> answer = new TreeSet<>();

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

    public String[] getAnswer() {
        return answer.toArray(new String[0]);
    }
    public void setAnswer(String... answer) {
        this.answer.clear();
        this.answer.addAll(List.of(answer));
    }

    /**
     * 检查输入的答案是否正确
     * @param answer 答案
     * @return 代表答案是否正确的布尔值
     */
    public boolean checkAnswer(String... answer) {
        return Arrays.equals(this.getAnswer(), answer);
    }

    public void printQuestion(int serialNum) {
        System.out.println(serialNum + "." + getDescription());
    }
    public Question encapsulateByXmlElement() {

    }
}
