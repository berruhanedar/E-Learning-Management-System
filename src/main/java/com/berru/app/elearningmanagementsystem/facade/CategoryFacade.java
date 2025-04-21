package com.berru.app.elearningmanagementsystem.facade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.berru.app.elearningmanagementsystem.dto.CategoryResponseDto;
import com.berru.app.elearningmanagementsystem.dto.CommonApiResponse;
import com.berru.app.elearningmanagementsystem.entity.Category;
import com.berru.app.elearningmanagementsystem.entity.Course;
import com.berru.app.elearningmanagementsystem.enums.status.ActiveStatus;
import com.berru.app.elearningmanagementsystem.exception.CategorySaveFailedException;
import com.berru.app.elearningmanagementsystem.service.CategoryService;
import com.berru.app.elearningmanagementsystem.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class CategoryFacade {

    private final Logger LOG = LoggerFactory.getLogger(CategoryFacade.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CourseService courseService;

    public ResponseEntity<CommonApiResponse> addCategory(Category category) {
        LOG.info("Request received for adding category");

        CommonApiResponse commonApiResponse = new CommonApiResponse();

        if (category == null) {
            commonApiResponse.setResponseMessage("Input is missing");
            commonApiResponse.setSuccess(false);

            return new ResponseEntity<CommonApiResponse>(commonApiResponse, HttpStatus.BAD_REQUEST);
        }

        category.setStatus(ActiveStatus.ACTIVE.value());

        Category savedCategory = this.categoryService.addCategory(category);

        if (savedCategory == null) {
            throw new CategorySaveFailedException("Category could not be added");
        }

        commonApiResponse.setResponseMessage("Category added successfully");
        commonApiResponse.setSuccess(true);

        return new ResponseEntity<CommonApiResponse>(commonApiResponse, HttpStatus.OK);

    }

    public ResponseEntity<CommonApiResponse> updateCategory(Category category) {


        LOG.info("Received request to update category");

        CommonApiResponse apiResponse = new CommonApiResponse();

        if (category == null) {
            apiResponse.setResponseMessage("Input is missing");
            apiResponse.setSuccess(false);

            return new ResponseEntity<CommonApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        if (category.getId() == 0) {
            apiResponse.setResponseMessage("Category ID is missing");
            apiResponse.setSuccess(false);

            return new ResponseEntity<CommonApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        category.setStatus(ActiveStatus.ACTIVE.value());
        Category savedCategory = this.categoryService.updateCategory(category);

        if (savedCategory == null) {
            throw new CategorySaveFailedException("Failed to update the category");
        }

        apiResponse.setResponseMessage("Category updated successfully");
        apiResponse.setSuccess(true);

        return new ResponseEntity<CommonApiResponse>(apiResponse, HttpStatus.OK);

    }

    public ResponseEntity<CategoryResponseDto> fetchAllCategory() {

        LOG.info("Processing request to fetch all categories");

        CategoryResponseDto apiResponse = new CategoryResponseDto();

        List<Category> categories = new ArrayList<>();

        categories = this.categoryService.getCategoriesByStatusIn(Arrays.asList(ActiveStatus.ACTIVE.value()));

        if (CollectionUtils.isEmpty(categories)) {
            apiResponse.setResponseMessage("No categories found");
            apiResponse.setSuccess(false);

            return new ResponseEntity<CategoryResponseDto>(apiResponse, HttpStatus.OK);
        }

        apiResponse.setCategories(categories);
        apiResponse.setResponseMessage("Categories fetched successfully");
        apiResponse.setSuccess(true);

        return new ResponseEntity<CategoryResponseDto>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<CommonApiResponse> deleteCategory(int categoryId) {

        LOG.info("Processing request to delete category");

        CommonApiResponse apiResponse = new CommonApiResponse();

        if (categoryId == 0) {
            apiResponse.setResponseMessage("Category ID is missing");
            apiResponse.setSuccess(false);

            return new ResponseEntity<CommonApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        Category category = this.categoryService.getCategoryById(categoryId);

        if (category == null) {
            apiResponse.setResponseMessage("Category not found");
            apiResponse.setSuccess(false);

            return new ResponseEntity<CommonApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<Course> courses = this.courseService.getByCategoryAndStatus(category, ActiveStatus.ACTIVE.value());

        category.setStatus(ActiveStatus.DEACTIVATED.value());
        Category updatedCategory = this.categoryService.updateCategory(category);

        if (updatedCategory == null) {
            throw new CategorySaveFailedException("Failed to delete category");
        }

        if (!CollectionUtils.isEmpty(courses)) {

            for (Course course : courses) {
                course.setStatus(ActiveStatus.DEACTIVATED.value());
            }

            List<Course> updatedCourses = this.courseService.updateAll(courses);

            if (CollectionUtils.isEmpty(updatedCourses)) {
                throw new CategorySaveFailedException("Failed to deactivate courses under the category");
            }
        }

        apiResponse.setResponseMessage("Category and its courses were successfully deleted");
        apiResponse.setSuccess(true);

        return new ResponseEntity<CommonApiResponse>(apiResponse, HttpStatus.OK);

    }

}