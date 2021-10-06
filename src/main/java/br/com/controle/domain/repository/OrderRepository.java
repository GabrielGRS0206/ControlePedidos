package br.com.controle.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.controle.domain.model.Order;
import br.com.controle.domain.repository.infrasctruture.repository.order.OrderRepositoryQueries;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryQueries{

}
