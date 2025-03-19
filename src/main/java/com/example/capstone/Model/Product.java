package com.example.capstone.Model;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@AllArgsConstructor
public class Product {
    @NotEmpty
    private String id;
    @NotEmpty
    private String name;
    @NotNull
    private double price;

    @NotNull(message = "category Iid must not be empty")
    private String categoryID;

    private int salesCount;

}
