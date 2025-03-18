package com.example.capstone.Service;

import com.example.capstone.Model.Product;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {
    private  List<Product> products = new ArrayList<>();



    public String addProduct(@Valid Product product, CategoryService categoryService) {

        if (!categoryService.existsById(product.getCategoryID())) {
            return "wrong category ID";
        }


        products.add(product);
        return "product added successfully";
    }
    public List<Product> getAllProducts() {
        return products;
    }
    public boolean existsById(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public Product getProductById(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public void updateProduct(String id, @Valid Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                products.set(i, updatedProduct);
                break;
            }
        }
    }

    public void deleteProduct(String id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                products.remove(i);
                break;
            }
        }
    }

    //1endpoint discount
    public String getDiscount(@Valid Product product) {
        if (product.getPrice() >= 100) {
            int discount = 12;
            //the operation for the discount
            double newPrice = product.getPrice() - (product.getPrice() * discount / 100);
            product.setPrice(newPrice);
            return "You got a 12% discount";
        } else {
            return "Your amount should be 100 or more to get a discount";
        }
    }

    //2 endpoint sort the prices
    public List<Product> getSortedProducts() {
        products.sort(Comparator.comparing(Product::getPrice));
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }


}