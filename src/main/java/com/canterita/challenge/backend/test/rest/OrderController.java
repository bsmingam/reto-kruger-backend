package com.canterita.challenge.backend.test.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.canterita.challenge.backend.test.dto.OrderDto;
import com.canterita.challenge.backend.test.serviceInterfaces.IOrderService;


@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
	
	@Autowired
	private IOrderService orderService;

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public OrderDto getOrder(@PathVariable Long id) {
		return orderService.getOrder(id);
	}
	
	@GetMapping(value = "/{id}/details")
	@ResponseStatus(HttpStatus.OK)
	public OrderDto getOrderDetail(@PathVariable Long id) {
		return orderService.getOrderDetail(id);
	}
	
	@PostMapping
	public OrderDto saveOrder(@RequestBody OrderDto order) {
		return orderService.saveOrder(order);
	}
	
	@PutMapping 
	public OrderDto updateOrder(@RequestBody OrderDto order) {
		return orderService.updateOrder(order);
	}

	@GetMapping
	public List<OrderDto> getAll() {
		return orderService.getAllOrders();
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
	}
}
 