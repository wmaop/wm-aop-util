package org.wmaop.util.logger;

import com.wm.util.JournalLogger;

public class Logger {

	private static final int CODE = 3;
	private static final int FAC = JournalLogger.FAC_UTIL;
	private static final int INFO = JournalLogger.INFO;
	private static final int DEBUG = JournalLogger.DEBUG;
	private static final int ERROR = JournalLogger.ERROR;

	private static final String PFX = "]>]> ";

	public Logger(Class<?> clazz) {
	}

	public static Logger getLogger(Class<?> clazz) {
		return new Logger(clazz);
	}

	public void info(String message) {
		log(INFO, message);
	}

	public boolean isDebugEnabled() {
		return JournalLogger.isLogEnabled(CODE, FAC, DEBUG);
	}

	public void debug(String message) {
		log(DEBUG, message);
	}

	public void error(String message, Exception e) {
		log(ERROR, message);
	}

	void log(int level, String message) {
		JournalLogger.log(CODE, FAC, level, PFX + message);
	}
}
