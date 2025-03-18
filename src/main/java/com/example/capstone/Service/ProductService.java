package com.example.capstone.Service;

import com.example.capstone.Model.Product;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private  List<Product> products = new ArrayList<>();


    private final CategoryService categoryService;

    public ProductService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

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

    //حقت الديسكاونت
    public double getDiscount(Product product) {
        if (product.getPrice() >= 100) {
            int discount = 12;

            double newPrice = product.getPrice() - (product.getPrice() * discount / 100);
            product.setPrice(newPrice);
            return newPrice;
        } else {
            return 0;
        }
    }


    public List<Product> getSortedProducts() {
        products.sort(Comparator.comparing(Product::getPrice));
        return products;
    }

    //ترجعلي الي انباعو
    public List<Product> getSoldProducts() {
        return products.stream()
                .filter(product -> product.getSalesCount() > 0)
                .collect(Collectors.toList());
    }

    public List<Product> addProductt(Product product) {
        products.add(product);
        return products;
    }

    //تعرضلي منتجات من الفئه نفسها
    public List<Product> getProductsByCategory(String categoryId) {
        return products.stream()
                .filter(product -> product.getCategoryID().equals(categoryId))
                .collect(Collectors.toList());
    }
}
