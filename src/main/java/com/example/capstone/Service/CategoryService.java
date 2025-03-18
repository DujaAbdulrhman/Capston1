package com.example.capstone.Service;
/*
import com.example.capstone.Model.Category;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private List<Category> categories = new ArrayList<>();


    public void addCategory(@Valid Category category) {
        categories.add(category);
    }

    public List<Category> getAllCategories() {
        return categories;
    }


    public Category getCategoryById(String id) {
        for (Category category : categories) {
            if (category.getId().equals(id)) {
                return category;
            }
        }
        return null;
    }

        public Category updateCategory(String id, @Valid Category updatedCategory) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId().equals(id)) {
                categories.set(i, updatedCategory);
                return updatedCategory;
            }
        }
            return updatedCategory;
        }


    public void deleteCategory(String id) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId().equals(id)) {
                categories.remove(i);
                break;
            }
        }
    }

    public List<Category> getCategory() {
        return categories;
    }
}*/

import com.example.capstone.Model.Category;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private List<Category> categories = new ArrayList<>();

    public void addCategory(@Valid Category category) {
        categories.add(category);
    }

    public List<Category> getAllCategories() {
        return categories;
    }

    public Category getCategoryById(String id) {
        for (Category category : categories) {
            if (category.getId().equals(id)) {
                return category;
            }
        }
        return null;
    }

    public boolean updateCategory(String id, @Valid Category updatedCategory) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId().equals(id)) {
                categories.set(i, updatedCategory);
                return true;
            }
        }
        return false;
    }


    public boolean deleteCategory(String id) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId().equals(id)) {
                categories.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean existsById(String id) {
        for (Category category : categories) {
            if (category.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
