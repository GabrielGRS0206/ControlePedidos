package com.controle.spring.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.controle.spring.domain.exception.notFound.CashRegisterException;
import com.controle.spring.domain.exception.notFound.OrderNotFoundException;
import com.controle.spring.domain.model.CashRegister;
import com.controle.spring.domain.model.Order;
import com.controle.spring.domain.model.OrderItem;
import com.controle.spring.domain.repository.OrderItemRepository;
import com.controle.spring.domain.repository.OrderRepository;
import com.controle.spring.domain.repository.ProductRepository;
import com.controle.spring.domain.utils.SpringUtils;
import com.controle.spring.domain.utils.interfaces.service.Services;

@Service
public class OrderService implements Services<Order>{

	public OrderRepository repository;

	public OrderItemRepository orderItemRepository;

	public ProductRepository productRepository;

	public CashRegisterService cashRegisterService;

	public OrderService(OrderRepository repository,
			OrderItemRepository itemRepository,
			ProductRepository mercadoriaRepository,
			CashRegisterService cashRegisterService) {
		this.repository = repository;
		this.orderItemRepository = itemRepository;
		this.productRepository = mercadoriaRepository;
		this.cashRegisterService = cashRegisterService;
	}

	@Override
	public Order save(Object obj) {
		Objects.requireNonNull(obj, "Objeto nao pode ser null");

		//======VERIFICA SE CAIXA INFORMADO EXISTE E ESTÁ ABERTO======
		if(((Order) obj).getCashRegister() == null || 
				((Order) obj).getCashRegister().getId() == 0) {
			throw new CashRegisterException("Deve ser informado um id de caixa");
		} else {

			Optional<CashRegister> cashRegister = cashRegisterService.findById(((Order) obj).getCashRegister().getId());

			if(cashRegister.isPresent()) {
				if(SpringUtils.valueDiffZero(cashRegister.get().getTotal_closure())) {
					throw new CashRegisterException("Caixa com código "+cashRegister.get().getId()+" já está com valor de fechamento");
				}
			} else {
				throw new CashRegisterException(((Order) obj).getCashRegister().getId());
			}
		}

		List<OrderItem> items =  ((Order) obj).getItems();

		BigDecimal total = BigDecimal.ZERO;

		for(OrderItem item : items) {
			total = total.add(item.totalItem());
		}

		Order porder = (Order) obj;
		porder.setTotal(total);
		repository.save(porder);

		items.forEach(e -> e.setId(null));
		items.forEach(e -> e.setOrder(porder));

		orderItemRepository.saveAll(items);

		items.forEach(e -> e.setProduct(productRepository.findById(e.getProductId()).get()));
		porder.setItems(items);

		return porder;
	}

	@Override
	public Order update(Object obj) {

		Objects.requireNonNull(obj, "Objeto nao pode ser null");

		//======VERIFICA SE CAIXA INFORMADO EXISTE E ESTÁ ABERTO======
		if(((Order) obj).getCashRegister() == null || 
				((Order) obj).getCashRegister().getId() == 0) {
			throw new CashRegisterException("Deve ser informado um id de caixa");
		} else {

			Optional<CashRegister> cashRegister = cashRegisterService.findById(((Order) obj).getCashRegister().getId());

			if(cashRegister.isPresent()) {
				if(SpringUtils.valueDiffZero(cashRegister.get().getTotal_closure())) {
					throw new CashRegisterException("Caixa com código "+cashRegister.get().getId()+" já está com valor de fechamento");
				}
			} else {
				throw new CashRegisterException(((Order) obj).getCashRegister().getId());
			}
		}

		repository.deleteItens(((Order) obj).getIdOrder());
		List<OrderItem> items =  ((Order) obj).getItems();

		BigDecimal total = BigDecimal.ZERO;

		for(OrderItem item : items) {
			total = total.add(item.totalItem());
		}

		Order order = (Order) obj;
		order.setTotal(total);
		items.forEach(e -> e.setId(null));
		repository.save(order);

		items.forEach(e -> e.setOrder(order));

		orderItemRepository.saveAll(items);

		items.forEach(e -> e.setProduct(productRepository.findById(e.getProductId()).get()));
		order.setItems(items);

		return order;
	}

	@Override
	public void delete(long codigo) {
		// TODO Auto-generated method stub
	}

	@Override
	public Optional<Order> findById(long codigo) {
		Optional<Order> order = repository.findById(codigo);

		if(!order.isPresent()) {
			throw new OrderNotFoundException(codigo);
		}

		return order;
	}

	@Override
	public List<Order> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean existsById(long codigo) {

		if(!repository.existsById(codigo)) {
			throw new OrderNotFoundException(codigo);
		}

		return true;
	}

	public List<Order> openOrder() {
		return repository.openOrder();
	}

	public boolean cancel(Long id) {

		if(existsById(id)) {
			return cancelOrder(id);
		}
		return false;
	}

	private boolean cancelOrder(long id) {
		return repository.cancelOrder(id);
	}


}
