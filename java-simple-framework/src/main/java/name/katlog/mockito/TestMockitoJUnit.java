package name.katlog.mockito;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

/**
 * 与Junit集成，可以使用mockito的注解了
 * Created by fw on 2021/3/26
 */
@RunWith(MockitoJUnitRunner.class)
public class TestMockitoJUnit {

    interface CalculatorService {
        double add(double input1, double input2);
        double subtract(double input1, double input2);
        double multiply(double input1, double input2);
        double divide(double input1, double input2);
    }
    class Calculator implements CalculatorService {
        @Override
        public double add(double input1, double input2) {
            return input1 + input2;
        }
        @Override
        public double subtract(double input1, double input2) {
            throw new UnsupportedOperationException("Method not implemented yet!");
        }
        @Override
        public double multiply(double input1, double input2) {
            throw new UnsupportedOperationException("Method not implemented yet!");
        }
        @Override
        public double divide(double input1, double input2) {
            throw new UnsupportedOperationException("Method not implemented yet!");
        }
    }

    public class MathApplication {
        private CalculatorService calcService;
        public void setCalculatorService(CalculatorService calcService){
            this.calcService = calcService;
        }
        public double add(double input1, double input2){
            return calcService.add(input1, input2);
        }
        public double subtract(double input1, double input2){
            return calcService.subtract(input1, input2);
        }
        public double multiply(double input1, double input2){
            return calcService.multiply(input1, input2);
        }
        public double divide(double input1, double input2){
            return calcService.divide(input1, input2);
        }
    }

    /** 需要用 @InjectMocks private Foo foo = new Foo(); 这种形式 */
    //@InjectMocks annotation is used to create and inject the mock object
    @InjectMocks
    MathApplication mathApplication = new MathApplication();

    //@Mock annotation is used to create the mock object to be injected
    @Mock
    CalculatorService calcService;

    @Test
    public void testVerify(){

        //add the behavior of calc service to add two numbers 添加行为
        when(calcService.add(10.0,20.0)).thenReturn(30.00);

        //test the add functionality 验证功能
        Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);

        //验证是否使用相同的参数调用calcService.(默认1次)
        verify(calcService).add(10.0,20.0);
    }

    /** 对特定方法进行的调用次数进行特殊检查 */
    @Test
    public void testVerify1(){

        //add the behavior of calc service to add two numbers
        when(calcService.add(10.0,20.0)).thenReturn(30.00);

        //add the behavior of calc service to subtract two numbers
        when(calcService.subtract(20.0,10.0)).thenReturn(10.00);

        //test the add functionality
        Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);
        Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);
        Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);

        //test the subtract functionality
        Assert.assertEquals(mathApplication.subtract(20.0, 10.0),10.0,0.0);

        //default call count is 1
        verify(calcService).subtract(20.0, 10.0);

        //check if add function is called three times
        verify(calcService, times(3)).add(10.0, 20.0);

        //verify that method was never called on a mock
        verify(calcService, never()).multiply(10.0,20.0);
    }

    @Test
    public void testVerify2(){
        //add the behavior of calc service to add two numbers
        when(calcService.add(10.0,20.0)).thenReturn(30.00);

        //add the behavior of calc service to subtract two numbers
        when(calcService.subtract(20.0,10.0)).thenReturn(10.00);

        //test the add functionality
        Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);
        Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);
        Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);

        //test the subtract functionality
        Assert.assertEquals(mathApplication.subtract(20.0, 10.0),10.0,0.0);

        //check a minimum 1 call count
        verify(calcService, atLeastOnce()).subtract(20.0, 10.0);

        //check if add function is called minimum 2 times
        verify(calcService, atLeast(2)).add(10.0, 20.0);

        //check if add function is called maximum 3 times
        verify(calcService, atMost(3)).add(10.0,20.0);
    }


    /** 为模拟对象添加了一个exception子句 */
    @Test(expected = RuntimeException.class)
    public void testDoThrow(){
        //add the behavior to throw exception
        doThrow(new RuntimeException("Add operation not implemented"))
                .when(calcService).add(10.0,20.0);

        //test the add functionality
        Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);
    }


    /** Inorder类负责模拟在适当的行动过程中将要进行的方法调用的顺序.  */
    @Test
    public void testOrderVerify(){

        //add the behavior to add numbers
        when(calcService.add(20.0,10.0)).thenReturn(30.0);

        //subtract the behavior to subtract numbers
        when(calcService.subtract(20.0,10.0)).thenReturn(10.0);

        //test the add functionality
        Assert.assertEquals(mathApplication.add(20.0, 10.0),30.0,0);

        //test the subtract functionality
        Assert.assertEquals(mathApplication.subtract(20.0, 10.0),10.0,0);

        //create an inOrder verifier for a single mock
        InOrder inOrder = inOrder(calcService);

        //following will make sure that add is first called then subtract is called.
        inOrder.verify(calcService).add(20.0,10.0);
        inOrder.verify(calcService).subtract(20.0,10.0);
    }

    /** Mockito提供Answer接口，允许通用接口进行存根.  */
    @Test
    public void testAdd(){

        //add the behavior to add numbers
        when(calcService.add(20.0,10.0)).thenAnswer(new Answer<Double>() {

            @Override
            public Double answer(InvocationOnMock invocation) throws Throwable {
                //get the arguments passed to mock
                Object[] args = invocation.getArguments();

                //get the mock
                Object mock = invocation.getMock();

                //return the result
                return 30.0;
            }
        });

        //test the add functionality
        Assert.assertEquals(mathApplication.add(20.0, 10.0),30.0,0);
    }

    @Test
    public void testSpy(){
        Calculator calculator = new Calculator();
        calcService = spy(calculator);
        mathApplication.setCalculatorService(calcService);

        Assert.assertEquals(mathApplication.add(20.0, 10.0),30.0,0);
    }

    /** 重置后不能再使用了 */
    @Test
    public void testReset(){
        //add the behavior to add numbers
        when(calcService.add(20.0,10.0)).thenReturn(30.0);

        //test the add functionality
        Assert.assertEquals(mathApplication.add(20.0, 10.0),30.0,0);

        //reset the mock
        reset(calcService);
    }
}
