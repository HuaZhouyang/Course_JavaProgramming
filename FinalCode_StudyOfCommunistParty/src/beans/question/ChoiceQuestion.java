package beans.question;

public abstract class ChoiceQuestion extends Question{
    private String A, B, C, D;

    public ChoiceQuestion() {
    }
    public ChoiceQuestion(String id, String description, String a, String b, String c, String d, String... answer) {
        super(id, description, answer);
        A = a;
        B = b;
        C = c;
        D = d;
    }

    public String getA() {
        return A;
    }
    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }
    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }
    public void setC(String c) {
        C = c;
    }

    public String getD() {
        return D;
    }
    public void setD(String d) {
        D = d;
    }

    @Override
    public void printQuestion(int serialNum) {
        super.printQuestion(serialNum);
        System.out.println("\tA." + getA());
        System.out.println("\tB." + getB());
        System.out.println("\tC." + getC());
        System.out.println("\tD." + getD());
    }
}
