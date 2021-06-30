package com.controle.spring.domain.repository.infrasctruture.repository.order;

import java.util.List;

import com.controle.spring.domain.model.Order;

public interface OrderRepositoryQueries {

	/**
	Lista todas comandas em aberto
	*/
	List<Order> openOrder();
	
	/**
	Deleta itens por id comanda
	*/
	void deleteItens(long id);
	/**
	Cancela comanda por id
	*/
	boolean cancelOrder(long id);
}
