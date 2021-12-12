package beans.question;

import java.util.Arrays;

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
        String[] answersRaw = AnswersToBeChecked.split(" ");
        String[] answers = new String[answersRaw.length];
        for (int i = 0; i < answersRaw.length; i++) {
            answers[i] = answersRaw[i].toUpperCase();
        }
        return Arrays.equals(getAnswer(), answers);
    }

    @Override
    public void printQuestion(int serialNum) {
        super.printQuestion(serialNum);
        System.out.println("（多个选项间用空格(space)隔开，选项不区分大小写）");
    }
}
