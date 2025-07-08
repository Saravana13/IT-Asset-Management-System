package com.example.it_asset_management_system.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data

public class AssetAssignmentRequestDto {
    int assetId;
    int userId;
    LocalDate assignedDate;
    LocalDate expectedReturnDate;
    LocalDate actualReturnDate;
}
