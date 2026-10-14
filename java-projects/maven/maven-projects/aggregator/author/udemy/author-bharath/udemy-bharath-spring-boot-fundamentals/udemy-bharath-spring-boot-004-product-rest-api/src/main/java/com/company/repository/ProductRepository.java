package com.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
