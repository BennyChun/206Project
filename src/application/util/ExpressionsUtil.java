package application.util;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;


/**
 * this class will provide the utilities to do mathematical expressions
 * like taking in an expression as input and output the answer as a number
 */
public class ExpressionsUtil {


    /**
     * this method takes a String, which represents an math expression
     * this method will try and return an int
     * will return -999.0 if there were exceptions
     * @param expression
     * @return
     */
    public int ExpressionToNum(String expression){

        try {
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            Object output = engine.eval(expression);


            return (int) output; // need to make sure the inout argument make be evaluated into an integer
        }catch (Exception se){
            //se.printStackTrace();
        }


        return -999;
    }


}
