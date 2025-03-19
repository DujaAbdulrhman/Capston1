package com.example.capstone.Controller;

import com.example.capstone.Model.Product;
import com.example.capstone.Service.CategoryService;
import com.example.capstone.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    private List<Product> mostSeelP=new ArrayList<>();

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




    
    @GetMapping("/discount/{index}")
    public ResponseEntity<?> getDiscount(@PathVariable int index, @RequestParam double price) {
        if (index < 0 || index >= products.size()) {

            return ResponseEntity.status(400).body("Index out of range");
        }

        Product productToUpdate = products.get(index);
        productToUpdate.setPrice(price);
        double result = productService.getDiscount(productToUpdate);

        return ResponseEntity.status(200).body(result);
    }



    //3 endpoint
    @GetMapping("/sorted")
    public ResponseEntity<List<Product>> getSortedProducts() {
        List<Product> sortedProducts = productService.getSortedProducts();
        return ResponseEntity.ok(sortedProducts);
    }
    //4
    @GetMapping("/mostsold")
    public ResponseEntity<List<Product>> getMostSoldProducts() {
        List<Product> mostSoldProducts = productService.getSoldProducts();
        return ResponseEntity.status(200).body(mostSoldProducts);
    }
    //5
    @GetMapping("/search/category")
    public ResponseEntity<String> searchByCategory(@RequestParam String categoryId) {
        String productsInCategory = productService.getProductsByCategory(categoryId).toString();
        return ResponseEntity.status(200).body(productsInCategory);
    }

}
