package com.example.it_asset_management_system.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;
import java.util.List;

public class Asset {

    private Long id;
    private AssetType assetType;
    private String assetName;
    private String serialNumber;
    private String manufacturer;
    private String model;

    private LocalDate purchaseDate;
    private LocalDate warrantyExpireDate;

    @Enumerated(EnumType.STRING)
    private AssetStatus status;

    private List<AssetAssignment> assignments;
}
