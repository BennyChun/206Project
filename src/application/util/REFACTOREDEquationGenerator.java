package application.util;

import java.util.Random;

/**
 * this calss will generate the equations for the question
 * easy Level: answer questions up to 20
 * hard Level: answer questions up to 99
 */
public class REFACTOREDEquationGenerator {

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
    public REFACTOREDEquationGenerator(String selectedLevel, String selectedOperation){
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



    /**
     * must call this method to generate the equation
     */
    public void generateEquation() {
        Random rand = new Random();

        //generate 2 random numbers between lower bound and upper bound
        int firstNum = rand.nextInt((upperBound - 1) + 1) + 1;
        int secondNum = rand.nextInt((upperBound - 1) + 1) + 1;

        String tempEquation = firstNum + selectedOperation + secondNum;

        if (selectedOperation.equals("/")){//if the selectedOperation was a divide

            //while the modulus of them is not eqal to zero
            //generate the two random numbers again
            while (firstNum % secondNum !=0){
                firstNum = rand.nextInt((upperBound - 1) + 1) + 1;//regenerate the random nums
                secondNum = rand.nextInt((upperBound - 1) + 1) + 1;//regenerate the random nums
                tempEquation = firstNum + " ÷ " + secondNum;

            }

            ExpressionsUtil equa = new ExpressionsUtil();
            theAnswer = equa.ExpressionToNum(tempEquation);
            theEquation = tempEquation;
            return;

        }

        //if the selected Operation is not a divide /
        //do this stuff
        ExpressionsUtil equa = new ExpressionsUtil();
        int answer = equa.ExpressionToNum(tempEquation);


        if (answer != -999){//if there was no exceptions
            while (answer < lowerBound || answer > upperBound) {//checks if the generated euqation>answer is within the easy range
                firstNum = rand.nextInt((upperBound - 1) + 1) + 1;//regenerate the random nums
                secondNum = rand.nextInt((upperBound - 1) + 1) + 1;//regenerate the random nums
                tempEquation = firstNum + " " + selectedOperation + " " + secondNum;
                answer = equa.ExpressionToNum(tempEquation);

            }

            theAnswer = answer;
            if(selectedOperation.equals("*")){
                tempEquation = firstNum + " x " + secondNum;
            }
            theEquation = tempEquation;
            return;

        }else{
            System.out.println("invalid math expression < this is displayed in OLDEquationGenerator") ;
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
