package com.example.myshop.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


public interface ProductRepository extends JpaRepository<Product, Long> {
}