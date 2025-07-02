package com.example.it_asset_management_system.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data

public class AssetAssignment extends BaseModel{

    @ManyToOne
    private User assignedTo;

    @ManyToOne
    private Asset asset;

    private LocalDate assignedDate;
    private LocalDate expectedReturnDate;
    private LocalDate actualReturnDate;

}
