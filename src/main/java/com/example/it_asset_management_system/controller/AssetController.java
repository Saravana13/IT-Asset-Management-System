package com.example.it_asset_management_system.controller;

import com.example.it_asset_management_system.entity.Asset;
import com.example.it_asset_management_system.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    @Autowired
    private AssetService assetService;

    AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets() {
        List<Asset> assets = assetService.getAllAssets();
        return ResponseEntity.of(Optional.ofNullable(assets));
    }

    public Asset getAssetById(int id) {
        return assetService.getAssetById(id);
    }

    @PostMapping
    public ResponseEntity<Asset> addAsset(@RequestBody Asset asset) {
        System.out.println("Adding asset");
        //(String assetName, String serialNumber, String manufacturer,
        //                          String model, String assetType, LocalDate purchasedDate,
        //                          LocalDate warrantyExpireDate, String status)
        Asset createdAsset = assetService.addAsset(asset.getAssetName(),asset.getSerialNumber(),
                asset.getManufacturer(),asset.getModel(),asset.getAssetType().toString(),
                asset.getPurchaseDate(),asset.getWarrantyExpireDate(),asset.getStatus().toString());
        System.out.println("Created asset, sending response");
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAsset);
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
