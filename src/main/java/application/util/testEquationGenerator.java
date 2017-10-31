package application.util;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        String ieee = dtf.format(localDate);
        System.out.println(ieee);


        REFACTOREDEquationGenerator test = new REFACTOREDEquationGenerator("easy", "*" , 00);




        for (int i = 0 ; i < 10 ; i++) {


            test.generateEquation();


            System.out.print(test.getTheEquation() + " = ");
            System.out.println(test.getTheAnswer());
        }
    }





}
