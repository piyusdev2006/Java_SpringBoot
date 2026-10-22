package com.piyus.TestingInSpringBoot.repository;


import com.piyus.TestingInSpringBoot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
        extends JpaRepository<Product, Long> {


    boolean existsByName(String name);
}
