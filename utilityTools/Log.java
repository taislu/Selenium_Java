package utilityTools;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {
	
	Logger logger;
	
	public Log(String funcName, String propertyFile) {
		
		logger = Logger.getLogger(funcName);
		
		PropertyConfigurator.configure(propertyFile);
	}
	
	public void info(String msg) {
		
		logger.info(msg);
	}
	
	public void warn(String msg) {
		
		logger.warn(msg);
	}
	
	public void error(String msg) {
		
		logger.error(msg);
	}
	
	public void fatal(String msg) {
		
		logger.fatal(msg);
	}
	
	public void debug(String msg) {
		
		logger.debug(msg);
	}

}
