package com.test.loganalyzer.model;

import org.junit.Assert;
import org.junit.Test;

public class LogEventTest {

    @Test
    public void getEventId() {
        LogEvent event = new LogEvent();
        event.setEventId("123");
        Assert.assertEquals("123", event.getEventId());
    }

    @Test
    public void setEventId() {
        LogEvent event = new LogEvent();
        Assert.assertNull(event.getEventId());
        event.setEventId("1234");
        Assert.assertEquals("1234", event.getEventId());
    }

    @Test
    public void getType() {
        LogEvent event = new LogEvent();
        event.setType("APPLICATION_1");
        Assert.assertEquals("APPLICATION_1", event.getType());
    }

    @Test
    public void setType() {
        LogEvent event = new LogEvent();
        Assert.assertNull(event.getType());
        event.setType("APPLICATION_2");
        Assert.assertEquals("APPLICATION_2", event.getType());
    }

    @Test
    public void getHost() {
        LogEvent event = new LogEvent();
        event.setHost("12345");
        Assert.assertEquals("12345", event.getHost());
    }

    @Test
    public void setHost() {
        LogEvent event = new LogEvent();
        Assert.assertNull(event.getHost());
        event.setHost("1233");
        Assert.assertEquals("1233", event.getHost());
    }

    @Test
    public void getEventDuration() {
        LogEvent event = new LogEvent();
        event.setEventDuration(6);
        Assert.assertEquals(6, event.getEventDuration().intValue());
    }

    @Test
    public void setEventDuration() {
        LogEvent event = new LogEvent();
        Assert.assertNull(event.getEventDuration());
        event.setEventDuration(200);
        Assert.assertEquals(200, event.getEventDuration().intValue());
    }

    @Test
    public void getAlertFlag() {
        LogEvent event = new LogEvent();
        event.setAlertFlag(true);
        Assert.assertEquals(true, event.getAlertFlag());
    }

    @Test
    public void setAlertFlag() {
        LogEvent event = new LogEvent();
        Assert.assertNull(event.getAlertFlag());
        event.setAlertFlag(false);
        Assert.assertEquals(false, event.getAlertFlag());
    }

    @Test
    public void getEventTimestamp() {
        LogEvent event = new LogEvent();
        event.setEventTimestamp(6432111L);
        Assert.assertEquals(6432111L, event.getEventTimestamp().longValue());
    }

    @Test
    public void setEventTimestamp() {
        LogEvent event = new LogEvent();
        Assert.assertNull(event.getEventTimestamp());
        event.setEventTimestamp(500000000L);
        Assert.assertEquals(500000000L, event.getEventTimestamp().longValue());
    }

    @Test
    public void getEventState() {
        LogEvent event = new LogEvent();
        event.setEventState("STARTED");
        Assert.assertEquals("STARTED", event.getEventState());
    }

    @Test
    public void setEventState() {
        LogEvent event = new LogEvent();
        Assert.assertNull(event.getEventState());
        event.setEventState("FINISHED");
        Assert.assertEquals("FINISHED", event.getEventState());
    }

    @Test
    public void testToString() {
        LogEvent event = new LogEvent();
        String expectedString = "LogEvent{eventId='null', type='null', host='null', eventDuration=null, alertFlag=null, eventTimestamp=null, eventState='null'}";
        Assert.assertEquals(expectedString, event.toString());
    }
}