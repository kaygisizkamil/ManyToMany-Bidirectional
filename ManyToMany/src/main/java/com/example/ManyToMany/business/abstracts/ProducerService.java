package com.example.ManyToMany.business.abstracts;

import com.example.ManyToMany.dto.ProducerDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProducerService {
    ProducerDto createProducer(ProducerDto productDto);
    List<ProducerDto> getAllProducers();
    ProducerDto getProducerById(Long id);
    void deleteProducerById(Long id);
    ProducerDto updateProducer(Long id,ProducerDto productDto);
}
