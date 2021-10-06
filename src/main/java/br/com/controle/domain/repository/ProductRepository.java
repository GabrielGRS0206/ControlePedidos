package br.com.controle.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.controle.domain.model.Product;
import br.com.controle.domain.repository.infrasctruture.repository.product.ProductRepositoryQueries;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>,ProductRepositoryQueries {

}
