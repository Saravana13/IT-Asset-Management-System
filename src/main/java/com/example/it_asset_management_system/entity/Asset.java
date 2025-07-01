package com.example.it_asset_management_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Asset extends BaseModel{

    @Enumerated(EnumType.STRING)
    private AssetType assetType;

    private String assetName;
    private String serialNumber;
    private String manufacturer;
    private String model;

    private LocalDate purchaseDate;
    private LocalDate warrantyExpireDate;

    @Enumerated(EnumType.STRING)
    private AssetStatus status;

    @OneToMany
    private List<AssetAssignment> assignments;
}
