package com.example.capstone.Service;


import com.example.capstone.Model.MerchantStock;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MerchantStockService {

    private static List<MerchantStock> merchantStocks = new ArrayList<>();

    private final MerchantService merchantService;
    private final ProductService productService;

    public MerchantStockService(MerchantService merchantService, ProductService productService) {
        this.merchantService = merchantService;
        this.productService = productService;
    }



    public boolean addMerchantStock(@Valid MerchantStock merchantStock) {
        if (getMerchantStockById(merchantStock.getId()) != null) {
            return false; // اوريدي موجود
        }
        // نتأكد اذا معرف التاجر موجود
        if (!merchantService.existsById(merchantStock.getMerchantId())) {
            return false; // Invalid merchant ID
        }
        // نتأكد اذا معرف البرودكت موجود
        if (!productService.existsById(merchantStock.getProductId())) {
            return false; // اذامو موجود
        }
        merchantStocks.add(merchantStock);//الاضافه اذا موجود
        return true;
    }

    public List<MerchantStock> getAllMerchantStocks() {
        return merchantStocks;
    }


    public MerchantStock getMerchantStockById(String id) {
        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getId().equals(id)) {
                return merchantStock;
            }
        }
        return null; // اذا مو موجوده
    }


    public boolean updateMerchantStock(String id, @Valid MerchantStock updatedMerchantStock) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId().equals(id)) {
                merchantStocks.set(i, updatedMerchantStock);
                return true; // اذا تعدلت
            }
        }
        return false;
    }

    public static boolean deleteMerchantStock(String id) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId().equals(id)) {
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }


    public void addtoStock(String productId, String merchantId, int additionalStock) {
        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getProductId().equals(productId) && merchantStock.getMerchantId().equals(merchantId)) {
                merchantStock.setStock(merchantStock.getStock() + additionalStock);
                return;
            }
        }
        throw new IllegalArgumentException("Merchant stock not found");
    }

    public void addStock(String productId, String merchantId, int additionalStock) {
    }
}

