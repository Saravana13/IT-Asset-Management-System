package com.example.it_asset_management_system.service;

import com.example.it_asset_management_system.entity.Asset;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImplementation implements AssetService {
    @Override
    public List<Asset> getAllAssets() {
        return List.of();
    }

    @Override
    public Asset getAssetById(int id) {
        return null;
    }

    @Override
    public Asset addAsset(Asset asset) {
        return null;
    }

    @Override
    public Asset updateAsset(Asset asset) {
        return null;
    }

    @Override
    public void deleteAsset(int id) {

    }
}
