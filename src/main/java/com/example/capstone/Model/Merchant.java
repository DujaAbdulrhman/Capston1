package com.example.capstone.Model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {
    @NotEmpty
    private String id;
    @NotEmpty
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;
    private int salesCount;


    public int getStock() {
        return 5;
    }
}
