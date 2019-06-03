package com.transport.smart.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdOn;

    private LocalDateTime expiresOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getExpiresOn() {
        return expiresOn;
    }

    public void setExpiresOn(LocalDateTime expiresOn) {
        this.expiresOn = expiresOn;
    }
}
