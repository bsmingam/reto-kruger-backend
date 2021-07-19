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
				new OrderDetailsDto(
						orderDetail.getId(), 
						new OrderDto(
								orderDetail.getOrder().getId(), 
								orderDetail.getOrder().getNumber(), 
								orderDetail.getOrder().getClient(),
								orderDetail.getOrder().getTotal(), 
								orderDetail.getOrder().getDateOrder(),
								null), 
						orderDetail.getDetail(), 
						orderDetail.getCantidad(), 
						orderDetail.getPrecioUnitario(), 
						orderDetail.getTotalDetail()))
				.orElse(null);
	}
	
	
	@Override
	public OrderDetailsDto saveOrderDetail(OrderDetailsDto orderDetail) {
		OrderDetailEntity savedOrderDetail = orderDetailRepository.save(
				new OrderDetailEntity(
						orderDetail.getId(), 
						new OrderEntity(
								orderDetail.getOrder().getId(),
								orderDetail.getOrder().getNumber(),
								orderDetail.getOrder().getClient(),
								orderDetail.getOrder().getTotal(),
								orderDetail.getOrder().getDateOrder(),
								null),
						orderDetail.getDetail(), 
						orderDetail.getCantidad(), 
						orderDetail.getPrecioUnitario(), 
						orderDetail.getTotalDetail()));
				
		return new OrderDetailsDto(
				savedOrderDetail.getId(), 
				new OrderDto(
						savedOrderDetail.getOrder().getId(), 
						savedOrderDetail.getOrder().getNumber(), 
						savedOrderDetail.getOrder().getClient(), 
						savedOrderDetail.getOrder().getTotal(), 
						savedOrderDetail.getOrder().getDateOrder(), 
						null), 
				savedOrderDetail.getDetail(), 
				savedOrderDetail.getCantidad(), 
				savedOrderDetail.getPrecioUnitario(), 
				savedOrderDetail.getTotalDetail());
	}

	@Override
	public OrderDetailsDto updateOrderDetail(OrderDetailsDto orderDetail) {
		OrderDetailEntity savedOrderDetail = orderDetailRepository.save(new OrderDetailEntity(
				orderDetail.getId(), 
				new OrderEntity(
						orderDetail.getOrder().getId(),
						orderDetail.getOrder().getNumber(),
						orderDetail.getOrder().getClient(),
						orderDetail.getOrder().getTotal(),
						orderDetail.getOrder().getDateOrder(),
						null
						),
				orderDetail.getDetail(), 
				orderDetail.getCantidad(), 
				orderDetail.getPrecioUnitario(), 
				orderDetail.getTotalDetail()));
		
		return new OrderDetailsDto(
				savedOrderDetail.getId(), 
				new OrderDto(
						savedOrderDetail.getOrder().getId(), 
						savedOrderDetail.getOrder().getNumber(), 
						savedOrderDetail.getOrder().getClient(), 
						savedOrderDetail.getOrder().getTotal(), 
						savedOrderDetail.getOrder().getDateOrder(), 
						null), 
				savedOrderDetail.getDetail(), 
				savedOrderDetail.getCantidad(), 
				savedOrderDetail.getPrecioUnitario(), 
				savedOrderDetail.getTotalDetail());
	}

	@Override
	public void deleteOrderDetail(Long id) {
		orderDetailRepository.deleteById(id);
	}
}
