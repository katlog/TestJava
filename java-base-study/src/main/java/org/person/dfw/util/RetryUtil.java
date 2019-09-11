package org.person.dfw.util;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by fw on 2019/7/31
 */
@Slf4j
public class RetryUtil {
    interface Action{
        void action() throws Exception;
    }

    public static void retry(int times,Action action){
        retry(times, action,"");
    }
    public static void retry(int times,Action action,String errorMsg){
        if (times <= 0) {
            return;
        }
        try {
            times--;
            action.action();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(" retry error:{}", errorMsg, e);
            retry(times, action, errorMsg);
        }
    }


    public interface Supplier<T>{
        T get() throws Exception;
    }


    public static <T> T retry(int times,Supplier<T> action){
       return retry(times, action,"");
    }
    public static <T> T retry(int times,Supplier<T> action,String errorMsg){
        if (times <= 0) {
            return null;
        }
        try {
            times--;
            return action.get();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(" retry error:{}", errorMsg, e);
            return retry(times, action, errorMsg);
        }
    }

    public static void main(String[] args) {
        retry(3,() -> {
            System.out.println("args = " + 1/0);
        });

        int i = retry(3, () -> 1 / 0);
        System.out.println("i = " + i);
    }
}
