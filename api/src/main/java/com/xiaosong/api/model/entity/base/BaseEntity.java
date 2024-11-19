package com.xiaosong.api.model.entity.base;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity implements Serializable {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "created_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;

    @Column(name = "last_updated_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedTime;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @PrePersist
    protected void onCreate() {
        if (isDeleted == null) {
            isDeleted = false;
        }
        id = UUID.randomUUID();
        createdTime = new Date(System.currentTimeMillis());
        lastUpdatedTime = new Date(System.currentTimeMillis());
    }
    @PreUpdate
    protected void onUpdate() {
        lastUpdatedTime = new Date(System.currentTimeMillis());
    }
}