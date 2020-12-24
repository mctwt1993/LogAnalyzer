package com.test.loganalyzer.utils;

import com.test.loganalyzer.LogFileProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
    private static final String LOG_FILE_NAME = "logfile.txt";


    public static Path getPath(String[] args) {
        Path logFilePath = null;
        if (args == null || args.length == 0) {
            logger.warn(" No file path selected, will be used default log file.");
            ClassLoader classLoader = LogFileProcessor.class.getClassLoader();
            URL resource = classLoader.getResource(LOG_FILE_NAME);
            if (resource != null) {
                try {
                    URI uri = resource.toURI();
                    logFilePath = Paths.get(uri);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        } else {
            logFilePath = Paths.get(args[0]);
        }
        return logFilePath;
    }
}
