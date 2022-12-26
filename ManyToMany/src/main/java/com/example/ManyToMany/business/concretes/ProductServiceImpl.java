package com.example.ManyToMany.business.concretes;

import com.example.ManyToMany.business.abstracts.ProducerService;
import com.example.ManyToMany.business.abstracts.ProductService;
import com.example.ManyToMany.domain.Producer;
import com.example.ManyToMany.domain.Product;
import com.example.ManyToMany.dto.ProducerDto;
import com.example.ManyToMany.dto.ProductDto;
import com.example.ManyToMany.repository.ProducerRepository;
import com.example.ManyToMany.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private ProducerRepository producerRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProducerRepository producerRepository) {
        this.productRepository = productRepository;
        this.producerRepository = producerRepository;
    }
    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = new Product();
        // map fields from productDto to product
        product.setName(productDto.getName());
        // map producerDtos field from productDto to producers field in product
        Set<ProducerDto> producerDtos = productDto.getProducerDtos();
        Set<Producer> producers = new HashSet<>();
        for (ProducerDto producerDto : producerDtos) {
            Optional<Producer> optionalProducer = producerRepository.findById(producerDto.getId());
            if (optionalProducer.isPresent()) {
                Producer producer = optionalProducer.get();
                // and so on for other fields
                producers.add(producer);
            }
        }
        product.setProducers(producers);
        product = productRepository.save(product);
        // map fields from product to productDto and return
        ProductDto savedProductDto = new ProductDto();
        savedProductDto.setId(product.getId());
        savedProductDto.setName(product.getName());
        savedProductDto.setProducerDtos(producerDtos);
        return savedProductDto;
    }
    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            ProductDto productDto = new ProductDto();
            // map fields from product to productDto
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            // map producers field from product to producerDtos field in productDto
            Set<Producer> producers = product.getProducers();
            Set<ProducerDto> producerDtos = new HashSet<>();
            for (Producer producer : producers) {
                ProducerDto producerDto = new ProducerDto();
                // map fields from producer to producerDto
                producerDto.setId(producer.getId());
                producerDto.setName(producer.getName());
                // and so on for other fields
                producerDtos.add(producerDto);
            }
            productDto.setProducerDtos(producerDtos);
            productDtos.add(productDto);
        }
        return productDtos;
    }@Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            // update fields of product with values from productDto
            product.setName(productDto.getName());
            // update producers field of product with values from producerDtos field in productDto
            Set<ProducerDto> producerDtos = productDto.getProducerDtos();
            Set<Producer> producers = new HashSet<>();
            for (ProducerDto producerDto : producerDtos) {
                Optional<Producer> optionalProducer = producerRepository.findById(producerDto.getId());
                if (optionalProducer.isPresent()) {
                    Producer producer = optionalProducer.get();
                    // update fields of producer with values from producerDto
                    producer.setName(producerDto.getName());
                    // and so on for other fields
                    producers.add(producer);
                }
            }
            product.setProducers(producers);
            // save updated product to repository
            productRepository.save(product);
            // map fields of updated product to a new ProductDto instance and return it
            ProductDto updatedProductDto = new ProductDto();
            updatedProductDto.setId(product.getId());
            updatedProductDto.setName(product.getName());
            updatedProductDto.setProducerDtos(producerDtos);
            return updatedProductDto;
        } else {
            // return null or throw an exception if product not found
            return null;
        }
    }


    @Override
    public ProductDto getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            ProductDto productDto = new ProductDto();
            // map fields from product to productDto
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            // map producers field from product to producerDto field in productDto
            Set<Producer> producers = product.getProducers();
            Set<ProducerDto> producerDtos = new HashSet<>();
            for (Producer producer : producers) {
                ProducerDto producerDto = new ProducerDto();
                // map fields from producer to producerDto
                producerDto.setId(producer.getId());
                producerDto.setName(producer.getName());
                // and so on for other fields
                producerDtos.add(producerDto);
            }
            productDto.setProducerDtos(producerDtos);
            return productDto;
        } else {
            // return null or throw an exception if product not found
            return null;
        }
    }
    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }


}