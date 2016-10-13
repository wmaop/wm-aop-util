package org.wmaop.util.logger;

import com.wm.util.JournalLogger;

/*
 * Place holder until correct server log handling figured out
 */
public class WmLogger {

	private static final int CODE = 3;
	private static final int FAC = JournalLogger.FAC_UTIL;

	private static final String PFX = "]>]> ";

	public void info(String message) {
		log(JournalLogger.INFO, message);
	}

	public boolean isDebugEnabled() {
		return JournalLogger.isLogEnabled(CODE, FAC, JournalLogger.DEBUG);
	}

	public void debug(String message) {
		log(JournalLogger.DEBUG, message);
	}

	public void error(String message, Exception e) {
		JournalLogger.log(CODE, FAC, JournalLogger.ERROR, PFX + message, e);
	}

	private void log(int level, String message) {
		JournalLogger.log(CODE, FAC, level, PFX + message);
	}

	public void warn(String message) {
		log(JournalLogger.WARNING, message);		
	}
}
