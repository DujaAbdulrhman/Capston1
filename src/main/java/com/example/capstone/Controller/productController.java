package com.example.capstone.Controller;

import com.example.capstone.Model.Product;
import com.example.capstone.Service.CategoryService;
import com.example.capstone.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Valid
@RestController
@RequestMapping("/api/v1/product")
public class productController {

    //TODO: delete and update

        private ProductService productService;
        private final CategoryService categoryService;

    private List<Product> products = new ArrayList<>();


    public productController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@Valid @RequestBody Product product) {
        String message = productService.addProduct(product, categoryService);
        if ("Invalid category ID".equals(message)) {
            return ResponseEntity.status(400).body(message);
        }
        return ResponseEntity.status(200).body(message);
    }

        @GetMapping("/get")
        public ResponseEntity<List<Product>> getAllProducts() {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.status(200).body(products);
        }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable String id, @RequestBody @Valid Product updatedProduct, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        productService.updateProduct(id, updatedProduct);
        return ResponseEntity.status(200).body("Product updated successfully!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {

        productService.deleteProduct(id);
        return ResponseEntity.status(200).body("Product deleted successfully!");
    }

    //endpoint 1
        @GetMapping("/discount/{index}")
        public ResponseEntity getDiscount(@PathVariable int index, @RequestBody @Valid Product product, Errors errors){
        if (errors.hasErrors()){
            String massage = errors.getAllErrors().get(0).getDefaultMessage();;
            return  ResponseEntity.status(400).body(massage);
        }
            if (index < 0 || index >= products.size()) {
                return ResponseEntity.status(400).body("index out of range");
            }
            Product productToUpdate = products.get(index);
            productToUpdate.setPrice(product.getPrice());

            String result = productService.getDiscount(productToUpdate);
            return ResponseEntity.status(200).body(result);
    }

    //2 
    @GetMapping("/sorted")
    public ResponseEntity<List<Product>> getSortedProducts() {
        List<Product> sortedProducts = productService.getSortedProducts();
        return ResponseEntity.ok(sortedProducts);
    }

    //3
    @GetMapping("/sold")
    public ResponseEntity<List<Product>> getSoldProducts() {
        List<Product> soldProducts = productService.getSoldProducts();
        return ResponseEntity.ok(soldProducts);
    }

}

