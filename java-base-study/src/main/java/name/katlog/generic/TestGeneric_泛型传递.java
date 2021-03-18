package name.katlog.generic;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by fw on 2020/1/15
 */
public class TestGeneric_泛型传递 {


    @Builder
    static class Container<IN,OUT>{
        private IN[] ins;
        private IN one;
        public OUT[] outs(Function<IN,OUT> function) {
            List<OUT> outs = new ArrayList<>();
            for (int i = 0; i < ins.length; i++) {
                outs.add(function.apply(ins[i]));
            }
            return (OUT[]) outs.toArray();
        }

        public  OUT accept(IN in){
            return null;
        }
    }

    // static interface Warap<IN,OUT>{
    //     <OUT,EOUT> Warap<OUT,EOUT> accepty(Function<EOUT,OUT> function);
    // }
    //
    // public static void main(String[] args) {
    //     Container<String,Integer> container ;
    //
    //     Warap<Integer,String> warap = new Warap<Integer, String>() {
    //         @Override
    //         public <OUT, EOUT> Warap<OUT, EOUT> accepty(Function<EOUT, OUT> function) {
    //             return null;
    //         }
    //     };
    //     Warap<Long, Object> accepty = warap.accepty(o -> String.valueOf("")).accepty(o -> Long.valueOf(1));
    //
    // }
}
