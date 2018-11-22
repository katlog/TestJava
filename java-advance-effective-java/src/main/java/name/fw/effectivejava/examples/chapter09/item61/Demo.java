package name.fw.effectivejava.examples.chapter09.item61;

/**
 * @moudle: ExtractMethod
 * @version:v1.0
 * @Description: TODO
 * @author: chensl
 * @date: 2018年2月8日 下午8:30:10
 *
 */
public class Demo {
    
    
    public void test1() throws HigherLeverlException {
        // Exception Translation
        try {
            // Use lower-level abstraction to do our bidding
            doSomething();
        } catch (LowerLevelException e) {
            throw new HigherLeverlException();
        }
    }
    
    private void doSomething() throws LowerLevelException {
        throw new LowerLevelException();
    }

    public void test2() throws HigherLeverlException {
        // Exception Chaining
        try {
            // Use lower-level abstraction to do our bidding
            doSomething();
        } catch (LowerLevelException cause) {
            throw new HigherLeverlException(cause);
        }
    }
}

class LowerLevelException extends Exception{}

// Exception with chaining-aware constructor
class HigherLeverlException extends Exception{
    public HigherLeverlException( ) {
    }
    public HigherLeverlException(LowerLevelException cause) {
        super(cause);
    }
}
