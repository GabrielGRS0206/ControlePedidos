package com.controle.spring.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controle.spring.domain.model.Product;
import com.controle.spring.domain.repository.infrasctruture.repository.product.ProductRepositoryQueries;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>,ProductRepositoryQueries {

}
