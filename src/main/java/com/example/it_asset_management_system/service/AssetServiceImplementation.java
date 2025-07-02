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
    public Asset updateAsset(int id, String assetName, String serialNumber,
                             String manufacturer, String model, String assetType,
                             LocalDate purchaseDate, LocalDate warrantyExpireDate, String status) {
      Asset asset = assetRepository.findById(id).orElse(null);
      if(asset==null) {
          return null;
      }
      if(assetName!=null) {
          asset.setAssetName(assetName);
      }
      if(serialNumber!=null) {
          asset.setSerialNumber(serialNumber);
      }
      if(manufacturer!=null) {
          asset.setManufacturer(manufacturer);
      }
      if(model!=null) {
          asset.setModel(model);
      }
      if(assetType!=null) {
          asset.setAssetType(AssetType.valueOf(assetType));
      }
      if(purchaseDate!=null) {
          asset.setPurchaseDate(purchaseDate);
      }
      if(warrantyExpireDate!=null) {
          asset.setWarrantyExpireDate(warrantyExpireDate);
      }
      if(status!=null) {
          asset.setStatus(AssetStatus.valueOf(status));
      }
      return assetRepository.save(asset);
    }

    @Override
    public boolean deleteAsset(int id) {
        if(assetRepository.existsById(id)) {
            assetRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
