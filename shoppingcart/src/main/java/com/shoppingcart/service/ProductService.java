package com.shoppingcart.service;

import com.shoppingcart.dto.productDto.ProductDto;
import com.shoppingcart.dto.productDto.ProductUpdateDto;
import com.shoppingcart.model.Category;


import java.util.List;

public interface ProductService {

    void createProduct(ProductDto productDto, Category category) ;

    List<ProductDto> getAllProducts();

    public void updateProduct(ProductUpdateDto productDto);


}
