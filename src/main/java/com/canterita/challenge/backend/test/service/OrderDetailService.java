package com.canterita.challenge.backend.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.canterita.challenge.backend.test.dto.OrderDetailsDto;
import com.canterita.challenge.backend.test.dto.OrderDto;
import com.canterita.challenge.backend.test.model.OrderDetailEntity;
import com.canterita.challenge.backend.test.model.OrderEntity;
import com.canterita.challenge.backend.test.repository.OrderDetailRepository;
import com.canterita.challenge.backend.test.serviceInterfaces.IOrderDetailService;

@Service
public class OrderDetailService implements IOrderDetailService{
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Override
	public OrderDetailsDto getOrderDetail(Long id) {
		return orderDetailRepository.findById(id)
				.map(orderDetail -> 
				newOrderDetailsDto(orderDetail))
				.orElse(null);
	}
		
	@Override
	public OrderDetailsDto saveOrderDetail(OrderDetailsDto orderDetail) {
		OrderDetailEntity savedOrderDetail = orderDetailRepository.save(newOrderDetailsEntity(orderDetail));				
		return newOrderDetailsDto(savedOrderDetail);
	}

	@Override
	public OrderDetailsDto updateOrderDetail(OrderDetailsDto orderDetail) {
		OrderDetailEntity savedOrderDetail = orderDetailRepository.save(newOrderDetailsEntity(orderDetail));		
		return newOrderDetailsDto(savedOrderDetail);
	}

	@Override
	public void deleteOrderDetail(Long id) {
		orderDetailRepository.deleteById(id);
	}
	
	private OrderDto createOrderDto(OrderEntity orderEntity) {
		return new OrderDto(
				orderEntity.getId(), 
				orderEntity.getNumber(), 
				orderEntity.getClient(),
				orderEntity.getTotal(), 
				orderEntity.getDateOrder(),
				null);
	}
	
	private OrderEntity createOrderEntity(OrderDto orderDto) {
		return new OrderEntity(
				orderDto.getId(),
				orderDto.getNumber(),
				orderDto.getClient(),
				orderDto.getTotal(),
				orderDto.getDateOrder(),
				null);
	}
	
	private OrderDetailsDto newOrderDetailsDto(OrderDetailEntity savedOrderDetail) {
		return new OrderDetailsDto(
				savedOrderDetail.getId(), 
				createOrderDto(savedOrderDetail.getOrder()),
				savedOrderDetail.getDetail(), 
				savedOrderDetail.getCantidad(), 
				savedOrderDetail.getPrecioUnitario(), 
				savedOrderDetail.getTotalDetail());
	}
	
	private OrderDetailEntity newOrderDetailsEntity(OrderDetailsDto orderDetail) {
		return new OrderDetailEntity(
				orderDetail.getId(), 
				createOrderEntity(orderDetail.getOrder()),
				orderDetail.getDetail(), 
				orderDetail.getCantidad(), 
				orderDetail.getPrecioUnitario(), 
				orderDetail.getTotalDetail());
	}
}
