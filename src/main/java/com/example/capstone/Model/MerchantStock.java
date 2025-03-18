package com.example.capstone.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {

    @NotEmpty(message = "Merchant Stock can't be eppty")
    private String id;

    @NotEmpty(message = "product id cant be empty")
    private String productId;

    @NotEmpty(message = "Merchant ID can't be empty")
    private String merchantId;

    @NotNull(message = "stock cant be null")
    @Min(value = 10,message = "Stock can't be less than 10 at the beginning")
    private int stock;


    public MerchantStock(@NotEmpty String id, @NotEmpty @Min(3) String name) {
    }


}
