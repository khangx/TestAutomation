package com.qa.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;

/**
 * Created by Robbie on 10/6/2016.
 */
public class QALogger {
    public static void log(Object clazz, String msg, LogLevel logLevel){
        log(clazz, msg, logLevel, null);
    }

    public static void log(Object clazz, String msg){
        log(clazz, msg, LogLevel.INFO, null);
    }

    /**
     *
     * @param clazz
     * @param msg
     * @param logLevel
     * @param e
     */
    public static void log(Object clazz, String msg, LogLevel logLevel, Exception e){
        Logger logger;

        if(clazz instanceof Class) {
            logger = LogManager.getLogger((Class)clazz);
        }else{
            logger = LogManager.getLogger(clazz);
        }

        switch(logLevel){
            default:
            case INFO:
                logger.info(msg);
                Reporter.log(String.format("<div class='info'>%s</div>",msg));
                break;
            case DEBUG:
                logger.debug(msg);
                Reporter.log(String.format("<div class='debug'>%s</div>",msg));
                break;
            case WARN:
                logger.warn(msg);
                Reporter.log(String.format("<div class='warn'>%s</div>",msg));
                break;
            case FATAL:
            case ERROR:
                if(e!=null) {
                    logger.fatal(msg, e);
                    Reporter.log(String.format("<div class='fatal'>%s</div><div class='stacktrace'>%s</div>", msg, e));
                } else {
                    logger.fatal(msg);
                    Reporter.log(String.format("<div class='fatal'>%s</div>",msg));
                }
                break;
        }

    }
}
