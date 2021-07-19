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
				.map(order -> createOrderDto(order))
				.orElse(null);
	}
	
	@Override
	public OrderDto getOrderDetail(Long id) {
		return orderRepository.findById(id)
				.map(order -> createOrderWithDtoDetail(order))
				.orElse(null);
	}
		
	@Override
	public OrderDto saveOrder(OrderDto order) {
		OrderEntity orden = createOrderEntity(order);
		
		orden.setOrdersDetailsList(listDtoToEntity(order.getOrdersDetailsList(), orden));		
		OrderEntity savedOrder = orderRepository.save(orden);
		return new OrderDto(savedOrder.getId(), savedOrder.getNumber(), savedOrder.getClient(), savedOrder.getTotal(), savedOrder.getDateOrder(), order.getOrdersDetailsList());
	}

	@Override
	public OrderDto updateOrder(OrderDto order) {
		OrderEntity orden = createOrderEntity(order);
		
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
			list.add(createOrderDto(order));
		}
		return list;
	}
	
	private List<OrderDetailsDto> listEntityToDto(List<OrderDetailEntity> listEntity){
		List<OrderDetailsDto> list = new ArrayList<OrderDetailsDto>();
		for (OrderDetailEntity entity : listEntity) {
			list.add(new OrderDetailsDto(
					entity.getId(), 
					createOrderDto(entity.getOrder()),
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
	
	
	private OrderEntity createOrderEntity(OrderDto order) {
		return new OrderEntity(
				order.getId(), 
				order.getNumber(), 
				order.getClient(), 
				order.getTotal(), 
				order.getDateOrder(), 
				null);
	}
	
	private OrderDto createOrderDto(OrderEntity entity) {
		return 	new OrderDto(
				entity.getId(), 
				entity.getNumber(), 
				entity.getClient(), 
				entity.getTotal(), 
				entity.getDateOrder(), 
				null);
	}	 
	
	private OrderDto createOrderWithDtoDetail(OrderEntity entity) {
		OrderDto orderDto = createOrderDto(entity);
		orderDto.setOrdersDetailsList(listEntityToDto(entity.getOrdersDetailsList()));
		return 	orderDto;
	}
}
