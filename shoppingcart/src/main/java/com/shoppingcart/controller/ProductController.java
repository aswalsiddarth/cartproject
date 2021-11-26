package com.shoppingcart.controller;


import com.shoppingcart.common.ApiResponse;
import com.shoppingcart.dto.productDto.ProductDto;
import com.shoppingcart.dto.productDto.ProductUpdateDto;
import com.shoppingcart.model.Category;
import com.shoppingcart.repository.CategoryRepo;
import com.shoppingcart.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;
    private final CategoryRepo categoryRepo;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto){

        final Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategoryId());
        if(optionalCategory.isEmpty()){
            return new ResponseEntity<>(new ApiResponse(false, "category does not exist"), HttpStatus.BAD_REQUEST);
        }
        else{
            productService.createProduct(productDto, optionalCategory.get());
            return new ResponseEntity<>(new ApiResponse(true, "product has been added"), HttpStatus.CREATED);
        }

    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getProducts(){

        List<ProductDto>products =  productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @PostMapping("/update")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductUpdateDto productDto){

        productService.updateProduct(productDto);
        return new ResponseEntity<>(new ApiResponse(true, "quantity has been updated"), HttpStatus.CREATED);


    }

}
