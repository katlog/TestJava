// Annotation type with an array parameter -  Page 173
package name.fw.effectivejava.examples.chapter06.item35;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated _03method is a test _03method that must throw the any
 * of the designated exceptions to succeed.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTest1 {
	Class<? extends Exception>[] value();
}
