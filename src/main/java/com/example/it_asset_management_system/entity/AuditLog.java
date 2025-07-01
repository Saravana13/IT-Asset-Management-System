package com.example.it_asset_management_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

@Entity
public class AuditLog extends BaseModel{

    @Enumerated(EnumType.STRING)
    private Action action;

    private String entityAffected;
    private String performedBy;
    private LocalDateTime timestamp;
    private String details;

}
