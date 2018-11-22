package name.dfw.refactor._07movecharacter;

/**
 * Created by fw on 2018/4/24
 */
public class MoveField {

    class BeforeRefactor {
        class Account {
            private AccountType _type;
            private double _interestRate;

            double interestForAmount_days(double amount, int days) {
                return _interestRate * amount * days / 365;
            }
        }

        class AccountType {

        }
    }

    class Refactor {

        class Account {
            private AccountType _type;
            private double _interestRate;

            double interestForAmount_days(double amount, int days) {
                return _type.getInterestRate() * amount * days / 365;
            }
        }

        class AccountType {
            private double _interestRate;

            void setInterestRate(double arg) {
                _interestRate = arg;
            }

            double getInterestRate() {
                return _interestRate;
            }
        }
    }

    class Refactor1 {

        class Account {
            private AccountType _type;
            private double _interestRate;

            double interestForAmount_days(double amount, int days) {
                return getInterestRate() * amount * days / 365;
            }

            double interestForAmountAndDays(double amount, int days) {
                return getInterestRate() * amount * days / 365;
            }

            private void setInterestRate(double arg) {
                _interestRate = arg;
            }

            private double getInterestRate() {
                return _interestRate;
            }
        }

        class AccountType {
            private double _interestRate;

            void setInterestRate(double arg) {
                _interestRate = arg;
            }

            double getInterestRate() {
                return _interestRate;
            }
        }
    }

    class Refactor2 {

        class Account {
            private AccountType _type;
            private double _interestRate;

            double interestForAmount_days(double amount, int days) {
                return getInterestRate() * amount * days / 365;
            }

            double interestForAmountAndDays(double amount, int days) {
                return getInterestRate() * amount * days / 365;
            }

            private void setInterestRate(double arg) {
                _type.setInterestRate(arg);
            }

            private double getInterestRate() {
                return _type.getInterestRate();
            }
        }

        class AccountType {
            private double _interestRate;

            void setInterestRate(double arg) {
                _interestRate = arg;
            }

            double getInterestRate() {
                return _interestRate;
            }
        }
    }

}
