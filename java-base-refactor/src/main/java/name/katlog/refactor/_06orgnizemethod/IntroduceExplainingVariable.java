package name.katlog.refactor._06orgnizemethod;

import java.util.Random;

/**
 * Created by fw on 2018/4/19
 * 引入解释性变量
 *  不太常用，作者一般用extract method来解释，而非解释性变量
 */
public class IntroduceExplainingVariable {

    class Demo{
        String platform;
        String browser;
        int resize;


        private boolean wasInitialized() {
            return new Random().nextBoolean();
        }
        public void beforeRefactor(){
            if ( (platform.toUpperCase().indexOf("MAC") > -1)
                    &&(browser.toUpperCase().indexOf("IE") > -1)
                    &&wasInitialized() && resize > 0 ){
                // do something
            }
        }

        public void refactor(){
            final boolean isMacOs = platform.toUpperCase().indexOf("MAC") > -1;
            final boolean isIEBrowser = browser.toUpperCase().indexOf("IE") > -1;
            final boolean wasResized = resize > 0;
            if (isMacOs && isIEBrowser && wasInitialized() && wasResized) {
                // do something
            }
        }

    }


    private int _quantity;
    private int _itemPrice;

    class BeforeRefactor {
        double price() {
            // price is base price - quantity discount + shipping
            return _quantity * _itemPrice - Math.max(0, _quantity - 500) * _itemPrice * 0.05 + Math.min(_quantity * _itemPrice * 0.1, 100.0);
        }
    }

    class Refactor1 {
        double price() {
            // price is base price - quantity discount + shipping
            final double basePrice = _quantity * _itemPrice;
            return basePrice - Math.max(0, _quantity - 500) * _itemPrice * 0.05 + Math.min(_quantity * _itemPrice * 0.1, 100.0);
        }
    }

    class Refactor2 {
        double price() {
            // price is base price - quantity discount + shipping
            final double basePrice = _quantity * _itemPrice;
            return basePrice - Math.max(0, _quantity - 500) * _itemPrice * 0.05 + Math.min(_quantity * _itemPrice * 0.1, 100.0);
        }
    }

    class Refactor3 {
        double price() {
            // price is base price - quantity discount + shipping
            final double basePrice = _quantity * _itemPrice;
            return basePrice - Math.max(0, _quantity - 500) * _itemPrice * 0.05 + Math.min(basePrice * 0.1, 100.0);
        }
    }

    class Refactor4 {
        double price() {
            // price is base price - quantity discount + shipping
            final double basePrice = _quantity * _itemPrice;
            final double quantityDiscount = Math.max(0, _quantity - 500) * _itemPrice * 0.05;
            return basePrice - quantityDiscount + Math.min(basePrice * 0.1, 100.0);
        }
    }

    class Refactor5 {
        double price() {
            final double basePrice = _quantity * _itemPrice;
            final double quantityDiscount = Math.max(0, _quantity - 500) * _itemPrice * 0.05;
            final double shipping = Math.min(basePrice * 0.1, 100.0);
            return basePrice - quantityDiscount + shipping;
        }
    }

    class Refactor6 {
        double price() {
            // price is base price - quantity discount + shipping
            return basePrice() - Math.max(0, _quantity - 500) * _itemPrice * 0.05 + Math.min(basePrice() * 0.1, 100.0);
        }

        private double basePrice() {
            return _quantity * _itemPrice;
        }
    }

    class Refactor7 {
        double price() {
            return basePrice() - quantityDiscount() + shipping();
        }

        private double quantityDiscount() {
            return Math.max(0, _quantity - 500) * _itemPrice * 0.05;
        }

        private double shipping() {
            return Math.min(basePrice() * 0.1, 100.0);
        }

        private double basePrice() {
            return _quantity * _itemPrice;
        }
    }
}
