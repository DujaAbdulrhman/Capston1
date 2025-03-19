package com.example.capstone.Controller;

import com.example.capstone.Model.MerchantStock;
import com.example.capstone.Model.Product;
import com.example.capstone.Model.User;
import com.example.capstone.Service.MerchantStockService;
import com.example.capstone.Service.ProductService;
import com.example.capstone.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final ProductService productService;
    private final MerchantStockService merchantStockService;

    @Autowired
    public UserController(UserService userService, ProductService productService, MerchantStockService merchantStockService) {
        this.userService = userService;
        this.productService = productService;
        this.merchantStockService = merchantStockService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        boolean isAdded = userService.addUser(user);
        if (isAdded) {
            return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with the same ID already exists");
        }
    }


    @GetMapping("/get")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @Valid @RequestBody User updatedUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        boolean isUpdated = userService.updateUser(id, updatedUser);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    //6
    @GetMapping("/orderhistory/{userId}")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<Order> orderHistory = user.getOrderHistory(); // افترض أن لديك قائمة orderHistory في كلاس User
        return ResponseEntity.status(HttpStatus.OK).body(orderHistory);
    }
}
