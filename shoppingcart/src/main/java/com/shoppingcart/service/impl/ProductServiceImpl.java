package com.shoppingcart.service.impl;

import com.shoppingcart.dto.productDto.ProductDto;
import com.shoppingcart.dto.productDto.ProductUpdateDto;
import com.shoppingcart.model.Category;
import com.shoppingcart.model.Product;
import com.shoppingcart.repository.ProductRepo;
import com.shoppingcart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

private final ProductRepo productRepo;

    @Override
    public void createProduct(ProductDto productDto, Category category){

         Product product = new Product();
         product.setDescription(productDto.getDescription());
         product.setName(productDto.getName());
         product.setCategory(category);
         product.setPrice(productDto.getPrice());
         product.setQuantity(productDto.getQuantity());
         productRepo.save(product);
}


    @Override
    public void updateProduct(ProductUpdateDto productDto){

        Product product = productRepo.getById(productDto.getProductId());
        product.setQuantity(productDto.getQuantity());
        productRepo.save(product);
    }

    private ProductDto getProductDto(Product product){

        ProductDto productDto = new ProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setPrice((int) product.getPrice());
        productDto.setQuantity(product.getQuantity());
        return productDto;

    }

    @Override
    public List<ProductDto> getAllProducts() {

       List<ProductDto> productDtoList = new ArrayList<>();
       final List<Product> allProducts = productRepo.findAll();
       for(Product product : allProducts){

           productDtoList.add(getProductDto(product));
    }
          return productDtoList;

    }
}
