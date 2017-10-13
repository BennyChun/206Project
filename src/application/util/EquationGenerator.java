package application.util;

import java.util.Random;

/**
 * this calss will generate the equations for the question
 * easy Level: answer questions up to 20
 * hard Level: answer questions up to 99
 */
public class EquationGenerator {

    private String selectedLevel;
    private String selectedOperation;
    private int theAnswer; // the answer to the passed in expression
    private  String theEquation;//the equation that got generated

    /**
     * this constructor takes an argument which represents the level the user has selected
     * and it takes a selected operation (plus , minus , multiply , divide)
     *
     * plus = +
     * minus = -
     * multiply = *
     * divide = /
     *
     * @param selectedLevel selectedOperation
     */
    EquationGenerator(String selectedLevel, String selectedOperation){
        this.selectedLevel=selectedLevel;
        this.selectedOperation = selectedOperation;
    }


    public void generateEquation(){
        if (selectedLevel.equals("easy")){
            generateEasy();
        }else if (selectedLevel.equals("hard")){
            generateHard();
        }else{
            //you shouldn't go into here
            //you passed in a wrong argument for the constructor
            //you fking retarded developer, check your types
            System.out.println("you should only pass either easy or hard into the EquationGenerator constructor");
        }
    }


    //generations of easy numbers
    private void generateEasy() {
        Random rand = new Random();

        //generate 2 random numbers between 1 - 20
        int firstEasyNum = rand.nextInt((20 - 1) + 1) + 1;
        int secondEasyNum = rand.nextInt((20 - 1) + 1) + 1;

        String tempEasyEquation = firstEasyNum + selectedOperation + secondEasyNum;

        if (selectedOperation.equals("/")){//if the selectedOperation was a divide.
            if (firstEasyNum % secondEasyNum != 0 ){//if the first num % second num NOT EQUAL to zero
                //this means that it was not divisible, and will need to regenerate new numbers

                generateEasy();//recursively call this function again if the selected operator was divide.
                return;//exit this method after the recursive call

            }else{//the two numbers generated is compatible, is divisible by each other

                ExpressionsUtil equa = new ExpressionsUtil();
                int answer = equa.ExpressionToNum(tempEasyEquation);
                theAnswer = answer;
                theEquation = tempEasyEquation;

                return;//this return is used to exit this methods,
            }

        }

        //if the selected Operation is not a divide /
        ExpressionsUtil equa = new ExpressionsUtil();
        int answer = equa.ExpressionToNum(tempEasyEquation);

        if (answer != -999){//if there was no exceptions
            if (answer > 0 && answer <= 20){//checks if the generated euqation>answer is within the easy range
                theAnswer = answer;
                theEquation = tempEasyEquation;
                return;

            }else{//the equation is out of scope, try again
                generateEasy();
                return;
            }
        }else{
            System.out.println("invalid math expression < this is displayed in EquationGenerator") ;
            return; // do i want this return ?????
        }

    }





    //might refactor this
    //and use the generateEasy method
    //they are pretty much the same
    //generation of hard numbers
    private void generateHard() {
        Random rand = new Random();

        int firstHardNum = rand.nextInt( (99 - 1) + 1) + 1;
        int secondHardNum = rand.nextInt( (99 - 1) + 1) + 1;

        String tempHardEquation = firstHardNum + selectedOperation + secondHardNum;
    }

    //==============================================================
    //returns the answer to the equation that got generated
    //you must pass valid inputs to the constructor for this class  e.g("easy" , "+")
    //you must call generateEquation() method first before calling this
    public int getTheAnswer(){
        return theAnswer;
    }

    //returns the equation that got generated
    //you must pass valid inputs to the constructor for this class e.g("easy" , "+")
    //you must call generateEquation() method first before calling this
    public String getTheEquation(){
        return theEquation;
    }


}
