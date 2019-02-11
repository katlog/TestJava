package name.dfw.refactor._06orgnizemethod;

/**
 * Created by fw on 2018/4/19
 * 将函数内联化
 */
public class InlineMethod {

    class Before {
        private int _numberOfLateDeliveries;

        int getRating() {
            return (moreThanFiveLateDeliveries()) ? 2 : 1;
        }
        boolean moreThanFiveLateDeliveries() {
            return _numberOfLateDeliveries > 5;
        }
    }

    class Refactor {
        private int _numberOfLateDeliveries;

        int getRating() {
            return (_numberOfLateDeliveries > 5) ? 2 : 1;
        }
    }


}

