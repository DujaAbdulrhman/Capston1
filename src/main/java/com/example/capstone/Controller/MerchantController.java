package com.example.capstone.Controller;


import com.example.capstone.Model.Merchant;
import com.example.capstone.Model.MerchantStock;
import com.example.capstone.Service.MerchantService;
import com.example.capstone.Service.MerchantStockService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/marchantstock")
public class merchantStockController {

    private final MerchantService marchentStockService;
    private final MerchantStockService merchentStock;
    private final MerchantService merchantService;
    private final MerchantStockService merchentStockService;


    public merchantStockController(MerchantStockService merchentStockService, MerchantService merchantService) {
        this.merchentStock = merchentStockService;
        this.marchentStockService = merchantService;
        this.merchantService = merchantService;
        this.merchentStockService = merchentStockService;
    }
    @PostMapping("/add")
    public ResponseEntity<String> addMerchant(@RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        boolean isAdded = merchantService.addMerchant(merchant); // Ensure 'merchant' is passed correctly
        if (isAdded) {
            return ResponseEntity.ok("Merchant added successfully");
        } else {
            return ResponseEntity.badRequest().body("Merchant with the same ID already exists");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<Merchant>> getAllMerchantStocks() {
        List<Merchant> merchantStocks = marchentStockService.getAllMerchants();
        return ResponseEntity.status(200).body(merchantStocks);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateMerchantStock(@PathVariable String id, @RequestBody @Valid MerchantStock updatedMerchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }

        boolean isUpdated = merchentStockService.updateMerchantStock(id, updatedMerchantStock);
        if (isUpdated) {
            return ResponseEntity.ok("Merchant stock updated successfully");
        } else {
            return ResponseEntity.status(400).body("Merchant stock not found");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMerchantStock(@PathVariable String id){
        boolean isDeleted=MerchantStockService.deleteMerchantStock(id);
        if (isDeleted){
            return ResponseEntity.status(200).body("Deleted Successfully");
        }return ResponseEntity.status(400).body("Merchant stock not found");
    }

    //1 .
    @PostMapping ("/addtostock")
    public ResponseEntity<String> addtoStock( @RequestParam String productId ,@RequestParam String merchantId , @RequestParam int additionalStock) {
        try {
            merchentStockService.addtoStock(productId, merchantId, additionalStock);
            return ResponseEntity.status(200).body("Stock added successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
