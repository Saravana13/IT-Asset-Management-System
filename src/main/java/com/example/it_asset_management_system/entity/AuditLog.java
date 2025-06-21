package com.example.it_asset_management_system.entity;

import java.time.LocalDateTime;

public class AuditLog {

    private Long id;

    private Action action;
    private String entityAffected;
    private String performedBy;
    private LocalDateTime timestamp;
    private String details;


}
