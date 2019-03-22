package name.katlog.refactor._06orgnizemethod;

/**
 * Created by fw on 2018/4/19
 */
public class ReplaceTempWithQuery {

    private int _quantity;
    private int _itemPrice;

    class BeforeRefactor {
        double getPrice() {
            int basePrice = _quantity * _itemPrice;
            double discountFactor;
            if (basePrice > 1000) discountFactor = 0.95;
            else discountFactor = 0.98;
            return basePrice * discountFactor;

        }
    }

    class Refactor1 {
        double getPrice() {
            final int basePrice = _quantity * _itemPrice;
            final double discountFactor;
            if (basePrice > 1000) discountFactor = 0.95;
            else discountFactor = 0.98;
            return basePrice * discountFactor;
        }
    }

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
