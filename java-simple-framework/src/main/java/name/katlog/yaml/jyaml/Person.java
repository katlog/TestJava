package name.katlog.yaml.jyaml;

import lombok.Data;

import java.util.List;

@Data
public class Person {
    private String name;
    private int age;
    private String Sex;
    private List<Person> children;
}
