package com.canterita.challenge.backend.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDetailsDto {

	private Long id;
	
	private OrderDto order;

	private String detail;
	
	private Double cantidad;

	private Double precioUnitario;

	private Double totalDetail;	
}
