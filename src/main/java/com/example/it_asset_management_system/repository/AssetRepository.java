package com.example.it_asset_management_system.repository;

import com.example.it_asset_management_system.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Integer> {
}
