package com.test.loganalyzer.utils;

import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;

public class FileUtilsTest {

    @Test
    public void getPathFromEmptyString() {
        Path path = FileUtils.getPath(new String[]{""});
        Assert.assertEquals("", path.getFileName().toString());
    }

    @Test
    public void getPathFromEmptyArray() {
        Path path = FileUtils.getPath(new String[]{});
        Assert.assertEquals("logfile.txt", path.getFileName().toString());
    }

    @Test
    public void getPathWhenNoArguments() {
        Path path = FileUtils.getPath(null);
        Assert.assertEquals("logfile.txt", path.getFileName().toString());
    }
}