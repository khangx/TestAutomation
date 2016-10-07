package com.qa.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;

/**
 * Created by Robbie on 10/6/2016.
 */
public class QALogger {
    public static final Boolean DEBUG = Boolean.valueOf(System.getProperty("debug"));

    public static void log(Object clazz, String msg, LogLevel logLevel){
        log(clazz, msg, logLevel, null);
    }

    public static void logStep(Object clazz, String msg){
        log(clazz, msg, LogLevel.STEP, null);
    }

    public static void log(Object clazz, String msg){
        log(clazz, msg, LogLevel.INFO, null);
    }

    /**
     *  Log output both to console and HTML report
     * @param clazz - Page Object
     * @param msg - Message to be logged
     * @param logLevel - Type of log message
     * @param e - Exception stacktrace
     */
    public static void log(Object clazz, String msg, LogLevel logLevel, Exception e){
        Logger logger;
        Class page;
        if(clazz instanceof Class) {
            page = (Class)clazz;
            logger = LogManager.getLogger(page);
        }else{
            page = clazz.getClass();
            logger = LogManager.getLogger(page);
        }

        if(DEBUG || logLevel.equals(LogLevel.DEBUG)){
            logger.debug(msg);
            Reporter.log(String.format("<div>[%s] %s</div>", page.getSimpleName(), msg));
        }else{
            switch (logLevel) {
                case STEP:
                    logger.info(msg);
                    Reporter.log(String.format("<div class='step'>[%s] %s</div>", page.getSimpleName(), msg));
                    break;
                case WARN:
                    logger.warn(msg);
                    Reporter.log(String.format("<div class='warn'>[%s] %s</div>", page.getSimpleName(), msg));
                    break;
                case FATAL:
                case ERROR:
                    if (e != null) {
                        logger.fatal(msg, e);
                        Reporter.log(String.format("<div class='fatal'>[%s] %s</div><div class='stacktrace'>%s</div>", page.getSimpleName(), msg, e));
                    } else {
                        logger.fatal(msg);
                        Reporter.log(String.format("<div class='fatal'>[%s] %s</div>", page.getSimpleName(), msg));
                    }
                    break;
            }
        }
    }
}
