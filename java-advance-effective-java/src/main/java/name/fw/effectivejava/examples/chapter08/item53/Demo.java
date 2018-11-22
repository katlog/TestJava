package name.fw.effectivejava.examples.chapter08.item53;

import java.util.Arrays;
import java.util.Set;

/**
 * @moudle: ExtractMethod
 * @version:v1.0
 * @Description: TODO
 * @author: chensl
 * @date: 2018年2月7日 下午9:54:23
 *
 */
public class Demo {

    // Reflective instantiation with interface acess
    public static void main(String[] args) {
        // Translate the class name into a Class object
        Class<?> cl = null;
        try {
            cl = Class.forName(args[0]);
        } catch (ClassNotFoundException e) {
            System.err.println("class not found");
            System.exit(1);
        }
        // Instantiate the class
        Set<String> s = null;
        try {
            s = (Set<String>) cl.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            System.err.println("class not found");
            System.exit(1);
        }
        //Exercise the set
        s.addAll(Arrays.asList(args).subList(1, args.length));
        System.out.println(s);
    }
}
