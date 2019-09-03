package com.saasovation.supply;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 聚合类标识
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface FactoryMethod {

    Source from() default Source.Aggregate;

    enum Source{
        /** 聚合 */
        Aggregate,
        /** 领域服务 */
        DomainService;
    }
}
