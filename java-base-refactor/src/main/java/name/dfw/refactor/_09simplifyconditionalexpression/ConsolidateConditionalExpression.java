package name.dfw.refactor._09simplifyconditionalexpression;

/**
 * Created by fw on 2018/4/20
 * 合并条件式
 */
public class ConsolidateConditionalExpression {

    class Demoo {
        private int _seniority;
        private int _monthsDisabled;
        private boolean _isPartTime;

        double disabilityAmount() {
            if (_seniority < 2) return 0;
            if (_monthsDisabled > 12) return 0;
            if (_isPartTime) return 0;
            // compute the disability amount
            return 0;
        }
    }

    class DemoRefactor {
        private int _seniority;
        private int _monthsDisabled;
        private boolean _isPartTime;

        double disabilityAmount() {
            if ((_seniority < 2) || (_monthsDisabled > 12) || (_isPartTime)) return 0;
            // compute the disability amount
            return 0;
        }
    }
    class DemoRefactor1 {
        private int _seniority;
        private int _monthsDisabled;
        private boolean _isPartTime;
        double disabilityAmount() {
            if (isNotEligibleForDisability()) return 0;
            // compute the disability amount
            return 0;
        }
		boolean isNotEligibleForDisability() {
            return ((_seniority < 2) || (_monthsDisabled > 12) || (_isPartTime));
        }
    }
}
