package name.katlog.refactor._09simplifyconditionalexpression;

/**
 * Created by fw on 2018/4/20
 * 以卫语句取代嵌套条件式
 */
public class ReplaceNestedConditionalwithGuardClauses {
    class Demo {
        private boolean _isDead;
        private boolean _isSeparated;
        private boolean _isRetired;

        double getPayAmount() {
            double result;
            if (_isDead)
                result = deadAmount();
            else {
                if (_isSeparated)
                    result = separatedAmount();
                else {
                    if (_isRetired)
                        result = retiredAmount();
                    else
                        result = normalPayAmount();
                }
            }
            return result;
        }

        private double normalPayAmount() {
            return 0;
        }

        private double retiredAmount() {
            return 0;
        }

        private double separatedAmount() {
            return 0;
        }

        private double deadAmount() {
            return 0;
        }
    }

    class DemoRefactor {
        private boolean _isDead;
        private boolean _isSeparated;
        private boolean _isRetired;

        double getPayAmount() {
            if (_isDead) return deadAmount();
            if (_isSeparated) return separatedAmount();
            if (_isRetired) return retiredAmount();
            return normalPayAmount();
        }

        private double normalPayAmount() {
            return 0;
        }

        private double retiredAmount() {
            return 0;
        }

        private double separatedAmount() {
            return 0;
        }

        private double deadAmount() {
            return 0;
        }
    }
}
