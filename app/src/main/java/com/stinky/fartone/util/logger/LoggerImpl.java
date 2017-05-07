package com.stinky.fartone.util.logger;

import android.util.Log;

/**
 * Created by Acer on 2017-04-12.
 */

public class LoggerImpl implements Logger {
    private final String tag;

    private LoggerImpl(String tag) {
        this.tag = tag;
    }

    public static Logger getInstance(String className) {
        return new LoggerImpl(className);
    }

    @Override
    public void info(String message, Object... args) {
        Log.i(tag, prepareMessage(message, args));
    }

    @Override
    public void warn(String message, Object... args) {
        Log.w(tag, prepareMessage(message, args));
    }

    @Override
    public void error(String message, Object... args) {
        Log.e(tag, prepareMessage(message, args));
    }

    private String prepareMessage(String template, Object... args) {
        if (template.contains("{}") && args != null && args.length > 0) {
            return String.format(template, args);
        } else {
            return template;
        }
    }
}
