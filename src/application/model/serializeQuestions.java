package application.model;


/**
 * this class will help wrap, or adapt the EquationQuestion class
 * by having the same fields but instead it will be ints and strings
 * and it will have getters and setters for those
 *
 * this class will be much easier to serialize than EquationQuestions
 * will i ever need this class tho ?
 */
public class serializeQuestions {

    private int theAnswer;  //records the answer
    private String theAnswerInMaori; //records the answer in maori :String
    private String theEquation; //records the equation
    private int currentAttempts; //records the number of attemps
    private boolean correct;    //records whether this question got answered correct

    public int getTheAnswer() {
        return theAnswer;
    }

    public void setTheAnswer(int theAnswer) {
        this.theAnswer = theAnswer;
    }
    //--------------------------------------------------------------------------------------
    public String getTheAnswerInMaori() {
        return theAnswerInMaori;
    }

    public void setTheAnswerInMaori(String theAnswerInMaori) {
        this.theAnswerInMaori = theAnswerInMaori;
    }
    //--------------------------------------------------------------------------------------
    public String getTheEquation() {
        return theEquation;
    }

    public void setTheEquation(String theEquation) {
        this.theEquation = theEquation;
    }
    //---------------------------------------------------------------------------------------
    public int getCurrentAttempts() {
        return currentAttempts;
    }

    public void setCurrentAttempts(int currentAttempts) {
        this.currentAttempts = currentAttempts;
    }

    //--------------------------------------------------------------------------------
    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
    //--------------------------------------------------------------------------------------

}
