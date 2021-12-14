package beans.question.normalQuestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 填空题类
 */
public class FillInBlanksQuestion extends NormalQuestion {
    @Override
    public String QuestionType() {
        return "填空题";
    }

    @Override
    public boolean checkAnswer(String AnswersToBeChecked) {
        String[] answersRaw = AnswersToBeChecked.split("\\s");
        List<String> answers = new ArrayList<>();
        for (String s : answersRaw) {
            if (!s.equals(""))
                answers.add(s.toUpperCase());
        }
        return Arrays.equals(getAnswers(), answers.toArray(new String[0]));
    }

    @Override
    public void printQuestion(int serialNum) {
        super.printQuestion(serialNum);
        System.out.println("（多个答案间用空格(space)隔开，答案间有顺序要求）");
    }
}
