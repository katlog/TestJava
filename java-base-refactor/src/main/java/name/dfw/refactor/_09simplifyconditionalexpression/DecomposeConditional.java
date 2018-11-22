package name.dfw.refactor._09simplifyconditionalexpression;

import java.util.Date;

/**
 * Created by fw on 2018/4/20
 * 分解条件式
 */
public class DecomposeConditional {
    private Date date;
    private Date SUMMER_START;
    private Date SUMMER_END;

    class Demo{
        private double charge;
        private double quantity;
        private double _winterRate;
        private double _winterServiceCharge;
        private double _summerRate;

        public void demo() {
            if (date.before (SUMMER_START) || date.after(SUMMER_END))
                charge = quantity * _winterRate + _winterServiceCharge;
            else
                charge = quantity * _summerRate;
        }
    }

    class DemoRefactor{
        private double charge;
        private double quantity;
        private double _winterRate;
        private double _winterServiceCharge;
        private double _summerRate;

        public void demo() {
            if (notSummer(date))
                charge = winterCharge(quantity);
            else
                charge = summerCharge (quantity);
        }

        private double summerCharge(double quantity) {
            return quantity * _summerRate;
        }

        private double winterCharge(double quantity) {
            return quantity * _winterRate + _winterServiceCharge;
        }

        private boolean notSummer(Date date) {
            return date.before (SUMMER_START) || date.after(SUMMER_END);
        }


    }
}
