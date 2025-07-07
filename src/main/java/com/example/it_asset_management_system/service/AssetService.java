package com.example.it_asset_management_system.service;

import com.example.it_asset_management_system.entity.Asset;

import java.time.LocalDate;
import java.util.List;

public interface AssetService {

    List<Asset> getAllAssets();

    Asset getAssetById(int id);

    Asset addAsset(String assetName, String serialNumber, String manufacturer,
                   String model, String assetType, LocalDate purchasedDate,
                   LocalDate warrantyExpireDate,String status);

    Asset updateAsset(int id,
                      String assetName,
                      String serialNumber,
                      String manufacturer,
                      String model,
                      String assetType,
                      LocalDate purchaseDate,
                      LocalDate warrantyExpireDate,
                      String status);

    boolean deleteAsset(int id);

}
