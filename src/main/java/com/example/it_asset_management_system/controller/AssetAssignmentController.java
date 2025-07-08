package com.example.it_asset_management_system.controller;

import com.example.it_asset_management_system.DTO.AssetAssignmentRequestDto;
import com.example.it_asset_management_system.entity.AssetAssignment;
import com.example.it_asset_management_system.exceptions.AssetNotFoundException;
import com.example.it_asset_management_system.exceptions.UserNotFoundException;
import com.example.it_asset_management_system.service.AssetAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssetAssignmentController {

    private final AssetAssignmentService assetAssignmentService;

    @Autowired
    public AssetAssignmentController(AssetAssignmentService assetAssignmentService) {
        this.assetAssignmentService = assetAssignmentService;
    }

    @PostMapping
    public ResponseEntity<AssetAssignment> assignAsset(@RequestBody AssetAssignmentRequestDto assetAssignment){
        AssetAssignment assignment;
        try {
            assignment = assetAssignmentService.assignAsset(assetAssignment.getUserId(),assetAssignment.getAssetId(),
                    assetAssignment.getAssignedDate(),assetAssignment.getExpectedReturnDate());
        } catch (UserNotFoundException|AssetNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(assignment);
    }

    @GetMapping
    public ResponseEntity<List<AssetAssignment>> getAllAssetAssignments(){
        List<AssetAssignment> assignments = assetAssignmentService.getAllAssetAssignments();
        if(assignments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(assignments);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<AssetAssignment>> getAssetAssignmentByUserId(@PathVariable int id){
        List<AssetAssignment> assignments;
        try {
            assignments=assetAssignmentService.getAllAssetAssignmentsByUser(id);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(assignments);
    }
    @GetMapping("/assets/{id}")
    public ResponseEntity<List<AssetAssignment>> getAssetAssignmentByAssetId(@PathVariable int id){
        List<AssetAssignment> assignments;
        assignments=assetAssignmentService.getAllAssetAssignmentsByAsset(id);
        if(assignments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(assignments);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<AssetAssignment> returnAsset(@PathVariable int id, LocalDate returnDate){
        AssetAssignment assignment=assetAssignmentService.returnAsset(id, returnDate);
        return ResponseEntity.ok(assignment);
    }

}
