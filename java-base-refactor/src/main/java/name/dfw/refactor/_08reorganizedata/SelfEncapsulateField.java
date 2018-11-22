package name.dfw.refactor._08reorganizedata;

/**
 * Created by fw on 2018/4/24
 * 自封装值域
 */
public class SelfEncapsulateField {

    class BeforeRefactor {
        class IntRange {
            private int _low, _high;

            boolean includes(int arg) {
                return arg >= _low && arg <= _high;
            }

            void grow(int factor) {
                _high = _high * factor;
            }

            IntRange(int low, int high) {
                _low = low;
                _high = high;
            }
        }
    }

    class Refactor {
        class IntRange {
            boolean includes(int arg) {
                return arg >= getLow() && arg <= getHigh();
            }

            void grow(int factor) {
                setHigh(getHigh() * factor);
            }

            private int _low, _high;

            int getLow() {
                return _low;
            }

            int getHigh() {
                return _high;
            }

            void setLow(int arg) {
                _low = arg;
            }

            void setHigh(int arg) {
                _high = arg;
            }
        }
    }


    class Refactor1 {
        class IntRange {

            IntRange(int low, int high) {
                initialize(low, high);
            }

            private void initialize(int low, int high) {
                _low = low;
                _high = high;
            }

            boolean includes(int arg) {
                return arg >= getLow() && arg <= getHigh();
            }

            void grow(int factor) {
                setHigh(getHigh() * factor);
            }

            private int _low, _high;

            int getLow() {
                return _low;
            }

            int getHigh() {
                return _high;
            }

            void setLow(int arg) {
                _low = arg;
            }

            void setHigh(int arg) {
                _high = arg;
            }
        }

        class CappedRange extends IntRange {
            CappedRange(int low, int high, int cap) {
                super(low, high);
                _cap = cap;
            }

            private int _cap;

            int getCap() {
                return _cap;
            }

            int getHigh() {
                return Math.min(super.getHigh(), getCap());
            }
        }
    }
}
