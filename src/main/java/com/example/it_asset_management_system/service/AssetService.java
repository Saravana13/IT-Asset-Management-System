package com.example.it_asset_management_system.service;

import com.example.it_asset_management_system.entity.Asset;

import java.util.List;

public interface AssetService {

    List<Asset> getAllAssets();
    Asset getAssetById(int id);
    Asset addAsset(Asset asset);
    Asset updateAsset(Asset asset);
    void deleteAsset(int id);

}
