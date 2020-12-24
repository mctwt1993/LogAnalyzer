package com.test.loganalyzer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.loganalyzer.model.LogEvent;
import com.test.loganalyzer.utils.DatabaseUtils;
import com.test.loganalyzer.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class LogFileProcessor {
    private static final Logger logger = LoggerFactory.getLogger(LogFileProcessor.class);

    public static void main(String[] args) throws IOException {
        logger.debug("LogFileProcessor is started: " + LocalDateTime.now());
        Path logFilePath = FileUtils.getPath(args);
        DatabaseUtils.initDatabase();
        Map<String, LogEvent> eventMap = new HashMap<>();
        processLogFile(logFilePath, eventMap);
        if (eventMap.size() > 0) {
            DatabaseUtils.populateDatabase(eventMap);
        } else {
            logger.warn("No log events found in log file. Nothing is persisted.");
        }
        DatabaseUtils.printLogEventSummary();
        logger.debug("LogFileProcessor is completed: " + LocalDateTime.now());
    }

    private static void processLogFile(Path logFilePath, Map<String, LogEvent> eventMap) throws IOException {
        try (Stream<String> stream = Files.lines(logFilePath)) {
            ObjectMapper mapper = new ObjectMapper();
            stream.forEach(s -> {
                try {
                    LogEvent event = mapper.readValue(s, LogEvent.class);
                    if (eventMap.containsKey(event.getEventId())) {
                        processEvent(eventMap, event);
                    } else {
                        eventMap.put(event.getEventId(), event);
                    }
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });
        }
    }


    private static void processEvent(Map<String, LogEvent> eventMap, LogEvent event) {
        LogEvent storedInMapEvent = eventMap.get(event.getEventId());
        if (storedInMapEvent.getEventDuration() != null) {
            return;
        }
        int eventDuration = Math.toIntExact(Math.abs(storedInMapEvent.getEventTimestamp() - event.getEventTimestamp()));
        logger.debug("eventId " + event.getEventId() + " ; eventDuration = " + eventDuration);
        storedInMapEvent.setEventDuration(eventDuration);
        if (eventDuration > 4) {
            storedInMapEvent.setAlertFlag(Boolean.TRUE);
        } else {
            storedInMapEvent.setAlertFlag(Boolean.FALSE);
        }
    }

}
