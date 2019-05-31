package dfw.log;

import java.util.logging.Logger;

/**
 * Created by fw on 2019/5/7
 */
public class JDKLog {
    public static void main( String[] args )    {
        Logger logger = Logger.getLogger("JDKLog");
        logger.info("Hello World.");
    }

}
