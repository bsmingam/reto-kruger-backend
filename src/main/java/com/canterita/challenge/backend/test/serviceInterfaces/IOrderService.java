package com.canterita.challenge.backend.test.serviceInterfaces;

import java.util.List;

import com.canterita.challenge.backend.test.dto.OrderDto;

public interface IOrderService {
	
	public OrderDto getOrder(Long id);
	public OrderDto saveOrder(OrderDto order);
	public OrderDto updateOrder(OrderDto order);
	public void deleteOrder(Long id);
	public List<OrderDto> getAllOrders();
}
