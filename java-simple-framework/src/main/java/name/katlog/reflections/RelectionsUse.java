package name.katlog.reflections;

import org.quartz.Job;
import org.reflections.Reflections;

import java.util.Set;

public class RelectionsUse {
    public static void main(String[] args) {
        Reflections reflections = new Reflections();

        Set<Class<? extends Job>> subTypesOf = reflections.getSubTypesOf(Job.class);

        subTypesOf.stream()
                .forEach(System.out::println);
    }
}
