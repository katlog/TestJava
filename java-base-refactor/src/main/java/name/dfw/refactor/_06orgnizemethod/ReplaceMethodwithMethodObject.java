package name.dfw.refactor._06orgnizemethod;

/**
 * Created by fw on 2018/4/24
 */
public class ReplaceMethodwithMethodObject {

    class BeforeRefactor {

        class Account {

            int gamma(int inputVal, int quantity, int yearToDate) {
                int importantValue1 = (inputVal * quantity) + delta();
                int importantValue2 = (inputVal * yearToDate) + 100;
                if ((yearToDate - importantValue1) > 100) importantValue2 -= 20;
                int importantValue3 = importantValue2 * 7;
                // and so on.
                return importantValue3 - 2 * importantValue1;
            }

            private int delta() {
                return 0;
            }
        }
    }

    class Refactor {

        class Account {

            int gamma(int inputVal, int quantity, int yearToDate) {
                return new Gamma(this, inputVal, quantity, yearToDate).compute();
            }

            private int delta() {
                return 0;
            }
        }

        class Gamma {
            private final Account _account;
            private int inputVal;
            private int quantity;
            private int yearToDate;
            private int importantValue1;
            private int importantValue2;
            private int importantValue3;

            Gamma(Account source, int inputValArg, int quantityArg, int yearToDateArg) {
                _account = source;
                inputVal = inputValArg;
                quantity = quantityArg;
                yearToDate = yearToDateArg;
            }

            int compute() {
                importantValue1 = (inputVal * quantity) + _account.delta();
                importantValue2 = (inputVal * yearToDate) + 100;
                if ((yearToDate - importantValue1) > 100) importantValue2 -= 20;
                int importantValue3 = importantValue2 * 7;
                // and so on.
                return importantValue3 - 2 * importantValue1;
            }
        }
    }

    class Refactor1 {

        class Account {

            int gamma(int inputVal, int quantity, int yearToDate) {
                return new Gamma(this, inputVal, quantity, yearToDate).compute();
            }

            private int delta() {
                return 0;
            }
        }

        class Gamma {
            private final Account _account;
            private int inputVal;
            private int quantity;
            private int yearToDate;
            private int importantValue1;
            private int importantValue2;
            private int importantValue3;

            Gamma(Account source, int inputValArg, int quantityArg, int yearToDateArg) {
                _account = source;
                inputVal = inputValArg;
                quantity = quantityArg;
                yearToDate = yearToDateArg;
            }

            int compute() {
                importantValue1 = (inputVal * quantity) + _account.delta();
                importantValue2 = (inputVal * yearToDate) + 100;
                importantThing();
                int importantValue3 = importantValue2 * 7;
                // and so on.
                return importantValue3 - 2 * importantValue1;
            }

            private void importantThing() {
                if ((yearToDate - importantValue1) > 100) importantValue2 -= 20;
            }
        }
    }
}
