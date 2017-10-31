package application.model;


/**
 * this class is used as an adapter
 * it has the same fields as the EquationQuestion class
 * but instead of properties, it only has String and int
 * this class is much easier to serialize than the aforementioned EquationQuestion class
 */
public class EquationQuestionAdapter {
    private int theAnswer;  //records the answer
    private String theAnswerInMaori; //records the answer in maori :String
    private String theEquation; //records the equation
    private int currentAttempts; //records the number of attemps
    private boolean correct;    //records whether this question got answered correct

    /**
     * this constructor takes an EquationQuestion class, and converts it to this class
     */
    public EquationQuestionAdapter(EquationQuestion equationQuestion ){
        theAnswer = equationQuestion.getTheAnswer();
        theAnswerInMaori = equationQuestion.getTheAnswerInMaori();
        theEquation = equationQuestion.getTheEquation();
        currentAttempts = equationQuestion.getCurrentAttempts();
        correct = equationQuestion.isCorrect();
    }
    //===================================================================================
    //                                 getters and setter
    //===================================================================================
    public int getTheAnswer() {
        return theAnswer;
    }

    public String getTheAnswerInMaori() {
        return theAnswerInMaori;
    }

    public String getTheEquation() {
        return theEquation;
    }

    public int getCurrentAttempts() {
        return currentAttempts;
    }

    public boolean isCorrect() {
        return correct;
    }
}
