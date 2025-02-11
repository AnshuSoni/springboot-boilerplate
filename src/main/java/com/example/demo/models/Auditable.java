package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<T> {

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_dt")
    protected LocalDateTime createdDt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated_dt")
    protected LocalDateTime lastUpdatedDt;


    @CreatedBy
    @Column(name = "created_by")
    protected T createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    protected T lastModifiedBy;
}
