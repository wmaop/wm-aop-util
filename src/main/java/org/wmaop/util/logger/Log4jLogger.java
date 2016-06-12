package org.wmaop.util.logger;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Log4jLogger extends org.wmaop.util.logger.Logger {

	private static final String PFX = "]>]> ";
	private Logger logger;

	public Log4jLogger(Class<?> clazz) {
		logger = Logger.getLogger(clazz);
	}

	@Override
	public void info(String message) {
		log(Level.INFO, message);
	}

	@Override
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}
	
	@Override
	public void debug(String message) {
		log(Level.DEBUG, message);
	}

	@Override
	public void warn(String message) {
		log(Level.WARN, message);		
	}

	@Override
	public void error(String message, Exception e) {
		logger.error(PFX + message, e);
	}

	@Override
	public void log(Level level, String message) {
		logger.log(level, PFX + message);
	}

}
