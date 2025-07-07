package com.example.it_asset_management_system.service;

import com.example.it_asset_management_system.entity.Asset;
import com.example.it_asset_management_system.entity.AssetAssignment;
import com.example.it_asset_management_system.entity.User;
import com.example.it_asset_management_system.exceptions.AssetNotFoundException;
import com.example.it_asset_management_system.exceptions.UserNotFoundException;
import com.example.it_asset_management_system.repository.AssetAssignmentRepository;
import com.example.it_asset_management_system.repository.AssetRepository;
import com.example.it_asset_management_system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AssetAssignmentServiceImplementation implements AssetAssignmentService {

    private final AssetAssignmentRepository assetAssignmentRepository;
    private final UserRepository userRepository;
    private final AssetRepository assetRepository;

    public AssetAssignmentServiceImplementation(AssetAssignmentRepository assetAssignmentRepository,
                                                UserRepository userRepository, AssetRepository assetRepository) {
        this.assetAssignmentRepository = assetAssignmentRepository;
        this.userRepository = userRepository;
        this.assetRepository = assetRepository;
    }

    @Override
    public AssetAssignment assignAsset(int user_id, int asset_id, LocalDate assigned_date, LocalDate excepted_return_date)
            throws UserNotFoundException, AssetNotFoundException {
        Optional<User> user = userRepository.findById(user_id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found in DB");
        }

        Optional<Asset> asset = assetRepository.findById(asset_id);
        if(asset.isEmpty()){
            throw new AssetNotFoundException("Asset not found in DB");
        }


        AssetAssignment assetAssignment = new AssetAssignment();
        assetAssignment.setAsset(asset.get());
        assetAssignment.setUser(user.get());
        assetAssignment.setAssignedDate(assigned_date);
        assetAssignment.setExpectedReturnDate(excepted_return_date);
        return assetAssignmentRepository.save(assetAssignment);
    }

    @Override
    public List<AssetAssignment> getAllAssetAssignments() {
        return assetAssignmentRepository.findAll();
    }

    @Override
    public List<AssetAssignment> getAllAssetAssignmentsByUser(int user_id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(user_id);
        if(user.isEmpty()){
            throw new UserNotFoundException("User not found in DB");
        }
        return assetAssignmentRepository.findByUserId(user_id);
    }

    @Override
    public List<AssetAssignment> getAllAssetAssignmentsByAsset(int asset_id) throws AssetNotFoundException {
        Optional<Asset> asset = assetRepository.findById(asset_id);
        if(asset.isEmpty()){
            throw new AssetNotFoundException("Asset not found in DB");
        }
        return assetAssignmentRepository.findByAssetId(asset_id);
    }

    @Override
    public void returnAsset(int asset_assignment_id, LocalDate returned_date) {
        Optional<AssetAssignment> assetAssignment = assetAssignmentRepository.findById(asset_assignment_id);
        if(assetAssignment.isEmpty()){
            return;
        }
        assetAssignment.get().setActualReturnDate(returned_date);
        assetAssignmentRepository.save(assetAssignment.get());
    }
}
