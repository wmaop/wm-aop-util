package org.wmaop.util.logger;

import com.wm.util.JournalLogger;

public class Logger<C extends Class>  {

	private static final int CODE = 3;
	private static final int FAC = JournalLogger.FAC_UTIL;
	private static final int INFO = JournalLogger.INFO;
	private static final int DEBUG = JournalLogger.DEBUG;
	private static final int ERROR = JournalLogger.ERROR;
	
	public Logger(C clazz) {
	}

	public static <T extends Class> Logger<T> getLogger(T clazz) {
		return new Logger<T>(clazz);
	}

	public void info(String message) {
		JournalLogger.log(CODE, FAC, INFO, message);
	}

	public boolean isDebugEnabled() {
		return JournalLogger.isLogEnabled(CODE, FAC, DEBUG);
	}

	public void debug(String message) {
		JournalLogger.log(CODE, FAC, DEBUG, message);
	}

	public void error(String message, Exception e) {
		JournalLogger.log(CODE, FAC, ERROR, message);
	}
	
}
