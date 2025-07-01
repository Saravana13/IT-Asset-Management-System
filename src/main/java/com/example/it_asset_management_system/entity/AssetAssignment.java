package com.example.it_asset_management_system.entity;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class AssetAssignment extends BaseModel{

    private User assignedTo;

    private Asset asset;

    private LocalDate assignedDate;
    private LocalDate expectedReturnDate;
    private LocalDate actualReturnDate;

}
