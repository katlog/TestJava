package dfw.yaml.snake;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;

public class TestSnakeYml {
    /**内部类不行*/
//    @Data
//    @NoArgsConstructor
//    class Me {
//        private Integer age;
//        private String name;
//        private Map<String, Object> params;
//        private List<String> favoriteBooks;
//    }

    @Test
    public void testParseMeYaml() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        Me me = yaml.loadAs(this.getClass().getResourceAsStream("me.yaml"), Me.class);
        System.out.println(me);
    }



}
