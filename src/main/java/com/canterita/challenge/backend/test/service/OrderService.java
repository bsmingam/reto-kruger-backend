package com.canterita.challenge.backend.test.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.canterita.challenge.backend.test.dto.OrderDetailsDto;
import com.canterita.challenge.backend.test.dto.OrderDto;
import com.canterita.challenge.backend.test.model.OrderDetailEntity;
import com.canterita.challenge.backend.test.model.OrderEntity;
import com.canterita.challenge.backend.test.repository.OrderRepository;
import com.canterita.challenge.backend.test.serviceInterfaces.IOrderService;

@Service
public class OrderService implements IOrderService{
		
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public OrderDto getOrder(Long id) {
		return orderRepository.findById(id)
				.map(order -> new OrderDto(
						order.getId(), 
						order.getNumber(), 
						order.getClient(), 
						order.getTotal(), 
						order.getDateOrder(), 
						listEntityToDto(order.getOrdersDetailsList())))
				.orElse(null);
	}
	
	@Override
	public OrderDto saveOrder(OrderDto order) {
		OrderEntity orden = new OrderEntity(
				order.getId(), 
				order.getNumber(), 
				order.getClient(), 
				order.getTotal(), 
				order.getDateOrder(), 
				null);
		
		orden.setOrdersDetailsList(listDtoToEntity(order.getOrdersDetailsList(), orden));		
		OrderEntity savedOrder = orderRepository.save(orden);
		return new OrderDto(savedOrder.getId(), savedOrder.getNumber(), savedOrder.getClient(), savedOrder.getTotal(), savedOrder.getDateOrder(), order.getOrdersDetailsList());
	}

	@Override
	public OrderDto updateOrder(OrderDto order) {
		OrderEntity orden = new OrderEntity(
				order.getId(), 
				order.getNumber(), 
				order.getClient(), 
				order.getTotal(), 
				order.getDateOrder(), 
				null);
		
		orden.setOrdersDetailsList(listDtoToEntity(order.getOrdersDetailsList(), orden));		
		OrderEntity savedOrder = orderRepository.save(orden);
		return new OrderDto(savedOrder.getId(), savedOrder.getNumber(), savedOrder.getClient(), savedOrder.getTotal(), savedOrder.getDateOrder(), order.getOrdersDetailsList());
	}

	@Override
	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);
	}

	@Override
	public List<OrderDto> getAllOrders() {
		List<OrderDto> list = new ArrayList<>();
		for (OrderEntity order: orderRepository.findAll()) {
			list.add(new OrderDto(
					order.getId(), 
					order.getNumber(), 
					order.getClient(), 
					order.getTotal(), 
					order.getDateOrder(), 
					null));
		}
		return list;
	}
	
	private List<OrderDetailsDto> listEntityToDto(List<OrderDetailEntity> listEntity){
		List<OrderDetailsDto> list = new ArrayList<OrderDetailsDto>();
		for (OrderDetailEntity entity : listEntity) {
			list.add(new OrderDetailsDto(
					entity.getId(), 
					new OrderDto(
							entity.getOrder().getId(), 
							entity.getOrder().getNumber(), 
							entity.getOrder().getClient(), 
							entity.getOrder().getTotal(), 
							entity.getOrder().getDateOrder(), 
							null), 
					entity.getDetail(), 
					entity.getCantidad(), 
					entity.getPrecioUnitario(), 
					entity.getTotalDetail()));
		}
		return list;
	}
	
	private List<OrderDetailEntity> listDtoToEntity(List<OrderDetailsDto> listDto, OrderEntity order){
		List<OrderDetailEntity> list = new ArrayList<OrderDetailEntity>();
		for (OrderDetailsDto dto : listDto) {
			list.add(new OrderDetailEntity(
					dto.getId(), 
					order, 
					dto.getDetail(), 
					dto.getCantidad(), 
					dto.getPrecioUnitario(), 
					dto.getTotalDetail()));
		}
		return list;
	}
}
