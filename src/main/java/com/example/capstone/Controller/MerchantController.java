package com.example.capstone.Controller;

import com.example.capstone.Model.Merchant;
import com.example.capstone.Service.MerchantService;
import com.example.capstone.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/merchant")
public class MerchantController {

    private final MerchantService merchantService;
    private final MerchantStockService merchantStockService;

   @Autowired
    public MerchantController(MerchantService merchantService, MerchantStockService merchantStockService) {
        this.merchantService = merchantService;
       this.merchantStockService = merchantStockService;
   }

    @PostMapping("/add")
    public ResponseEntity<String> addMerchant(@RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        boolean isAdded = merchantService.addMerchant(merchant);
        if (isAdded) {
            return ResponseEntity.ok("Merchant added successfully");
        } else {
            return ResponseEntity.badRequest().body("Merchant with the same ID already exists");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateMerchant(@PathVariable String id, @RequestBody @Valid Merchant updatedMerchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        if (id.equals(updatedMerchant.getId())) {
            return ResponseEntity.badRequest().body(updatedMerchant.getId());
        }
        boolean isUpdated = merchantService.updateMerchant(id, updatedMerchant);
        if (isUpdated) {
            return ResponseEntity.ok("Merchant updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Merchant not found");
        }
    }
    @PostMapping("/addstock")
    public ResponseEntity<String> addStock(
            @RequestParam String productId,
            @RequestParam String merchantId,
            @RequestParam int additionalStock) {
        try {
            merchantStockService.addtoStock(productId, merchantId, additionalStock);
            return ResponseEntity.status(HttpStatus.OK).body("Stock added successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("/get")
    public ResponseEntity getAllMerchants() {
        ArrayList<Merchant> merchants = merchantService.getAllMerchants();
        return ResponseEntity.ok(merchants);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMerchant(@PathVariable String id) {
        boolean isDeleted = merchantService.deleteMerchant(id);
        if (isDeleted) {
            return ResponseEntity.ok("Merchant deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Merchant not found");
        }
    }

    @GetMapping("/checkstock/{id}")
    public ResponseEntity<String> checkStock(@PathVariable String id) {

        String message = merchantService.checkMerchantStock(id);
        return ResponseEntity.status(200).body(message);
    }


}
