package name.dfw.refactor._06orgnizemethod;

/**
 * Created by dell on 2018/4/24
 * 将临时变量内联化
 */
public class InlineTemp {

    class Order {
        private int basePrice;

        public int basePrice() {
            return this.basePrice;
        }
    }


    class Before {
        public boolean isOverPrice(Order anOrder) {
            double basePrice = anOrder.basePrice();
            return (basePrice > 1000);
        }
    }

    class Refactor {
        public boolean isOverPrice(Order anOrder) {
            return ((double) anOrder.basePrice() > 1000);
        }
    }
}
