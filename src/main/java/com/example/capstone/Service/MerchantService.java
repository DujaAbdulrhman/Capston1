package com.example.capstone.Service;

import com.example.capstone.Model.Merchant;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MerchantService {
    private final List<Merchant> merchants = new ArrayList<>();

    public boolean addMerchant(@Valid Merchant merchant) {
        for (Merchant existingMerchant : merchants) {
            if (existingMerchant.getId().equals(merchant.getId())) {
                return false;
            }
        }
        merchants.add(merchant);
        return true;
    }


    public List<Merchant> getAllMerchants() {
        return merchants;
    }

    public boolean updateMerchant(String id, Merchant updatedMerchant) {
        for (int i = 0; i < merchants.size(); i++) {
            Merchant existingMerchant = merchants.get(i);
            if (existingMerchant.getId().equals(id)) {
                merchants.set(i, updatedMerchant);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchant(String id) {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId().equals(id)) {
                merchants.remove(i);
                return true;
            }
        }
        return false;
    }

    //نشيك على ايدي التاجر اذا التاجر موجود او لا
    public boolean existsById(String id) {
        for (Merchant merchant : merchants) {
            if (merchant.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
    public String checkMerchantStock(String merchantId) {
        for (Merchant merchant : merchants) {
            if (merchant.getId().equals(merchantId)) {
                if (merchant.getStock() < 10) {
                    return "Stock for merchant is low";
                }
                return "Stock level is sufficient";
            }
        }
        return "Merchant not found";
    }
}
