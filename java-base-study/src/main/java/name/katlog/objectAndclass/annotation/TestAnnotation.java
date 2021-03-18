package name.katlog.objectAndclass.annotation;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by fw on 2019/6/26
 */
public class TestAnnotation {

    @Test
    public void _demo(){
        @DescriptionLocalVariable("变量注释")
        int a = 0;
    }

}

@Target({ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@interface DescriptionLocalVariable {
    String value() default "";
}