package com.example.it_asset_management_system.repository;

import com.example.it_asset_management_system.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Integer> {
}
