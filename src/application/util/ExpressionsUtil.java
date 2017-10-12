package application.util;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * this class will provide the utilities to do mathmatical expressions
 * like taking in an expression as input and output the answer as a number
 */
public class ExpressionsUtil {




    public void ExpressionToNum(String expression)throws ScriptException{
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        Object output = engine.eval(expression);
        System.out.println(output);
    }


}
