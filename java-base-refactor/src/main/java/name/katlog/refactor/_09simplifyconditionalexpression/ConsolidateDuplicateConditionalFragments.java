package name.katlog.refactor._09simplifyconditionalexpression;

/**
 * Created by fw on 2018/4/20
 * 合并重复的条件片段
 */
public class ConsolidateDuplicateConditionalFragments {

    class Demo {
        private double total;
        private double price;

        public void demo() {
            if (isSpecialDeal()) {
                total = price * 0.95;
                send();
            } else {
                total = price * 0.98;
                send();
            }
        }

        private boolean isSpecialDeal() {
            return false;
        }

        private void send() {
            // compute
        }
    }

    class DemoRefactor {
        private double total;
        private double price;

        public void demo() {
            if (isSpecialDeal())
                total = price * 0.95;
            else
                total = price * 0.98;
            send();
        }

        private boolean isSpecialDeal() {
            return false;
        }

        private void send() {
            // compute
        }
    }
}
