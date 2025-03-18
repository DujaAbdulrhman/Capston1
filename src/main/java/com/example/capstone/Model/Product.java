package com.example.capstone.Model;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    @NotEmpty
    private String id;
    @NotEmpty
    private String name;
    @NotNull
    private double price;

    @NotNull(message = "Category ID must not be empty")
    private String categoryID;


    //categoryID  لازم يتأكد انها موجوده بالكاتوجري كلاس فنسوي التحقق بالاي دي حقت البرودكت
}
