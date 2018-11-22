package name.dfw.refactor._06orgnizemethod;

import java.util.Date;

/**
 * Created by fw on 2018/4/24
 */
public class RemoveAssignments2Parameters {

    class BeforeRefactor {
        int discount(int inputVal, int quantity, int yearToDate) {
            if (inputVal > 50) inputVal -= 2;
            if (quantity > 100) inputVal -= 1;
            if (yearToDate > 10000) inputVal -= 4;
            return inputVal;
        }
    }

    class Refactor4TempRepalceParametersAssignments {
        int discount(int inputVal, int quantity, int yearToDate) {
            int result = inputVal;
            if (inputVal > 50) result -= 2;
            if (quantity > 100) result -= 1;
            if (yearToDate > 10000) result -= 4;
            return result;
        }
    }

    class RefactorAddFinalRestrict {
        int discount(final int inputVal, final int quantity, final int yearToDate) {
            int result = inputVal;
            if (inputVal > 50) result -= 2;
            if (quantity > 100) result -= 1;
            if (yearToDate > 10000) result -= 4;
            return result;
        }
    }

    // ----java的值传递 pass by value
    static class Param {
        public static void main(String[] args) {
            int x = 5;
            triple(x);
            System.out.println("x after triple: " + x);
        }

        private static void triple(int arg) {
            arg = arg * 3;
            System.out.println("arg in triple: " + arg);
        }
    }

    static class Param1 {
        public static void main(String[] args) {
            Date d1 = new Date("1 Apr 98");
            nextDateUpdate(d1);
            System.out.println("d1 after nextDay: " + d1);

            Date d2 = new Date("1 Apr 98");
            nextDateReplace(d2);
            System.out.println("d2 after nextDay: " + d2);
        }

        private static void nextDateUpdate(Date arg) {
            arg.setDate(arg.getDate() + 1);
            System.out.println("arg in nextDay: " + arg);
        }

        private static void nextDateReplace(Date arg) {
            arg = new Date(arg.getYear(), arg.getMonth(), arg.getDate() + 1);
            System.out.println("arg in nextDay: " + arg);
        }
    }


}