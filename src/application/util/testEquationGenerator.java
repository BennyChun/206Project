package application.util;



public class testEquationGenerator {

    public static void main(String[] args){

        EquationGenerator test = new EquationGenerator("easy" , "/");
        test.generateEquation();




        System.out.print(test.getTheEquation() + " = ");
        System.out.println (test.getTheAnswer());
    }





}
