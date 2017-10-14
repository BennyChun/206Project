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


    private int upperBound;//this records the upper bound, e.g , easy will be 20 , hard will be 99

    private int lowerBound;//this records the lower bound, DEFAULT 1

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
    public EquationGenerator(String selectedLevel, String selectedOperation){
        this.selectedLevel=selectedLevel;
        this.selectedOperation = selectedOperation;

        if (selectedLevel.equals("easy")){
            upperBound = 20;
        }else if (selectedLevel.equals("hard")){
            upperBound = 99;
        }else{
            System.out.println("you have passed an invalid argument to the constructor, please only pass easy or hard");
        }
        lowerBound = 1;
    }



    //=======================================================

    /**
     * this method lets you set the lower bound for the random equation generation
     * @param upperBound
     */
    public void setUpperBound(int upperBound){
        this.upperBound = upperBound;
    }

    /**
     * this method lets you set the upper bound for the random equation generation
     * @param lowerBound
     */
    public void setLowerBound(int lowerBound){
        this.lowerBound = lowerBound;
    }

    //=======================================================





    public void generateEquation(){
        generateEasy();
    }


    //generations of easy numbers
    private void generateEasy() {
        Random rand = new Random();

        //generate 2 random numbers between lower bound and upper bound
        int firstEasyNum = rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;
        int secondEasyNum = rand.nextInt((upperBound - lowerBound) + 1) + lowerBound;

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
            if (answer >= lowerBound && answer <= upperBound){//checks if the generated euqation>answer is within the easy range
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
