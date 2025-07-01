package com.example.it_asset_management_system.repository;

import com.example.it_asset_management_system.entity.AssetAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetAssignmentRepository extends JpaRepository<AssetAssignment, Integer> {
}
