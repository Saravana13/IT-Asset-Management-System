package com.example.it_asset_management_system.service;

import com.example.it_asset_management_system.entity.Asset;
import com.example.it_asset_management_system.entity.AssetAssignment;
import com.example.it_asset_management_system.exceptions.AssetNotFoundException;
import com.example.it_asset_management_system.exceptions.UserNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface AssetAssignmentService {

    AssetAssignment assignAsset(int user_id, int asset_id, LocalDate assigned_date, LocalDate excepted_return_date) throws UserNotFoundException, AssetNotFoundException;
    List<AssetAssignment> getAllAssetAssignments();
    List<AssetAssignment> getAllAssetAssignmentsByUser(int user_id) throws UserNotFoundException;
    List<AssetAssignment> getAllAssetAssignmentsByAsset(int asset_id) throws AssetNotFoundException;
    void returnAsset(int asset_assignment_id, LocalDate returned_date) ;

}
