package beans.question.normalQuestion;

import beans.question.Question;

public abstract class NormalQuestion extends Question {
    public NormalQuestion() {
    }

    public NormalQuestion(int setNum, String id, String description, String... answer) {
        super(setNum, id, description, answer);
    }
}
