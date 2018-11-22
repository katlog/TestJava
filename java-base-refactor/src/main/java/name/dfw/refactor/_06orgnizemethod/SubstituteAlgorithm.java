package name.dfw.refactor._06orgnizemethod;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fw on 2018/4/19
 */
public class SubstituteAlgorithm {
    class Demo {
        String foundPerson(String[] people) {
            for (int i = 0; i < people.length; i++) {
                if (people[i].equals("Don")) {
                    return "Don";
                }
                if (people[i].equals("John")) {
                    return "John";
                }
                if (people[i].equals("Kent")) {
                    return "Kent";
                }
            }
            return "";
        }
    }

    class DemoRefactor {
        String foundPerson(String[] people) {
            List candidates = Arrays.asList(new String[]{"Don", "John", "Kent"});
            for (int i = 0; i < people.length; i++)
                if (candidates.contains(people[i]))
                    return people[i];
            return "";
        }
    }
}
