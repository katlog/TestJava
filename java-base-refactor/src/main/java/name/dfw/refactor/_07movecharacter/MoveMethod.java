package name.dfw.refactor._07movecharacter;

/**
 * Created by fw on 2018/4/24
 * 函数迁移
 */
public class MoveMethod {

    class BeforeRefactor {
        class Account {
            double overdraftCharge() { //译注： 透支金计费， 它和其他class的关系似乎比较密切。
                if (_type.isPremium()) {
                    double result = 10;
                    if (_daysOverdrawn > 7)
                        result += (_daysOverdrawn - 7) * 0.85;
                    return result;
                } else {
                    return _daysOverdrawn * 1.75;
                }
            }

            double bankCharge() {
                double result = 4.5;
                if (_daysOverdrawn > 0) result += overdraftCharge();
                return result;
            }

            private AccountType _type;
            private int _daysOverdrawn;

        }

        class AccountType {
            public boolean isPremium() {
                return false;
            }
        }
    }

    class Refactor {
        class Account {
            double overdraftCharge() { //译注： 透支金计费， 它和其他class的关系似乎比较密切。
                return _type.overdraftCharge(_daysOverdrawn);
            }

            double bankCharge() {
                double result = 4.5;
                if (_daysOverdrawn > 0) result += _type.overdraftCharge(_daysOverdrawn);
                return result;
            }

            private AccountType _type;
            private int _daysOverdrawn;

        }

        class AccountType {
            public boolean isPremium() {
                return false;
            }

            double overdraftCharge(int daysOverdrawn) {
                if (isPremium()) {
                    double result = 10;
                    if (daysOverdrawn > 7) result += (daysOverdrawn - 7) * 0.85;
                    return result;
                } else {
                    return daysOverdrawn * 1.75;
                }

            }
        }
    }

    class Refactor1 {
        class Account {
            private int daysOverdrawn;

            double overdraftCharge() { //译注： 透支金计费， 它和其他class的关系似乎比较密切。
                return _type.overdraftCharge(this);
            }

            double bankCharge() {
                double result = 4.5;
                if (_daysOverdrawn > 0) result += _type.overdraftCharge(this);
                return result;
            }

            private AccountType _type;
            private int _daysOverdrawn;

            public int getDaysOverdrawn() {
                return daysOverdrawn;
            }
        }

        class AccountType {
            public boolean isPremium() {
                return false;
            }

            double overdraftCharge(Account account) {
                if (isPremium()) {
                    double result = 10;
                    if (account.getDaysOverdrawn() > 7) result += (account.getDaysOverdrawn() - 7) * 0.85;
                    return result;
                } else return account.getDaysOverdrawn() * 1.75;
            }

        }
    }
}