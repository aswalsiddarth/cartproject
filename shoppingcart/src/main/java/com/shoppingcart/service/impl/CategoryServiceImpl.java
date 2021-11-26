package com.shoppingcart.service.impl;

import com.shoppingcart.model.Category;
import com.shoppingcart.repository.CategoryRepo;
import com.shoppingcart.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public List<Category> listCategories() {
        
      return  categoryRepo.findAll();
    }

    @Override
    public void createCategory(Category category) {

        categoryRepo.save(category);

    }

    @Override
    public Category readCategory(String categoryName) {
        
        return categoryRepo.findByCategoryName(categoryName);

    }

    
}
