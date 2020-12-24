package com.test.loganalyzer;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class LogFileProcessorTest {

    @Test(expected = NoSuchFileException.class)
    public void mainWithWrongPathInArgs() throws IOException {
        String[] args = new String[]{"C:\\test"};
        LogFileProcessor.main(args);
    }


}