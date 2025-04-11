package com.berru.app.elearningmanagementsystem.service;

import com.berru.app.elearningmanagementsystem.entity.Category;

import java.util.List;

public interface CategoryService {

    Category addCategory(Category category);

    Category updateCategory(Category category);

    Category getCategoryById(int category);

    List<Category> getCategoriesByStatusIn(List<String> status);
}
