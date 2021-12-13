package beans.question.choiceQuestion;

/**
 * 单选题类
 */
public class SingleChoiceQuestion extends ChoiceQuestion {

    @Override
    public String QuestionType() {
        return "单选题";
    }

    @Override
    public void printQuestion(int serialNum) {
        super.printQuestion(serialNum);
        System.out.println("（选项不区分大小写）");
    }
}
