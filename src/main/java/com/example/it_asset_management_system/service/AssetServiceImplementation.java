package com.example.it_asset_management_system.service;

import com.example.it_asset_management_system.entity.Asset;
import com.example.it_asset_management_system.entity.AssetStatus;
import com.example.it_asset_management_system.entity.AssetType;
import com.example.it_asset_management_system.repository.AssetRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AssetServiceImplementation implements AssetService {

    AssetRepository assetRepository;

    public AssetServiceImplementation(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public List<Asset> getAllAssets() {

        return assetRepository.findAll();
    }

    @Override
    public Asset getAssetById(int id) {
        if(assetRepository.findById(id).isPresent()){
            return assetRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public Asset addAsset(String assetName, String serialNumber, String manufacturer,
                          String model, String assetType, LocalDate purchasedDate,
                          LocalDate warrantyExpireDate, String status) {
        Asset asset = new Asset();
        asset.setAssetName(assetName);
        asset.setSerialNumber(serialNumber);
        asset.setManufacturer(manufacturer);
        asset.setModel(model);
        asset.setAssetType(AssetType.valueOf(assetType));
        asset.setPurchaseDate(purchasedDate);
        asset.setWarrantyExpireDate(warrantyExpireDate);
        asset.setStatus(AssetStatus.valueOf(status));
        assetRepository.save(asset);
        return asset;
    }


    @Override
    public Asset updateAsset(Asset asset) {
        return assetRepository.save(asset);
    }

    @Override
    public void deleteAsset(int id) {
        assetRepository.deleteById(id);
    }
}
