package com.example.ManyToMany.business.concretes;

import com.example.ManyToMany.business.abstracts.ProducerService;
import com.example.ManyToMany.domain.Producer;
import com.example.ManyToMany.dto.ProducerDto;
import com.example.ManyToMany.repository.ProducerRepository;
import com.example.ManyToMany.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ProducerServiceImpl implements ProducerService {
    private ProductRepository productRepository;
    private ProducerRepository producerRepository;
    @Autowired
    public ProducerServiceImpl(ProductRepository productRepository, ProducerRepository producerRepository) {
        this.productRepository = productRepository;
        this.producerRepository = producerRepository;
    }

    @Override
    public ProducerDto createProducer(ProducerDto producerDto) {
        // map fields from producerDto to producer
        Producer producer = new Producer();
        producer.setId(producerDto.getId());
        producer.setName(producerDto.getName());
        // and so on for other fields

        // set other fields on the producer object using the setter methods

        producer = producerRepository.save(producer);

        // map fields from producer to producerDto and return
        producerDto.setId(producer.getId());
        return producerDto;
    }

    @Override
    public List<ProducerDto> getAllProducers() {
        List<Producer> producers = producerRepository.findAll();
        List<ProducerDto> producerDtos = new ArrayList<>();
        for (Producer producer : producers) {
            ProducerDto producerDto = new ProducerDto();
            // map fields from producer to producerDto
            producerDto.setId(producer.getId());
            producerDto.setName(producer.getName());


            producerDtos.add(producerDto);
        }
        return producerDtos;
    }

    @Override
    public ProducerDto getProducerById(Long id) {
        Optional<Producer> optionalProducer = producerRepository.findById(id);
        if (optionalProducer.isPresent()) {
            Producer producer = optionalProducer.get();
// map fields from producer to producerDto and return
            ProducerDto producerDto = new ProducerDto();
            producerDto.setId(producer.getId());
            producerDto.setName(producer.getName());
            return producerDto;
        } else {
// return null or throw an exception if producer not found
            return null;
        }
    }


    @Override
    public void deleteProducerById(Long id) {
        producerRepository.deleteById(id);
    }

    @Override
    public ProducerDto updateProducer(Long id, ProducerDto producerDto) {
        Optional<Producer> optionalProducer = producerRepository.findById(id);
        if (optionalProducer.isPresent()) {
            Producer producer = optionalProducer.get();
// update fields in producer using data from producerDto
            producer.setId(producerDto.getId());
            producer.setName(producerDto.getName());
// and so on for other fields
            producer = producerRepository.save(producer);
// map updated fields from producer to producerDto and return
            producerDto.setId(producer.getId());
            return producerDto;
        } else {
// return null or throw an exception if producer not found
            return null;
        }
    }
}
