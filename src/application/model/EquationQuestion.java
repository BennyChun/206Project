package application.model;


import application.util.MaoriAnswerUtil;
import application.util.REFACTOREDEquationGenerator;

import javafx.beans.property.*;

/**
 * This class will represent a model for a Question (pretty similar to the Question class)
 * but this question will have an equation instead of just an number
 *
 * everytime instantiate this class
 * this class will make an equation ans tore that equation as a field
 * this calss will make the equation as instantiation base on the argaument that was pass in
 *
 *
 */
public class EquationQuestion {

    private IntegerProperty theAnswer;  //records the answer
    private StringProperty theAnswerInMaori; //records the answer in maori :String
    private StringProperty theEquation; //records the equation
    private IntegerProperty currentAttempts; //records the number of attemps
    private BooleanProperty correct;    //records whether this question got answered correct


    /**
     * a constructor that takes in arguments instead of generating them
     * and sets them
     */
    public EquationQuestion(int theAnswer , String theAnswerInMaori , String theEquation , int currentAttempts , boolean correct){
        this.theAnswer = new SimpleIntegerProperty(theAnswer);
        this.theAnswerInMaori = new SimpleStringProperty(theAnswerInMaori);
        this.theEquation = new SimpleStringProperty(theEquation);
        this.currentAttempts = new SimpleIntegerProperty(currentAttempts);
        this.correct = new SimpleBooleanProperty(correct);
    }



    /**
     * this constructor takes 2 inputs, which represents the users selectedLevel
     * and the users selected Operation (+ , - , * , / )
     *
     * and will generate random equation base on those selections
     * the generated equation will be stored as a field
     *
     * @param selectedLevel
     * @param selectedOperation
     */

    public EquationQuestion(String selectedLevel , String selectedOperation , int upperBound){
        //generate a random equation base on the users input
        //save the randomly generated equation as a field
        //save the sanswer as a field
        //translate the sanswer to maori

        REFACTOREDEquationGenerator generate = new REFACTOREDEquationGenerator(selectedLevel, selectedOperation , upperBound);
        generate.generateEquation();

        theEquation = new SimpleStringProperty(generate.getTheEquation() );
        theAnswer = new SimpleIntegerProperty(generate.getTheAnswer());

        currentAttempts =new  SimpleIntegerProperty(1);
        correct = new SimpleBooleanProperty(false);


        MaoriAnswerUtil mao = new MaoriAnswerUtil();//use the maorianswer util
        mao.numberToMaori(getTheAnswer());//pass the int, and convert to a maori word
        String maomao = mao.getMaoriWords();//get the maori word
        theAnswerInMaori = new SimpleStringProperty(maomao);

    }

    //======================================setters and getter====================================

    public int getTheAnswer(){
        return theAnswer.get();
    }
    public IntegerProperty theAnswerProperty() {
        return theAnswer;
    }

    //=============================================
    public String getTheEquation() {
        return theEquation.get();
    }

    public StringProperty theEquationProperty() {
        return theEquation;
    }

    //=======================================================
    public int getCurrentAttempts() {
        return currentAttempts.get();
    }

    public IntegerProperty currentAttemptsProperty() {
        return currentAttempts;
    }

    public void setCurrentAttempts() {
        int tempAttempt = getCurrentAttempts() + 1;//increment
        currentAttempts.set(tempAttempt);

    }

    //============================================================
    public boolean isCorrect() {
        return correct.get();
    }

    public BooleanProperty correctProperty() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct.set(correct);
    }
    //=============================================================
    public String getTheAnswerInMaori() {
        return theAnswerInMaori.get();
    }

    public StringProperty theAnswerInMaoriProperty() {
        return theAnswerInMaori;
    }


}
