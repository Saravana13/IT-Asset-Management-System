package com.example.it_asset_management_system.entity;

import java.time.LocalDate;

public class AssetAssignment {

    private long id;

    private User assignedTo;

    private Asset asset;

    private LocalDate assignedDate;
    private LocalDate expectedReturnDate;
    private LocalDate actualReturnDate;

}
