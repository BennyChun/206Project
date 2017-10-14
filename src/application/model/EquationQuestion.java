package application.model;


import application.util.EquationGenerator;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
    private StringProperty theEquation; //records the equation
    private IntegerProperty numAttemps; //records the number of attemps





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
    public void EquationQuestion(String selectedLevel , String selectedOperation){
        //generate a random equation base on the users input
        //save the randomly generated equation as a field
        //save the sanswer as a field
        //translate the sanswer to maori

        EquationGenerator generate = new EquationGenerator(selectedLevel, selectedOperation);
        generate.generateEquation();

        theEquation = new SimpleStringProperty(generate.getTheEquation());
        theAnswer = new SimpleIntegerProperty(generate.getTheAnswer());




    }



}
