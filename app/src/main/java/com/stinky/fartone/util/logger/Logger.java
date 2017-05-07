package com.stinky.fartone.util.logger;

/**
 * Created by Acer on 2017-04-12.
 */

public interface Logger {

    void info(String message, Object... args);
    void warn(String message, Object... args);
    void error(String message, Object... args);
}
