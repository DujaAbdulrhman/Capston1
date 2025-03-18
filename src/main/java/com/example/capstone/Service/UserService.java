/*
package com.example.capstone.Service;

import com.example.capstone.Model.User;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();

    // Add a user
    public void addUser(@Valid User user) {
        users.add(user);
    }


    public List<User> getAllUsers() {
        return users;
    }


    public User getUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }


    public void updateUser(String id, @Valid User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.set(i, updatedUser);
                break;
            }
        }
    }

    // Delete a user
    public void deleteUser(String id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.remove(i);
                break;
            }
        }
    }
}*/
package com.example.capstone.Service;

import com.example.capstone.Model.User;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();


    public boolean addUser(@Valid User user) {
        // تشييكه للاي دي اذا موجود او لا
        if (getUserById(user.getId()) != null) {
            return false;
        }
        users.add(user);
        return true;
    }


    public List<User> getAllUsers() {
        return users;
    }


    public User getUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public boolean updateUser(String id, @Valid User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.set(i, updatedUser);
                return true;
            }
        }
        return false;
    }


    public boolean deleteUser(String id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }
}