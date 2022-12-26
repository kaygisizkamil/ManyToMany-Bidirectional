package com.example.ManyToMany.business.abstracts;

import com.example.ManyToMany.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
    void deleteProductById(Long id);
    ProductDto updateProduct(Long id,ProductDto productDto);
}
