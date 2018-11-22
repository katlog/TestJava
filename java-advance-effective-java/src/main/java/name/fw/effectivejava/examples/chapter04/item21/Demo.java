package name.fw.effectivejava.examples.chapter04.item21;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @moudle: ExtractMethod
 * @version:v1.0
 * @Description: TODO
 * @author: chensl
 * @date: 2018年1月28日 下午8:01:25
 *
 */
public class Demo {

    public static void main(String[] args) {
        String[] stringArray = new String[]{};
        Arrays.sort(stringArray,new Comparator<String>() {
            public int compare(String s1,String s2){
                return s1.length()-s2.length();
            }
            
        });
    }
}
class StringLengthCoparator{
    public int compare(String s1,String s2){
        return s1.length()-s2.length();
    }
}
class StringLengthCoparator1{
    
    private StringLengthCoparator1(){}
    public static final StringLengthCoparator1 INSTANCE = new StringLengthCoparator1();
    public int compare(String s1,String s2){
        return s1.length()-s2.length();
    }
}

// Strategy interface
//interface Comparator<T>{
//    public int compare(T t1, T t2) ;
//}
class StringLengthCoparator2 implements Comparator<String>{
    private StringLengthCoparator2(){}
    public static final StringLengthCoparator2 INSTANCE = new StringLengthCoparator2();
    @Override
    public int compare(String s1, String s2) {
        return s1.length()-s2.length();
    }
}

//Exporting a concrete strategy
class Host{
 private static class StrLenCmpp implements Comparator<String> ,Serializable{
     @Override
     public int compare(String s1, String s2) {
         return s1.length()-s2.length();
     }
 }
 public static final Comparator<String> STRING_LENGHT_COMPARATOR = new StrLenCmpp();
 
 
}
