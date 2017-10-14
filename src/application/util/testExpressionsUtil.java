package application.util;

/**
 * this class is solely used for testing the ExpressionsUtil class
 * by passing in mutiply test cases
 */
public class testExpressionsUtil {
    public static void main(String[] args) {

        ExpressionsUtil equa = new ExpressionsUtil();
        int answer = equa.ExpressionToNum("4 / 5");
        System.out.println(answer);
    }
}
