package beans.question.choiceQuestion;

import java.util.Set;
import java.util.TreeSet;

/**
 * 多选题类
 */
public class MultipleChoiceQuestion extends ChoiceQuestion {

    @Override
    public String QuestionType() {
        return "多选题";
    }

    @Override
    public boolean checkAnswer(String AnswersToBeChecked) {
        String[] answersRaw = AnswersToBeChecked.split("\\s");
        Set<String> answers = new TreeSet<>();
        for (String s : answersRaw) {
            if (!s.equals(""))
                answers.add(s.toUpperCase());
        }
        return getAnswerSet().equals(answers);
    }

    @Override
    public void printQuestion(int serialNum) {
        super.printQuestion(serialNum);
        System.out.println("（多个选项间用空格(space)隔开，选项不限顺序、不区分大小写）");
    }
}
