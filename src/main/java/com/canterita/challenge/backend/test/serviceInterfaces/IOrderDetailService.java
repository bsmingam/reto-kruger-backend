package com.canterita.challenge.backend.test.serviceInterfaces;

import com.canterita.challenge.backend.test.dto.OrderDetailsDto;

public interface IOrderDetailService {
	
	public OrderDetailsDto getOrderDetail(Long id);
	public OrderDetailsDto saveOrderDetail(OrderDetailsDto order);
	public OrderDetailsDto updateOrderDetail(OrderDetailsDto order);
	public void deleteOrderDetail(Long id);
}
