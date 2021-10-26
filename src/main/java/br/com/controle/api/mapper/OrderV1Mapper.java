package br.com.controle.api.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.controle.api.mapper.dto.request.OrderItemRequestV1Dto;
import br.com.controle.api.mapper.dto.request.OrderRequestV1Dto;
import br.com.controle.api.mapper.dto.response.OrderItemResponseV1Dto;
import br.com.controle.api.mapper.dto.response.OrderResponseV1Dto;
import br.com.controle.domain.model.Client;
import br.com.controle.domain.model.Order;
import br.com.controle.domain.model.OrderItem;
import br.com.controle.domain.model.Payment;

@Component
public class OrderV1Mapper extends ValidConstrains<Order> {

	public static final String SUMMARY = "S";
	public static final String COMPLETE = "C";

	public OrderResponseV1Dto entityToDto(Order order, String tipo) {
		var response = new OrderResponseV1Dto();
		response.setId(order.getIdOrder());
		response.setCity(order.getCity());
		response.setComplement(order.getComplement());
		response.setDelivery(order.getDelivery());
		response.setIdClient(order.getClient() != null ? order.getClient().getId() : 0);
		response.setName(order.getNameClient());
		response.setObservation(order.getObservation());
		response.setProximity(order.getProximity());
		response.setStatus(order.getStatus());
		response.setStreet(order.getStreet());
		response.setValueTotal(order.getTotal());
		response.setPaymentCod(order.getPayment());
		response.setPaymentDescription(Payment.descriptionCod(order.getPayment()));

		if (tipo.equals(COMPLETE)) {
			response.setItems(itemsToDto(order.getItems()));
		}

		return response;
	}

	public Order dtoToEntity(OrderRequestV1Dto request) {
		var entity = new Order();
		entity.setCashRegisterId(request.getCashRegisterId());
		entity.setCity(request.getCity());
		entity.setNameClient(request.getNameClient());
		entity.setComplement(request.getComplement());
		entity.setContact(request.getContact());
		entity.setDelivery(request.getDelivery());
		entity.setObservation(request.getObservation());
		entity.setPayment(request.getPayment());
		entity.setItems(itemToEntity(request.getItems()));
		entity.setTotal(total(entity.getItems()));

		if (request.getIdClient() != null) {
			entity.setClient(new Client(request.getIdClient()));
		}

		isValid(entity);
		return entity;
	}

	public List<OrderItemResponseV1Dto> itemsToDto(List<OrderItem> items) {
		return items.stream().map(e -> OrderItemResponseV1Dto.toDto(e)).collect(Collectors.toList());
	}

	private List<OrderItem> itemToEntity(List<OrderItemRequestV1Dto> items) {
		return items.stream().map(e -> OrderItemRequestV1Dto.dtoToEntity(e)).collect(Collectors.toList());
	}

	public List<OrderResponseV1Dto> listToDto(List<Order> list, String tipo) {
		return list.stream().map(element -> entityToDto(element, tipo)).collect(Collectors.toList());
	}

	private BigDecimal total(List<OrderItem> items) {
		BigDecimal value = BigDecimal.ZERO;

		for (OrderItem e : items) {
			value = value.add(e.totalItem());
		}
		return value;
	}
}