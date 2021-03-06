package com.asex.util;

import org.apache.log4j.Logger;

public class Log {

	static Logger log = Logger.getLogger(Log.class.getName());

	public static void logDebug(String message) {
		log.debug(message);
	}

	public static void logInfo(String message) {
		log.info(message);
	}

	public static void logError(Object o) {
		log.error(o);
	}

}
