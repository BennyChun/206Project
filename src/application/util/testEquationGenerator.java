package application.util;


/**
 * ALL WORK AND NO PLAY MAKES JACK A DULL BOY
 * ALL WORK AN D NOT PLAY MAKES JCUAK A DULL BOY
 * ALL WORK NADN NO OPLAY MAKES JACK AD DULL BOY
 * ALL WORK AND NO PLAY MAKES JAK A DULL BOY
 * ALL WORK AND NO PLAY MAKES JACK A DULL BOY
 * ALL WORK AND NO PLAY MAKES JACK A DULL BOY
 * ALL WORN KAND MAOJE JACK A DULL BOY
 * ALL WORK AND NO LPLAY
 * @author Jack L. Mao
 */
public class testEquationGenerator {

    /**
     * Begins the test for creating a test equation.
     *
     * @param args
     */
    public static void main(String[] args){

        EquationGenerator test = new EquationGenerator("easy" , "/");
        test.generateEquation();




        System.out.print(test.getTheEquation() + " = ");
        System.out.println (test.getTheAnswer());
    }





}
