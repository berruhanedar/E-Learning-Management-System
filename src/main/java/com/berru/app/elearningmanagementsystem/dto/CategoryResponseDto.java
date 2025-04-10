package com.berru.app.elearningmanagementsystem.dto;

import java.util.ArrayList;
import java.util.List;

import com.berru.app.elearningmanagementsystem.entity.Category;
import lombok.Data;

@Data
public class CategoryResponseDto extends CommonApiResponse {

    private List<Category> categories = new ArrayList<>();
}
