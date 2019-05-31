package dfw.log;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.util.UUID;

/**
 * Created by fw on 2019/5/15
 */
public class Log4j2 {
    private static Logger logger = LogManager.getLogger(Log4j2.class.getName());

    static {
        ThreadContext.put("MDCCRT_GUID_TRACEID", UUID.randomUUID().toString());
    }

    public static void main(String[] args) {
        /**
         * 如果没有自定义配置文件，上面这个类在写一个main方法，控制台会输入下面的样子：
         * 19:09:40.256 [main] ERROR cn.lsw.base.log4j2.Hello - Did it again!
         * 19:09:40.260 [main] FATAL cn.lsw.base.log4j2.Hello - 我是fatal信息
         * 14:58:32.409 [main] ERROR dfw.log.Log4j2 - hello
         */

        logger.trace("开始程序.");
        Hello hello= new Hello();
        if (!hello.hello()) {
            logger.error("hello");
        }
        logger.trace("退出程序.");
    }


   static class Hello {

        static Logger logger = LogManager.getLogger(Hello.class.getName());
        public boolean hello() {
            //trace级别的信息，单独列出来是希望你在某个方法或者程序逻辑开始的时候调用，和logger.trace("entry")基本一个意思
            logger.entry();
            //error级别的信息，参数就是你输出的信息
            logger.error("Did it again!");
            logger.error("Did it again!!");
            //info级别的信息
            logger.info("我是info信息");
            logger.debug("我是debug信息");
            logger.warn("我是warn信息");
            logger.fatal("我是fatal信息");
            //这个就是制定Level类型的调用：谁闲着没事调用这个，也不一定哦！
            logger.log(Level.DEBUG, "我是debug信息");
            //和entry()对应的结束方法，和logger.trace("exit");一个意思
            logger.exit();
            return false;
        }
    }
}


