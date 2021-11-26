package com.shoppingcart.controller;

import com.shoppingcart.common.ApiResponse;
import com.shoppingcart.model.Category;
import com.shoppingcart.service.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catgory")
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {

        if(Objects.nonNull(categoryService.readCategory(category.getCategoryName()))) {
            return new ResponseEntity<>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
        }
           categoryService.createCategory(category);
           return new ResponseEntity<>(new ApiResponse(true,"new category created"),HttpStatus.CREATED);
    }

    @GetMapping ("/list")
    public ResponseEntity<List<Category>> getAllCategory() {

        List<Category> categories =  categoryService.listCategories();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }
}
