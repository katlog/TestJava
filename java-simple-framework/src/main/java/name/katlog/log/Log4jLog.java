package name.katlog.log;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by fw on 2019/5/7
 */
public class Log4jLog {

    public static void main(String args[]) {
        Logger logger = LogManager.getLogger(Log4jLog.class);
        logger.debug("Debug Level");
        logger.info("Info Level");
        logger.warn("Warn Level");
        logger.error("Error Level");
    }

}
