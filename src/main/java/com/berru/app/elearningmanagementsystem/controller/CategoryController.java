package com.berru.app.elearningmanagementsystem.controller;

import com.berru.app.elearningmanagementsystem.dto.CategoryResponseDto;
import com.berru.app.elearningmanagementsystem.dto.CommonApiResponse;
import com.berru.app.elearningmanagementsystem.entity.Category;
import com.berru.app.elearningmanagementsystem.facade.CategoryFacade;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/course/category")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

    @Autowired
    private CategoryFacade categoryFacade;

    @PostMapping("/add")
    @Operation(summary = "Api to add category")
    public ResponseEntity<CommonApiResponse> addCategory(@RequestBody Category category) {
        return categoryFacade.addCategory(category);
    }

    @PutMapping("/update")
    @Operation(summary = "Api to update category")
    public ResponseEntity<CommonApiResponse> updateCategory(@RequestBody Category category) {
        return categoryFacade.updateCategory(category);
    }

    @GetMapping("/fetch/all")
    @Operation(summary = "Api to fetch all category")
    public ResponseEntity<CategoryResponseDto> fetchAllCategory() {
        return categoryFacade.fetchAllCategory();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Api to delete category all its events")
    public ResponseEntity<CommonApiResponse> deleteCategory(@RequestParam("categoryId") int categoryId) {
        return categoryFacade.deleteCategory(categoryId);
    }
}