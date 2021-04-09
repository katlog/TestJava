package name.katlog.collection.stream;

import org.junit.Test;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * Created by fw on 2021/3/31
 */
public class TestIntStream {

    public static final IntConsumer PRINTLN = System.out::println;


    @Test
    public void o(){
        IntStream.generate(() -> 1).limit(10).forEach(PRINTLN);
    }

    @Test
    public void of(){
        IntStream.of(1,2,3,4).forEach(PRINTLN);
    }

    @Test
    public void closed(){
        IntStream.rangeClosed(1,10).forEach(PRINTLN);
    }

    @Test
    public void iterate(){
        IntStream stream = IntStream.iterate(1, operand -> operand * 2);
        stream.limit(10).forEach(PRINTLN);
    }
}
