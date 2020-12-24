package com.test.loganalyzer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LogEvent {

    @JsonProperty("id")
    private String eventId;
    private String type;
    private String host;
    private Integer eventDuration;
    private Boolean alertFlag;

    @JsonProperty("timestamp")
    private Long eventTimestamp;

    @JsonProperty("state")
    private String eventState;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getEventDuration() {
        return eventDuration;
    }

    public void setEventDuration(Integer eventDuration) {
        this.eventDuration = eventDuration;
    }

    public Boolean getAlertFlag() {
        return alertFlag;
    }

    public void setAlertFlag(Boolean alertFlag) {
        this.alertFlag = alertFlag;
    }

    public Long getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(Long eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public String getEventState() {
        return eventState;
    }

    public void setEventState(String eventState) {
        this.eventState = eventState;
    }

    @Override
    public String toString() {
        return "LogEvent{" +
                "eventId='" + eventId + '\'' +
                ", type='" + type + '\'' +
                ", host='" + host + '\'' +
                ", eventDuration=" + eventDuration +
                ", alertFlag=" + alertFlag +
                ", eventTimestamp=" + eventTimestamp +
                ", eventState='" + eventState + '\'' +
                '}';
    }
}
