package name.katlog.refactor._10simplifymethodcall;

/**
 * Created by fw on 2018/4/20
 * 令函数携带参数
 */
public class ParameterizeMethod {
    class Demo {
        protected Dollars baseCharge() {
            double result = Math.min(lastUsage(), 100) * 0.03;
            if (lastUsage() > 100) {
                result += (Math.min(lastUsage(), 200) - 100) * 0.05;
            }
            if (lastUsage() > 200) {
                result += (lastUsage() - 200) * 0.07;
            }
            return new Dollars(result);
        }
    }

    class DemoRefactor {
        protected Dollars baseCharge() {
            double result = usageInRange(0, 100) * 0.03;
            result += usageInRange(100, 200) * 0.05;
            result += usageInRange(200, Integer.MAX_VALUE) * 0.07;
            return new Dollars(result);
        }

        protected int usageInRange(int start, int end) {
            if (lastUsage() > start) return Math.min(lastUsage(), end) - start;
            else return 0;
        }
    }

    private int lastUsage() {
        return 0;
    }

    private class Dollars {
        public Dollars(double result) {
        }
    }
}
