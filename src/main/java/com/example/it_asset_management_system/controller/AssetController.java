package com.example.it_asset_management_system.controller;

import com.example.it_asset_management_system.entity.Asset;
import com.example.it_asset_management_system.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    @Autowired
    private AssetService assetService;

    AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    public List<Asset> getAllAssets() {
        return assetService.getAllAssets();
    }

    public Asset getAssetById(int id) {
        return assetService.getAssetById(id);
    }

    @PostMapping
    public void addAsset(Asset asset) {
        assetService.addAsset(asset);
    }

    @DeleteMapping
    public void deleteAssetById(int id) {
        assetService.deleteAsset(id);
    }

    @PutMapping
    public void updateAssetById(Asset asset) {
        assetService.updateAsset(asset);
    }

}
