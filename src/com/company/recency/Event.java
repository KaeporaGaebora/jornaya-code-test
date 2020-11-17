package com.company.recency;

public class Event {
    private String category;
    private Integer timestamp;

    public Integer getTimestamp() {
        return timestamp;
    }

    public String getCategory() {
        return category;
    }

    public Event(String category, Integer timestamp) {
        this.category = category;
        this.timestamp = timestamp;
    }
}
