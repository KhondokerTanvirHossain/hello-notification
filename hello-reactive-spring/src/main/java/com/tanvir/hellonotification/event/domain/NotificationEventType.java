package com.tanvir.hellonotification.event.domain;

import lombok.Getter;

@Getter
public enum NotificationEventType {
    TRIGGER("TRIGGER"),
    NO_ACTION("NO_ACTION")
    ;
    private final String value;

    NotificationEventType(String value) {
        this.value = value;
    }
}

