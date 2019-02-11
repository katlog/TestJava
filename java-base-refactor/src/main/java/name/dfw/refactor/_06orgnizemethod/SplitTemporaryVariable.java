package name.dfw.refactor._06orgnizemethod;

/**
 * Created by fw on 2018/4/24
 */
public class SplitTemporaryVariable {

    /**  当有某个临时变量被赋值超过一次， 它既不是循环变量，也不是一个集用临时变量（collecting temporary variable） 。
     *   针对每次赋值， 创造一个独立的、 对应的临时变量。 */
    class DEMO {
        private double _height;
        private double _width;

        public void before() {
            double temp = 2 * (_height + _width);
            System.out.println(temp);
            temp = _height * _width;
            System.out.println(temp);
        }

        public void refactor() {
            final double perimeter = 2 * (_height + _width);
            System.out.println(perimeter);
            final double area = _height * _width;
            System.out.println(area);
        }
    }


    class BeforeRefactor {
        private double _primaryForce;
        private double _mass;
        private int _delay;
        private double _secondaryForce;

        double getDistanceTravelled(int time) {
            double result;
            double acc = _primaryForce / _mass; //译注： 第一次赋值处
            int primaryTime = Math.min(time, _delay);
            result = 0.5 * acc * primaryTime * primaryTime;
            int secondaryTime = time - _delay;
            if (secondaryTime > 0) {
                double primaryVel = acc * _delay; //以下是第二次赋值处
                acc = (_primaryForce + _secondaryForce) / _mass;
                result += primaryVel * secondaryTime + 0.5 * acc * secondaryTime * secondaryTime;
            }
            return result;
        }
    }

    class Refactor1 {
        private double _primaryForce;
        private double _mass;
        private int _delay;
        private double _secondaryForce;

        double getDistanceTravelled(int time) {
            double result;
            final double primaryAcc = _primaryForce / _mass;
            int primaryTime = Math.min(time, _delay);
            result = 0.5 * primaryAcc * primaryTime * primaryTime;
            int secondaryTime = time - _delay;
            if (secondaryTime > 0) {
                double primaryVel = primaryAcc * _delay;
                double acc = (_primaryForce + _secondaryForce) / _mass;
                result += primaryVel * secondaryTime + 0.5 * acc * secondaryTime * secondaryTime;
            }
            return result;
        }
    }

    class Refactor2 {
        private double _primaryForce;
        private double _mass;
        private int _delay;
        private double _secondaryForce;

        double getDistanceTravelled(int time) {
            double result;
            final double primaryAcc = _primaryForce / _mass;
            int primaryTime = Math.min(time, _delay);
            result = 0.5 * primaryAcc * primaryTime * primaryTime;
            int secondaryTime = time - _delay;
            if (secondaryTime > 0) {
                double primaryVel = primaryAcc * _delay;
                final double secondaryAcc = (_primaryForce + _secondaryForce) / _mass;
                result += primaryVel * secondaryTime + 0.5 * secondaryAcc * secondaryTime * secondaryTime;
            }
            return result;
        }

    }

}

