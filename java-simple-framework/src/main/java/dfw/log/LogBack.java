package dfw.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by fw on 2019/5/7
 */
public class LogBack {
    static final Logger logger = LoggerFactory.getLogger(LogBack.class);
    public static void main(String[] args) {
        logger.trace("Trace Level.");
        logger.debug("Debug Level.");
        logger.info("Info Level.");
        logger.warn("Warn Level.");
        logger.error("Error Level.");
        logger.info("test place holder :{},{}",11,12);
    }

}
