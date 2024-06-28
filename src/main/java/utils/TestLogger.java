package utils;

import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.LogManager;

public class TestLogger {
	
	private static final Logger logger = (Logger) LogManager.getLogger(TestLogger.class);

	public static void info(String message) {
		long threadId = Thread.currentThread().getId();
		logger.info("Thread " + threadId + ": " + message);

	}

	public static void error(String message) {
		long threadId = Thread.currentThread().getId();
		logger.error("Thread " + threadId + ": " + message);
	}

	public static void debug(String message) {
		long threadId = Thread.currentThread().getId();
		logger.debug("Thread " + threadId + ": " + message);
	}

	public static void warn(String message) {
		long threadId = Thread.currentThread().getId();
		logger.warn("Thread " + threadId + ": " + message);
	}

	public static void trace(String message) {
		long threadId = Thread.currentThread().getId();
		logger.trace("Thread " + threadId + ": " + message);
	}



}
