package com.example.capstone.Model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {

    @NotEmpty(message = "id can not be empty")
    private String id;
    @NotEmpty(message = "name can not be empty")
    private String name;


}
