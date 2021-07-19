package com.canterita.challenge.backend.test.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.canterita.challenge.backend.test.dto.OrderDetailsDto;
import com.canterita.challenge.backend.test.serviceInterfaces.IOrderDetailService;


@RestController
@RequestMapping("/api/v1/orders/details")
public class OrderDetailController {
	
	@Autowired
	private IOrderDetailService orderDetailService;

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public OrderDetailsDto getOrderDetails(@PathVariable Long id) {
		return orderDetailService.getOrderDetail(id);
	}
	
	@PostMapping
	public OrderDetailsDto saveOrderDetails(@RequestBody OrderDetailsDto order) {
		return orderDetailService.saveOrderDetail(order);
	}
	
	@PutMapping
	public OrderDetailsDto updateOrderDetails(@RequestBody OrderDetailsDto order) {
		return orderDetailService.updateOrderDetail(order);
	}
		
	@DeleteMapping(value = "/{id}")
	public void deleteOrderDetails(@PathVariable Long id) {
		orderDetailService.deleteOrderDetail(id);
	}
}
 