package com.shoppingcart.service;
import com.shoppingcart.model.Category;
import java.util.*;


public interface CategoryService {

    List<Category> listCategories() ;

    void createCategory(Category category) ;

    Category readCategory(String categoryName) ;


}
