package slf4j_logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jLogBackDemo {
	
	private static Logger logger=LoggerFactory.getLogger(Slf4jLogBackDemo.class);
	
	public static void main(String[] args) {
		//级别为debug的日志
        logger.debug("Hello! debug!");
        //级别为info的日志
        logger.info("Hello! info!");
        //级别为warn的日志
        logger.warn("Hello! warn!");
        //级别为error的日志
        logger.error("Hello! error!");
	}

}
