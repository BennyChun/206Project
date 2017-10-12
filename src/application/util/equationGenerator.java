package application.util;

import java.util.Random;

/**
 * this calss will generate the equations for the question
 * easy Level: answer questions up to 20
 * hard Level: answer questions up to 99
 */
public class equationGenerator {

    private String selectedLevel;
    private String selectedOperation;


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
    equationGenerator(String selectedLevel, String selectedOperation){
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
            System.out.println("you should only pass either easy or hard into the equationGenerator constructor");
        }
    }


    //generations of easy numbers
    private void generateEasy() {
        Random rand = new Random();
        //nextInt is normally exclusive of the top value,
        //so add one to make it inclusive

        //generate 2 random numbers between 1 - 20
        int firstEasyNum = rand.nextInt((20 - 1) + 1) + 1;
        int secondEasyNum = rand.nextInt((20 - 1) + 1) + 1;

        String tempEasyEquation = firstEasyNum + selectedOperation + secondEasyNum;




    }





    //generation of hard numbers
    private void generateHard() {
        Random rand = new Random();

        int firstHardNum = rand.nextInt( (99 - 1) + 1) + 1;
        int secondHardNum = rand.nextInt( (99 - 1) + 1) + 1;

        String tempHardEquation = firstHardNum + selectedOperation + secondHardNum;
    }


}
