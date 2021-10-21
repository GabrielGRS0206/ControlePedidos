package br.com.controle.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controle.domain.exception.business.BusinessException;
import br.com.controle.domain.exception.business.MessageException;
import br.com.controle.domain.model.CashRegister;
import br.com.controle.domain.model.Order;
import br.com.controle.domain.model.OrderItem;
import br.com.controle.domain.model.StatusOrder;
import br.com.controle.domain.repository.OrderItemRepository;
import br.com.controle.domain.repository.OrderRepository;
import br.com.controle.domain.repository.ProductRepository;
import br.com.controle.domain.utils.SpringUtils;
import br.com.controle.domain.utils.interfaces.service.Services;

@Service
public class OrderService implements Services<Order> {

	@Autowired
	public OrderRepository repository;

	@Autowired
	public OrderItemRepository orderItemRepository;

	@Autowired
	public ProductRepository productRepository;

	@Autowired
	public CashRegisterService cashRegisterService;

	@Override
	public Order save(Object obj) {
		Objects.requireNonNull(obj, MessageException.OBJECT_NOT_NULL.getValue());

		if (((Order) obj).getCashRegister() == null || ((Order) obj).getCashRegister().getId() == 0) {
			throw new BusinessException(MessageException.MSG_CAIXA_NAO_ENCONTRADO.getValue(),
					((Order) obj).getCashRegister().getId());
		} else {

			Optional<CashRegister> cashRegister = cashRegisterService.findById(((Order) obj).getCashRegister().getId());

			if (cashRegister.isPresent()) {
				if (SpringUtils.valueDiffZero(cashRegister.get().getTotalClosure())) {
					throw new BusinessException(MessageException.MSG_CASH_REGISTER_CLOSE.getValue(),
							cashRegister.get().getId());
				}
			} else {
				throw new BusinessException(MessageException.MSG_CAIXA_NAO_ENCONTRADO.getValue(),
						((Order) obj).getCashRegister().getId());
			}
		}

		List<OrderItem> items = ((Order) obj).getItems();

		BigDecimal total = BigDecimal.ZERO;

		for (OrderItem item : items) {
			total = total.add(item.totalItem());
		}

		Order order = (Order) obj;
		order.setStatus(StatusOrder.ABERTA);
		order.setTotal(total);
		repository.save(order);

//		items.forEach(e -> e.setId(null));
		items.forEach(e -> e.setOrder(order));

		orderItemRepository.saveAll(items);
		items.forEach(e -> e.setProduct(productRepository.findById(e.getProduct().getId()).get()));
		order.setItems(items);

		return order;
	}

	@Override
	public Order update(Object obj) {

		Objects.requireNonNull(obj, MessageException.OBJECT_NOT_NULL.getValue());

		if (((Order) obj).getCashRegister() == null || ((Order) obj).getCashRegister().getId() == 0) {
			throw new BusinessException(MessageException.MSG_INFORME_CASH_REGISTER.getValue());
		} else {

			Optional<CashRegister> cashRegister = cashRegisterService.findById(((Order) obj).getCashRegister().getId());

			if (cashRegister.isPresent()) {
				if (SpringUtils.valueDiffZero(cashRegister.get().getTotalClosure())) {
					throw new BusinessException(MessageException.MSG_CASH_REGISTER_CLOSE.getValue(),
							cashRegister.get().getId());
				}
			} else {
				throw new BusinessException(MessageException.MSG_CAIXA_NAO_ENCONTRADO.getValue(),
						((Order) obj).getCashRegister().getId());
			}
		}

		repository.deleteItens(((Order) obj).getIdOrder());
		List<OrderItem> items = ((Order) obj).getItems();

		BigDecimal total = BigDecimal.ZERO;

		for (OrderItem item : items) {
			total = total.add(item.totalItem());
		}

		Order order = (Order) obj;
		order.setTotal(total);
		items.forEach(e -> e.setId(null));
		repository.save(order);

		items.forEach(e -> e.setOrder(order));

		orderItemRepository.saveAll(items);

		items.forEach(e -> e.setProduct(productRepository.findById(e.getProduct().getId()).get()));
		order.setItems(items);

		return order;
	}

	@Override
	public void delete(long codigo) {
	}

	@Override
	public Optional<Order> findById(long id) {
		Optional<Order> order = repository.findById(id);

		if (!order.isPresent()) {
			throw new BusinessException(MessageException.MSG_COMANDA_NAO_ENCONTRADA.getValue(), id);
		}

		return order;
	}

	@Override
	public List<Order> findAll() {
		return repository.findAll();
	}

	@Override
	public boolean existsById(long id) {
		if (!repository.existsById(id)) {
			throw new BusinessException(MessageException.MSG_COMANDA_NAO_ENCONTRADA.getValue(), id);
		}
		return true;
	}

	public List<Order> openOrders() {
		return repository.openOrders();
	}

	public boolean cancel(Long id) {

		if (existsById(id)) {
			return cancelOrder(id);
		}
		return false;
	}

	private boolean cancelOrder(long id) {
		return repository.cancelOrder(id);
	}

}
