package org.wmaop.util.logger;

import org.apache.log4j.Level;

public abstract class Logger {

	public static Logger getLogger(Class<?> clazz) {
		return new Log4jLogger(clazz);  // Fixed until WmLogger produces correct output	
	}

	public abstract void info(String message);

	public abstract boolean isDebugEnabled();

	public abstract void debug(String message);

	public abstract void error(String message, Exception e);

	public abstract void log(Level level, String message);

	public abstract void warn(String message);
}
