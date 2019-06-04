
/**  
 * @Title: ConfigFactory.java
 * @Package: org.person.dfw.refelct.proxy.demo
 * @author: 丰伟
 * @date: 2017年4月20日 下午3:37:46
 * @version: V1.0  
 */ 
package org.person.dfw.refelct.proxy.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Properties;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface Value {
    String value();
}

interface IConfig {
    @Value("db.url")
    String dbUrl();
    
    @Value("db.validation")
    boolean isValidated();
    
    @Value("db.pool.size")
    int poolSize();
}
/**用动态代理，获取配置文件，非常方便且有优势：*/
public class ConfigFactory {
    private ConfigFactory() {}

    public static IConfig create(final InputStream is) throws IOException{
        final Properties properties = new Properties();
        properties.load(is);
        
        return (IConfig) Proxy.newProxyInstance(IConfig.class.getClassLoader(),
            new Class[] { IConfig.class }, new PropertyMapper(properties));
    }

    public static final class PropertyMapper implements InvocationHandler {

        private final Properties properties;
    
        public PropertyMapper(Properties properties) {
            this.properties = properties;
        }
    
        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
    
            final Value value = method.getAnnotation(Value.class);

            if (value == null) return null;
              
            String property = properties.getProperty(value.value());
            if (property == null) return (null);
              
            final Class<?> returns = method.getReturnType();
            if (returns.isPrimitive()){
                if (returns.equals(int.class)) return (Integer.valueOf(property));
                else if (returns.equals(long.class)) return (Long.valueOf(property));
                else if (returns.equals(double.class)) return (Double.valueOf(property));
                else if (returns.equals(float.class)) return (Float.valueOf(property));
                else if (returns.equals(boolean.class)) return (Boolean.valueOf(property));
            }
            return property;
        }
    }
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //java.io默认定位到当前用户目录("user.dir")下
        System.out.println(System.getProperty("user.dir"));
        IConfig config = ConfigFactory.create(new FileInputStream("basic/org/person/dfw/refelct/proxy/demo/config.properties"));
        String dbUrl = config.dbUrl();
        System.out.println(dbUrl);
        boolean isLoginValidated = config.isValidated();
        System.out.println(isLoginValidated);
        int dbPoolSize = config.poolSize();
        System.out.println(dbPoolSize);
    }
}
