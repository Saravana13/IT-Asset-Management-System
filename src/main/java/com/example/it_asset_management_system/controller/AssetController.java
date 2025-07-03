package com.example.it_asset_management_system.controller;

import com.example.it_asset_management_system.entity.Asset;
import com.example.it_asset_management_system.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;

    @Autowired
    AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    //Get all assets
    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets() {
        List<Asset> assets = assetService.getAllAssets();
        if(assets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(assets);
    }

    //Get Asset By id
    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable int id) {
        Asset asset=assetService.getAssetById(id);
        if(asset==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(asset);
    }

    //Adding new Asset
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

    //Delete Asset By id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssetById(@PathVariable int id) {
        boolean deleted=assetService.deleteAsset(id);
        if(deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //Update Asset
    @PutMapping("/{id}")
    public ResponseEntity<Asset> updateAssetById(@PathVariable int id ,@RequestBody Asset asset) {
        Asset updatedAsset=assetService.updateAsset(
                id,
                asset.getAssetName(),
                asset.getSerialNumber(),
                asset.getManufacturer(),
                asset.getModel(),
                asset.getAssetType().toString(),
                asset.getPurchaseDate(),
                asset.getWarrantyExpireDate(),
                asset.getStatus().toString()
        );
        if(updatedAsset==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedAsset);
    }

}
