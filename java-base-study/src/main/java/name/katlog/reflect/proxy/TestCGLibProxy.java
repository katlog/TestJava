package name.katlog.reflect.proxy;

import java.lang.reflect.Method;

import org.junit.Test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

class Run{
    public void test1(String str){ System.out.println("excute test1 ...");}
}
public class TestCGLibProxy {
    class MyCGLibProxy implements MethodInterceptor{
        private Enhancer enhancer = new Enhancer();
        
        public Object getProxy(Class clazz) {
            enhancer.setSuperclass(clazz);
            enhancer.setCallback(this);
            return enhancer.create();
        }
        @Override
        public Object intercept(Object obj, Method mehtod, Object[] args, MethodProxy proxy)
            throws Throwable {
            System.out.println("enter proxy ...");
            Object result=proxy.invokeSuper(obj, args);
            System.out.println("out proxy ...");
            return result;
        }
    }
    
    @Test public void proxy(){
        Run proxy = (Run) new MyCGLibProxy().getProxy(Run.class);
        proxy.test1("string param");
    }
}
