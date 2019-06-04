
package org.person.dfw.program;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.junit.Test;

/**
 * @moudle: TestTryCatch
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年4月10日 上午9:38:41
 *
 *
 *情况1：try{} catch(){}finally{} return;
            显然程序按顺序执行。
    情况2:try{ return; }catch(){} finally{} return;
              程序执行try块中return之前（包括return语句中的表达式运算）代码；
             再执行finally块，最后执行try中return;
             finally块之后的语句return，因为程序在try中已经return所以不再执行。
    情况3:try{ } catch(){return;} finally{} return;
             程序先执行try，如果遇到异常执行catch块，
             有异常：则执行catch中return之前（包括return语句中的表达式运算）代码，再执行finally语句中全部代码，
                         最后执行catch块中return. finally之后也就是4处的代码不再执行。
             无异常：执行完try再finally再return.
    情况4:try{ return; }catch(){} finally{return;}
              程序执行try块中return之前（包括return语句中的表达式运算）代码；
              再执行finally块，因为finally块中有return所以提前退出。
    情况5:try{} catch(){return;}finally{return;}
              程序执行catch块中return之前（包括return语句中的表达式运算）代码；
              再执行finally块，因为finally块中有return所以提前退出。
    情况6:try{ return;}catch(){return;} finally{return;}
              程序执行try块中return之前（包括return语句中的表达式运算）代码；
              有异常：执行catch块中return之前（包括return语句中的表达式运算）代码；
                           则再执行finally块，因为finally块中有return所以提前退出。
              无异常：则再执行finally块，因为finally块中有return所以提前退出。
 */
public class TestTryCatch {
    /**
     * 只有finally中的return 会影响返回的顺序
     */ 
    @Test
    public void test() {
        System.out.println("-------------tryCahtch1----------------");
        System.out.println(tryCahtch1());
        System.out.println("------------tryCahtch2-----------------");
        System.out.println(tryCahtch2());
        System.out.println("-----------tryCahtch3------------------");
        System.out.println(tryCahtch3());
        System.out.println("-----------tryCahtch4------------------");
        System.out.println(tryCahtch4());
        System.out.println("----------tryCahtch5-------------------");
        System.out.println(tryCahtch5());
        System.out.println("---------tryCahtch6--------------------");
        System.out.println(tryCahtch6());
        System.out.println("------------------------------");
    }

    private String tryCahtch1() {
        String result = "" ;
        try {
            System.out.println("execute try");
            result+=" try ";
        } catch (Exception e) {
            System.out.println("execute catch");
            result+=" catch ";
        } finally {
            System.out.println("execute finally");
            result+=" finally ";
        }
        System.out.println("execute out");
        result+=" out ";
        return result+"reOut";
    }

    private String tryCahtch2() {
        String result = "" ;
        try {
            System.out.println("execute try");
            result+=" try ";
            return result+" reTry";
        } catch (Exception e) {
            System.out.println("execute catch");
            result+=" catch ";
        } finally {
            System.out.println("execute finally");
            result+=" finally ";
        }
        System.out.println("execute out");
        result+=" out ";
        return result+"reOut";
    }
    private String tryCahtch3() {
        String result = "" ;
        try {
            System.out.println("execute try");
            result+=" try ";
            if (result.length()>1) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("execute catch");
            result+=" catch ";
            return result+" reCatch";
        } finally {
            System.out.println("execute finally");
            result+=" finally ";
        }
        System.out.println("execute out");
        result+=" out ";
        return result+"reOut";
    }
    @SuppressWarnings("finally")
    private String tryCahtch4() {
        String result = "" ;
        try {
            System.out.println("execute try");
            result+=" try ";
            return result+" reTry";
        } catch (Exception e) {
            System.out.println("execute catch");
            result+=" catch ";
        } finally {
            System.out.println("execute finally");
            result+=" finally ";
            return result+" reFinally";
        }
    }
    @SuppressWarnings("finally")
    private String tryCahtch5() {
        String result = "" ;
        try {
            System.out.println("execute try");
            result+=" try ";
            if (result.length()>1) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("execute catch");
            result+=" catch ";
            return result+" reCatch";
        } finally {
            System.out.println("execute finally");
            result+=" finally ";
            return result+" reFinally";
        }
    }
    @SuppressWarnings("finally")
    private String tryCahtch6() {
        String result = "" ;
        try {
            System.out.println("execute try");
            result+=" try ";
            if (result.length()>1) {
                throw new Exception();
            }
            return result+" reTry";
        } catch (Exception e) {
            System.out.println("execute catch");
            result+=" catch ";
            return result+" reCatch";
        } finally {
            System.out.println("execute finally");
            result+=" finally ";
            return result+" reFinally";
        }
    }
    
    
    /** 7 try-catch-resource */
    @Test public void _test1(){
    	 testNormalOutput("");  // 一般的关闭资源的方式
         testAutoCloseWithTryCatch("");  // try-catch-resource关闭资源的方式
    }
  
    private static void testNormalOutput(String filepath){  
        OutputStream global_out = null;  
        BufferedWriter writer ;  
        try {  
            OutputStream out = out = new FileOutputStream(filepath);  
            global_out = out;  
            out.write((filepath + "inside try catch block").getBytes());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        try{  
            if(global_out!=null){  
                global_out.write("  \t\r outside try catch block".getBytes());  
                global_out.close();  
            }  
        } catch (Exception e){  
            e.printStackTrace();  
        }  
    }  
  
    private static void testAutoCloseWithTryCatch(String filepath){  
        OutputStream global_out = null;  
        
        //try()的括号中可以写多行声明,每个声明的变量类型都必须是Closeable的子类
        try(OutputStream out = new FileOutputStream(filepath);) {  
            global_out = out;  
            out.write((filepath+"inside try catch block").getBytes());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        try{  
            if(global_out!=null){  
                global_out.write("  \t\r outside try catch block".getBytes());  
                global_out.close();  
            }  
        } catch (Exception e){  
            e.printStackTrace();  
        }  
    }  
    

}
