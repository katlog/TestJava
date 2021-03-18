package name.katlog.yaml.jyaml;

import org.ho.yaml.Yaml;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class TestJYmal {


    //写入yaml配置文件
    @Test
    public  void write() {
        /* Initialize data. */
        Person father = new Person();
        father.setName("simon.zhang");
        father.setAge(23);
        father.setSex("man");
        List<Person> children=new ArrayList<Person>();
        for (int i = 8; i < 10; i++) {
            Person child = new Person();
            if (i % 2 == 0) {
                child.setSex("man");
                child.setName("mary" + (i - 7));
            } else {
                child.setSex("fatel");
                child.setName("simon" + (i - 7));
            }
            child.setAge(i);
            children.add(child);
        }
        father.setChildren(children);
        /* Export data to a YAML file. */
        File dumpFile = new File(System.getProperty("user.dir") + "/test/src/main/conf/testYaml.yaml");

        try {
            Yaml.dump(father, dumpFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //读取yaml配置文件
    @Test
    public  void  read() throws FileNotFoundException {
        Person father = (Person) Yaml.loadType(this.getClass().getResourceAsStream("testYaml.yaml"), Person.class);
        System.out.println(father);

    }
}
