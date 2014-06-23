package com.swf.seed.test.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BasicLoggingExample {
	public static void main(String[] args) {
		Logger  logger = Logger.getLogger(BasicLoggingExample.class.getName());
		logger.log(Level.INFO, "Test of loging system");
	}
}
