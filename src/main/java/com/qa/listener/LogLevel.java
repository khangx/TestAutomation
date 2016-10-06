package com.qa.listener;

/**
 * Created by Robbie on 10/6/2016.
 */
public enum LogLevel {
    INFO(0), DEBUG(1), WARN(2), ERROR(3), FATAL(4);
    Integer lvl = 0;
    LogLevel(Integer lvl){
        this.lvl = lvl;
    }
    public Integer getLvl(){ return lvl;}
}
