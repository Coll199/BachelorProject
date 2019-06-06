package com.transport.smart.message;

import java.util.UUID;

public class SubscriptionRequest {
    private UUID userId;
    private Long days;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Long getDays() {
        return days;
    }

    public void setDays(Long days) {
        this.days = days;
    }
}
