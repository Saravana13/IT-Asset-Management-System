package com.example.it_asset_management_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data

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

}
