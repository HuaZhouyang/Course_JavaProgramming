package beans.question.normalQuestion;

/**
 * 判断题类
 */
public class TrueOrFalseQuestion extends NormalQuestion {

    @Override
    public String QuestionType() {
        return "判断题";
    }

    @Override
    public void printQuestion(int serialNum) {
        super.printQuestion(serialNum);
        System.out.println("（输入T/true为真, F/false为假。不区分大小写）");
    }

    @Override
    public boolean checkAnswer(String AnswersToBeChecked) {
        if (AnswersToBeChecked.toLowerCase().matches("t|true")) {
            return super.checkAnswer("true");
        }
        if (AnswersToBeChecked.toLowerCase().matches("f|false")) {
            return super.checkAnswer("false");
        }
        return super.checkAnswer(AnswersToBeChecked);
    }
}
