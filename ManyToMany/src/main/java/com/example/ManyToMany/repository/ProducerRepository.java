package com.example.ManyToMany.repository;

import com.example.ManyToMany.domain.Producer;
import com.example.ManyToMany.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository  extends JpaRepository<Producer,Long> {

}
