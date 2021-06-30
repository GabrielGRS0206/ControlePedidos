package com.controle.spring.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controle.spring.domain.model.Order;
import com.controle.spring.domain.repository.infrasctruture.repository.order.OrderRepositoryQueries;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryQueries{

}
