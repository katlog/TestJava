package name.katlog.refactor._06orgnizemethod;

/**
 * Created by fw on 2018/4/19
 * 以查询取代临时变量
 */
public class ReplaceTempWithQuery {

    private int _quantity;
    private int _itemPrice;

    /**  替换掉两个临时变量：basePrice和discountFactor */
    class BeforeRefactor {
        double getPrice() {
            int basePrice = _quantity * _itemPrice;
            double discountFactor;
            if (basePrice > 1000) discountFactor = 0.95;
            else discountFactor = 0.98;
            return basePrice * discountFactor;

        }
    }

    /**  先把临时变量声明为final， 检查他们是否的确只被赋值一次 */
    class Refactor1 {
        double getPrice() {
            final int basePrice = _quantity * _itemPrice;
            final double discountFactor;
            if (basePrice > 1000) discountFactor = 0.95;
            else discountFactor = 0.98;
            return basePrice * discountFactor;
        }
    }

    /**  首先把赋值（assignment） 动作的右侧表达式提炼出来 */
    class Refactor2 {
        double getPrice() {
            final int basePrice = basePrice();
            final double discountFactor;
            if (basePrice > 1000) discountFactor = 0.95;
            else discountFactor = 0.98;
            return basePrice * discountFactor;
        }

        private int basePrice() {
            return _quantity * _itemPrice;
        }
    }

    /**  然后开始用Replace Temp with Query。 先把临时变量basePrice的第一个引用点替换掉 */
    class Refactor3 {
        double getPrice() {
            final int basePrice = basePrice();
            final double discountFactor;
            if (basePrice() > 1000) discountFactor = 0.95;
            else discountFactor = 0.98;
            return basePrice * discountFactor;
        }

        private int basePrice() {
            return _quantity * _itemPrice;
        }
    }

    /**  由于「下一个」 已经是basePrice的最后一个引用点，所以把basePrice临时变量的声明式一并摘除 */
    class Refactor4 {
        double getPrice() {
            final double discountFactor;
            if (basePrice() > 1000) discountFactor = 0.95;
            else discountFactor = 0.98;
            return basePrice() * discountFactor;
        }

        private int basePrice() {
            return _quantity * _itemPrice;
        }
    }

    /**  搞定basePrice之后，再以类似办法提炼出一个discountFactor()： */
    class Refactor5DiscountFactor1 {

        double getPrice() {
            final double discountFactor = discountFactor();
            return basePrice() * discountFactor;
        }

        private double discountFactor() {
            if (basePrice() > 1000) return 0.95;
            else return 0.98;
        }

        private int basePrice() {
            return _quantity * _itemPrice;
        }
    }

    class Refactor5DiscountFactor2 {

        double getPrice() {
            return basePrice() * discountFactor();
        }
        private double discountFactor() {
            if (basePrice() > 1000) return 0.95;
            else return 0.98;
        }
        private int basePrice() {
            return _quantity * _itemPrice;
        }
    }
}
