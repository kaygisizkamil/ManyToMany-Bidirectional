package com.example.ManyToMany.dto;


import com.example.ManyToMany.domain.Producer;
import lombok.Data;

import java.util.Set;

@Data
public class ProductDto {
    private Long id;
    private String name;
    @JsonIgnore
    private Set<ProducerDto> producerDtos;
 //   private ProducerDto producerDto;
}
