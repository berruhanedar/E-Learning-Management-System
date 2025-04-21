package com.berru.app.elearningmanagementsystem.service;

import com.berru.app.elearningmanagementsystem.entity.Category;
import com.berru.app.elearningmanagementsystem.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    public Category updateCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    public Category getCategoryById(int categoryId) {

        Optional<Category> optionalCategory = this.categoryRepository.findById(categoryId);

        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        } else {
            return null;
        }

    }

    public List<Category> getCategoriesByStatusIn(List<String> status) {
        return this.categoryRepository.findByStatusIn(status);
    }
}
