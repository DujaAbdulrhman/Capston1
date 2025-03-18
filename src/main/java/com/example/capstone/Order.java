package com.example.capstone.Model;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
@Data
@RequiredArgsConstructor
public class Order {
    @NotEmpty
    private String orderId;
    @NotEmpty
    private String productId;
    @NotEmpty
    private String merchantId;
    @NotEmpty
    private Date orderDate;
    @NotNull (message = "total price cant be null")
    private double totalPrice;




}
