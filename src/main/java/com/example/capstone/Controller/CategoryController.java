/*
package com.example.capstone.Controller;

import com.example.capstone.Model.Category;
import com.example.capstone.Service.CategoryService;
import com.example.capstone.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

        @Autowired
        private ProductService productService;


    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@Valid @RequestBody Category category) {
        productService.addCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body("Category added successfully");
    }


        @GetMapping("/get")
        public ResponseEntity<List<Category>> getAllCategorys() {
            List<Category> categories =categoryService.getCategory();
            return ResponseEntity.status(200).body(categories);
        }
        @PutMapping("/update")
        public ResponseEntity updateCategory(@PathVariable String id, @RequestBody @Valid Category updatedcategory,Category categories ,Errors errors){
            if(errors.hasErrors()){
                String massage=errors.getAllErrors().get(0).getDefaultMessage();
                return ResponseEntity.status(400).body(massage);
            }
            categories.set(id);
            return ResponseEntity.status(200).body(updatedcategory);
        }
        @DeleteMapping("/delete")
        public ResponseEntity<String> deleteCategory(@PathVariable String id, @RequestBody Category categories){
            categoryService.deleteCategory(id);
            return ResponseEntity.status(200).body("Deleted Successfully");
        }



}
*/
package com.example.capstone.Controller;

import com.example.capstone.Model.Category;
import com.example.capstone.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@Valid @RequestBody Category category) {
        categoryService.addCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body("Category added successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }
    //TODO: the update didnt work

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable String id, @Valid @RequestBody Category updatedCategory, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        boolean isUpdated = categoryService.updateCategory(id, updatedCategory); // Call the updated service method
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body("Category updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable String id) {
        boolean isDeleted = categoryService.deleteCategory(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Category deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
    }
}
