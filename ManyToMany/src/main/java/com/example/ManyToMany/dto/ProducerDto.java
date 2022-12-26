package com.example.ManyToMany.dto;

import com.example.ManyToMany.domain.Product;
import lombok.Data;

import java.util.Set;

@Data
public class ProducerDto {
    private Long id;

    private String name;
    private Set<Product> products;
}
