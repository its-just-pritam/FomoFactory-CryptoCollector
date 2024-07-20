package com.microservice.ff.config.logger;

import ch.qos.logback.classic.pattern.ThrowableHandlingConverter;
import ch.qos.logback.classic.pattern.ThrowableProxyConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.contrib.json.JsonLayoutBase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationPid;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class Layout extends JsonLayoutBase<ILoggingEvent> {

    @Value("${spring.application.name}")
    private String APPLICATION_NAME;

    private final ThrowableHandlingConverter throwableHandlingConverter = new ThrowableProxyConverter();
    private final ApplicationPid applicationPid = new ApplicationPid();

    @Override
    public void start() {
        this.throwableHandlingConverter.start();;
        super.start();
    }

    @Override
    public void stop() {
        this.throwableHandlingConverter.stop();;
        super.stop();
    }


    @Override
    protected Map<String, Object> toJsonMap(ILoggingEvent iLoggingEvent) {

        LinkedHashMap<String, Object> jsonMap = new LinkedHashMap<>();
        this.add("message", true, iLoggingEvent.getFormattedMessage(), jsonMap);
        this.addTimestamp("timestamp", this.includeTimestamp, iLoggingEvent.getTimeStamp(), jsonMap);

        HashMap<String,String> fields = new HashMap<>();
        fields.put("processId", String.valueOf(applicationPid));
        this.addMap("fields", true, fields, jsonMap);
        this.add("level", true, String.valueOf(iLoggingEvent.getLevel()), jsonMap);
        this.add("thread", true, iLoggingEvent.getThreadName(), jsonMap);
        this.add("logger", true, iLoggingEvent.getLoggerName(), jsonMap);
        this.add("context", true, String.valueOf(iLoggingEvent.getLoggerContextVO()), jsonMap);

        if(iLoggingEvent.getThrowableProxy() != null)
            jsonMap.put("exception", this.throwableHandlingConverter.convert(iLoggingEvent));

        return jsonMap;
    }
}
