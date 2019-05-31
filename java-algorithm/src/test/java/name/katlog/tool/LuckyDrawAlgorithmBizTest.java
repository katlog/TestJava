package name.katlog.tool;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class LuckyDrawAlgorithmBizTest {

    @Test
    public void obtainedByProbability() {


        // 概率米粒map 必中 没有谢谢参与了
        Map<Box<Integer>, Double> probabilities = new LinkedHashMap<>();
        // 谢谢参与
        probabilities.put(new Box<>(0), 0.0);
        probabilities.put((new Box<>(15)), 10.5);
        probabilities.put((new Box<>(new Random().nextInt(100) + 1)), 50.0);
        probabilities.put((new Box<>(30)), 7.0);
        probabilities.put((new Box<>(50)), 17.5);
        // 谢谢参与
        probabilities.put((new Box<>(0)), 0.0);
        probabilities.put((new Box<>(1)), 7.5);
        probabilities.put((new Box<>(10)), 7.5);

        System.out.println("probabilities = " + probabilities);
        for (int i = 0; i < 10; i++) {
            LuckyDrawAlgorithm.Tuple<Integer, Integer> integerBoxTuple = new LuckyDrawAlgorithm().obtainedByProbability(probabilities);
            System.out.println("result = " + integerBoxTuple);
        }
    }
}