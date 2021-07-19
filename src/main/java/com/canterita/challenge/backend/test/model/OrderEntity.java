package com.canterita.challenge.backend.test.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.canterita.challenge.backend.test.dto.OrderDetailsDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity(name = "ORDER_ENTITY")
public class OrderEntity {
		
	public OrderEntity() {		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "number")
	private String number;
	
	@Column(name = "client")
	private String client;

	@Column(name = "total")
	private Double total;
	
	@Column(name = "date_order")
	private LocalDateTime dateOrder;
	
	@OneToMany(mappedBy = "order", cascade = {CascadeType.ALL})
	private List<OrderDetailEntity> ordersDetailsList;
	
	public void addOrderDetails(OrderDetailsDto orderDetails) {
		if(ordersDetailsList == null) {
			ordersDetailsList = new ArrayList<>();
		}		
		ordersDetailsList.add(new OrderDetailEntity(
				orderDetails.getId(),
				this,
				orderDetails.getDetail(),
				orderDetails.getCantidad(),
				orderDetails.getPrecioUnitario(),
				orderDetails.getTotalDetail()));	
	}
}