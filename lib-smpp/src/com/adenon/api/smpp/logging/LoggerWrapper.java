package com.adenon.api.smpp.logging;

import org.apache.log4j.Level;

public class LoggerWrapper {

    private org.apache.log4j.Logger logger;
    private boolean                 writeMethodNames = true;

    public LoggerWrapper(org.apache.log4j.Logger logger) {
        this.logger = logger;
    }

    public boolean isDebugEnabled() {
        return this.logger.isDebugEnabled();
    }

    public boolean isInfoEnabled() {
        return this.logger.isInfoEnabled();
    }

    public void debug(String className,
                      String methodName,
                      long transactionID,
                      String tagLabel,
                      String msg) {
        StringBuilder builder = this.getLogHeader(className, methodName, transactionID, tagLabel);
        builder.append(msg);
        this.logger.debug(builder.toString());
    }

    private StringBuilder getLogHeader(String className,
                                       String methodName,
                                       long transactionID,
                                       String tagLabel) {
        StringBuilder builder = new StringBuilder(128);
        if (this.isWriteMethodNames()) {
            builder.append("[");
            builder.append(className);
            builder.append("<");
            builder.append(methodName);
            builder.append(">] ");
        }
        if (transactionID > 0) {
            builder.append("(T:");
            builder.append(transactionID);
            builder.append(") ");
        }
        if (tagLabel != null) {
            builder.append(tagLabel);
            builder.append(" ");
        }
        return builder;
    }

    public void info(String className,
                     String methodName,
                     long transactionID,
                     String tagLabel,
                     String msg) {
        StringBuilder builder = this.getLogHeader(className, methodName, transactionID, tagLabel);
        builder.append(msg);
        this.logger.info(builder.toString());
    }

    public void error(String className,
                      String methodName,
                      long transactionID,
                      String tagLabel,
                      String msg) {
        StringBuilder builder = this.getLogHeader(className, methodName, transactionID, tagLabel);
        builder.append(msg);
        this.logger.error(builder.toString());
    }

    public void error(String className,
                      String methodName,
                      long transactionID,
                      String tagLabel,
                      String msg,
                      Throwable e) {
        StringBuilder builder = this.getLogHeader(className, methodName, transactionID, tagLabel);
        builder.append(msg);
        this.logger.error(builder.toString(), e);
    }

    public void warn(String className,
                     String methodName,
                     long transactionID,
                     String tagLabel,
                     String msg) {
        StringBuilder builder = this.getLogHeader(className, methodName, transactionID, tagLabel);
        builder.append(msg);
        this.logger.warn(builder.toString());
    }

    public void fatal(String className,
                      String methodName,
                      long transactionID,
                      String tagLabel,
                      String msg) {
        StringBuilder builder = this.getLogHeader(className, methodName, transactionID, tagLabel);
        builder.append(msg);
        this.logger.fatal(builder.toString());
    }

    public boolean isWriteMethodNames() {
        return this.writeMethodNames;
    }

    public void setWriteMethodNames(boolean writeMethodNames) {
        this.writeMethodNames = writeMethodNames;
    }

    public void setLevel(Level level) {
        this.logger.setLevel(level);

    }

}
