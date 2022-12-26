package com.example.ManyToMany.controller;
import com.example.ManyToMany.business.abstracts.ProducerService;
import com.example.ManyToMany.business.abstracts.ProductService;
import com.example.ManyToMany.dto.ProducerDto;
import com.example.ManyToMany.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producers")
public class ProducerController {

    private ProducerService producerService;

    @Autowired
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping
    public ResponseEntity<ProducerDto> createProducer(@RequestBody ProducerDto producerDto) {
        ProducerDto createdProducerDto = producerService.createProducer(producerDto);
        return new ResponseEntity<>(createdProducerDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProducerDto>> getAllProducers() {
        List<ProducerDto> producerDtos = producerService.getAllProducers();
        return new ResponseEntity<>(producerDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProducerDto> getProducerById(@PathVariable Long id) {
        ProducerDto producerDto = producerService.getProducerById(id);
        if (producerDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(producerDto, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducerById(@PathVariable Long id) {
        producerService.deleteProducerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProducerDto> updateProducer(@PathVariable Long id, @RequestBody ProducerDto producerDto) {
        ProducerDto updatedProducerDto = producerService.updateProducer(id, producerDto);
        if (updatedProducerDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updatedProducerDto, HttpStatus.OK);
        }
    }
}